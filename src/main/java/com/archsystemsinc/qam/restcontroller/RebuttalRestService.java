/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.model.Role;
import com.archsystemsinc.qam.sec.util.GenericConstants;
import com.archsystemsinc.qam.service.RadUserService;
import com.archsystemsinc.qam.service.RebuttalService;
import com.archsystemsinc.qam.service.mail.MailService;
import com.archsystemsinc.qam.utils.EmailObject;
import com.archsystemsinc.qam.utils.UploadResponse;

	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class RebuttalRestService {
	private static final Logger log = Logger.getLogger(RebuttalRestService.class);
	
	@Autowired
	private RebuttalService rebuttalService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	@Autowired
	private RadUserService radUserService;
	
	@RequestMapping(value = "/rebuttallist", method = RequestMethod.POST)
	public List<Rebuttal> getRebuttalList(@RequestBody Rebuttal rebuttal){
		List<Rebuttal> data=null;
		
		try {
			log.debug("--> getRebuttalList:");
			
			data = rebuttalService.search(rebuttal);
			log.debug("<-- getRebuttalList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}	
	
	@RequestMapping(value="/uploadRebuttalFileObject", method=RequestMethod.POST,
		    consumes = {"multipart/form-data"})
    public  @ResponseBody String handleFileUpload( 
    		@RequestParam("rebuttalFileObject") MultipartFile multipartFile,@RequestParam("rebuttalId") Integer rebuttalId){
		log.debug("--> uploadRebuttalFileObject:");		
		UploadResponse response = new UploadResponse();
        //Code to Test File Functionality
		//File tempFile = new File("C:\\Temp\\Myobject.pdf");
		OutputStream outputStream;
		//String fileName ="";
		//Integer rebuttalId = 1;
		String filename=multipartFile.getOriginalFilename();
		String name = multipartFile.getName();
		try {
		
			Rebuttal rebuttal = rebuttalService.findById(rebuttalId);
			rebuttal.setFileType(multipartFile.getContentType());
    		rebuttal.setFileName(multipartFile.getOriginalFilename());
    		rebuttal.setRebuttalFileAttachment(multipartFile.getBytes());
    		rebuttalService.saveOrUpdateRebuttal(rebuttal);
    		
		} catch (Exception e1) {
			
			e1.printStackTrace();
			return null;
		} 
		log.debug("<-- uploadRebuttalFileObject:");		
        return null;      
    }	
	
	
	private String getEmailSendList( boolean newRebuttalFlag) {
		StringBuffer sendToEmailList = new StringBuffer();
		RadUser radUser = new RadUser();
		
		Role role = new Role();
		//Set Quality Manager 
		role.setId(2l);
		radUser.setRole(role);
		List<RadUser> qualityManagers = radUserService.search(radUser);
		for(RadUser eachUser:qualityManagers) {
			sendToEmailList.append(eachUser.getUserName()+",");
			//sendToEmailList.append("sheiknissu4@gmail.com"+",");
		}
		
		//Set Administrators
		role.setId(1l);
		radUser.setRole(role);
		List<RadUser> administrators = radUserService.search(radUser);
		for(RadUser eachUser:administrators) {
			sendToEmailList.append(eachUser.getUserName()+",");
			//sendToEmailList.append("nissar.msis@gmail.com"+",");
		}
		
		//Set Administrators
		role.setId(5l);
		radUser.setRole(role);
		List<RadUser> qualityMonitors = radUserService.search(radUser);
		for(RadUser eachUser:qualityMonitors) {
			sendToEmailList.append(eachUser.getUserName()+",");
			//sendToEmailList.append("ashaik@archsystemsinc.com"+",");
		}
		
		
		return sendToEmailList.toString();
	}
	
	@RequestMapping(value = "/saveOrUpdateRebuttal", method = RequestMethod.POST)
	public @ResponseBody Rebuttal saveOrUpdateRebuttal(
			@RequestBody  Rebuttal rebuttal){
		log.debug("--> saveOrUpdateRebuttal:");		
		
		Rebuttal rebuttalResult = null;
		boolean newRebuttal = false;
		
		try {
			
			String roleType = rebuttal.getRoleType();
			
			if (rebuttal.getId() == 0) {
				newRebuttal = true;
				rebuttalResult = rebuttalService.saveOrUpdateRebuttal(rebuttal);
			} else {
				Rebuttal existingRebuttal = rebuttalService.findById(rebuttal.getId());
				if(existingRebuttal.getFileName() !=null && !existingRebuttal.getFileName().equalsIgnoreCase("")) {
					rebuttal.setFileName(existingRebuttal.getFileName());
					rebuttal.setFileType(existingRebuttal.getFileType());
					rebuttal.setRebuttalFileAttachment(existingRebuttal.getRebuttalFileAttachment());
				}
				rebuttalResult = rebuttalService.saveOrUpdateRebuttal(rebuttal);
			}
			
			
			EmailObject emailObject = new EmailObject();
			
			if(newRebuttal) {				
				emailObject.setEmailType(GenericConstants.EMAIL_TYPE_RB_CREATE);		
				
			} else if(roleType.equalsIgnoreCase("MAC User")) {				
				emailObject.setEmailType(GenericConstants.EMAIL_TYPE_RB_UPDATE_MU);					
				
			} else {
				emailObject.setEmailType(GenericConstants.EMAIL_TYPE_RB_UPDATE_CRAD);					
			}
			
			String sendToEmailList = getEmailSendList(newRebuttal);			
			emailObject.setToEmail(rebuttalResult.getCreatedBy());
			emailObject.setFromEmail(fromEmail);			
			emailObject.setToBccEmail(sendToEmailList);
			emailObject.setMacName(rebuttal.getMacName());
			emailObject.setJurisidctionName(rebuttal.getJurisName());
			emailObject.setLink(radUIEndPoint);
			mailService.sendEmail(emailObject);
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return rebuttalResult;
			
		}
		log.debug("<-- saveOrUpdateRebuttal");
		return rebuttalResult;
	}	
	
	/*Method that worked directly from JSP
	 * @RequestMapping(value="/uploadRebuttalFileObject", method=RequestMethod.POST)
    public UploadResponse handleFileUpload( 
            @RequestParam("rebuttalFileObject") MultipartFile multiPartFile,@RequestParam("id") Integer rebuttalId){
		UploadResponse response = new UploadResponse();
        //Code to Test File Functionality
		//File tempFile = new File("C:\\Temp\\Myobject.pdf");
		OutputStream outputStream;
		try {
			//Code to Test File Functionality
			//outputStream = new FileOutputStream(tempFile);
			//IOUtils.copy(file.getInputStream(), outputStream);
    		//outputStream.close();
			if (!multiPartFile.getOriginalFilename().equals("")) {
				Rebuttal rebuttal = rebuttalService.findById(rebuttalId);
				rebuttal.setFileType(multiPartFile.getContentType());
	    		rebuttal.setFileName(multiPartFile.getOriginalFilename());
	    		rebuttal.setRebuttalFileAttachment(multiPartFile.getBytes());
	    		rebuttalService.saveOrUpdateRebuttal(rebuttal);
			}
    		
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
        return null;      
    }*/
	
	
	/*@RequestMapping(value = "/download-rebuttal-document" , method = RequestMethod.POST)
    public HttpServletResponse  downloadDocument(@RequestParam("id") Integer rebuttalId, HttpServletResponse response) throws IOException {
		
		 try {
			 Rebuttal rebuttal = rebuttalService.findById(rebuttalId);//UserDocument document = userDocumentService.findById(docId);
		     response.setContentType(rebuttal.getFileType());
		     response.setContentLength(rebuttal.getRebuttalFileAttachment().length);
		     response.setHeader("Content-Disposition","attachment; filename=\"" + rebuttal.getFileName()+"\"");
		     
		     FileCopyUtils.copy(rebuttal.getRebuttalFileAttachment(), response.getOutputStream());
			 response.getOutputStream().flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		 return response;
	 }*/
	
	/*@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = {"/download-rebuttal-document"},
	                method = RequestMethod.POST)
	public HttpEntity<byte[]> downloadExcelReport(@RequestParam("id") Integer rebuttalId, HttpServletResponse response) {
		
		Rebuttal rebuttal = rebuttalService.findById(rebuttalId);
	 
	    *//** assume that below line gives you file content in byte array **//*
	    byte[] excelContent = rebuttal.getRebuttalFileAttachment();
	    // prepare response
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
	    header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=my_file.xls");
	    header.setContentLength(excelContent.length);
	 
	    return new HttpEntity<byte[]>(excelContent, header);
	}*/
	
	/*@RequestMapping(value = "/download-rebuttal-document" , method = RequestMethod.POST)
	public  ResponseEntity<byte[]> downloadErrorData(@RequestParam("id") Integer rebuttalId, HttpServletResponse response) throws Exception {
		
			Rebuttal rebuttal = rebuttalService.findById(rebuttalId);
			byte[] isr = rebuttal.getRebuttalFileAttachment();
			String fileName = rebuttal.getFileName();
			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.setContentLength(isr.length);
			//respHeaders.setContentType("application/pdf");
			respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
			return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
			// response.setContentType(rebuttal.getFileType());
			// response.setContentLength(rebuttal.getRebuttalFileAttachment().length);
			 //response.setHeader("Content-Disposition","attachment; filename=\"" + rebuttal.getFileName() +"\"");
			 
			// response.addHeader("Content-Disposition","attachment; filename=\"" + rebuttal.getFileName() +"\"");
			// response.addHeader("X-Frame-Options", "ALLOWALL");
			 
			// InputStream targetStream = new ByteArrayInputStream(rebuttal.getRebuttalFileAttachment());
			// IOUtils.copy(targetStream, response.getOutputStream());
			// response.flushBuffer();
		
	}*/
	
	/*@RequestMapping(value = "/download-document", method = RequestMethod.POST)
    public void downloadFile( HttpServletResponse response) {
        try {
			// Load file from database
			QamEnvironmentChangeForm qamEnvironmentChangeForm = qamEnvironmentChangeFormService.getQamEnvironmentChangeForm(docId);

				        return ResponseEntity.ok()
			        .contentType(MediaType.parseMediaType(qamEnvironmentChangeForm.getType()))
			        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + qamEnvironmentChangeForm.getDocumentName() + ".xlsx\"")
			        .body(new ByteArrayResource(qamEnvironmentChangeForm.getDocumentContent()));
			
			 response.setContentType(qamEnvironmentChangeForm.getType());
			 response.setContentLength(qamEnvironmentChangeForm.getDocumentContent().length);
			 response.setHeader("Content-Disposition","attachment; filename=\"" + qamEnvironmentChangeForm.getDocumentName() +".xlsx\"");
			 InputStream inputStream = new ByteArrayInputStream(qamEnvironmentChangeForm.getDocumentContent());
			 // get output stream of the response
			 OutputStream outStream = response.getOutputStream();
			 
			 byte[] buffer = new byte[BUFFER_SIZE];
		        int bytesRead = -1;
		 
		        // write bytes read from the input stream into the output stream
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		 
		        inputStream.close();
		        outStream.close();
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 try {
			 QamEnvironmentChangeForm qamEnvironmentChangeForm = qamEnvironmentChangeFormService.getQamEnvironmentChangeForm(3l);
		 	 //UserDocument document = userDocumentService.findById(docId);
		     response.setContentType(qamEnvironmentChangeForm.getType());
		     response.setContentLength(qamEnvironmentChangeForm.getDocumentContent().length);
		     response.setHeader("Content-Disposition","attachment; filename=\"" + qamEnvironmentChangeForm.getDocumentName() +".xlsx\"");
		     InputStream targetStream = new ByteArrayInputStream(qamEnvironmentChangeForm.getDocumentContent());
		     IOUtils.copy(targetStream, response.getOutputStream());
			 response.flushBuffer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
    }*/
	
	
	/*@RequestMapping(value = "/download-rebuttal-document", method = RequestMethod.POST)
	 public ResponseEntity<InputStreamResource> download(@RequestParam("id") Integer rebuttalId) throws IOException {
		
		Rebuttal rebuttal = rebuttalService.findById(rebuttalId);
		
	  System.out.println("Calling Download:- " + rebuttal.getFileName());
	  ClassPathResource pdfFile = new ClassPathResource("downloads/" + rebuttal.getFileName());
	  byte[] isr = rebuttal.getRebuttalFileAttachment();
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.parseMediaType("application/pdf"));
	  headers.add("Access-Control-Allow-Origin", "*");
	  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
	  headers.add("Access-Control-Allow-Headers", "Content-Type");
	  headers.add("Content-Disposition", "filename=" + rebuttal.getFileName());
	  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	  headers.add("Pragma", "no-cache");
	  headers.add("Expires", "0");

	  headers.setContentLength(pdfFile.contentLength());
	  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
	    new InputStreamResource(rebuttal.getRebuttalFileAttachment().   getInputStream()), headers, HttpStatus.OK);
	  return response;

	 }*/
}