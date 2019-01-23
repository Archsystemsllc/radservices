/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.Jurisdiction;
import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.model.QamEnvironmentChangeForm;
import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.sec.util.GenericConstants;
import com.archsystemsinc.qam.sec.util.RadServicesUtilityFunctions;
import com.archsystemsinc.qam.service.JurisdictionService;
import com.archsystemsinc.qam.service.MacLookupService;
import com.archsystemsinc.qam.service.QamEnvironmentChangeFormService;
import com.archsystemsinc.qam.service.RadUserService;
import com.archsystemsinc.qam.utils.UploadResponse;
	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class QamEnvironmentChangeFormRestService {
	private static final Logger log = Logger.getLogger(QamEnvironmentChangeFormRestService.class);
	
	@Autowired
	private QamEnvironmentChangeFormService qamEnvironmentChangeFormService;
	
	@Autowired
	private MacLookupService macLookupService;
	
	@Autowired
	private JurisdictionService jurisdictionService;
	
	@Autowired
	private RadUserService radUserService;
	
	 @Autowired
	 RadServicesUtilityFunctions radServicesUtilityFunctions;
	
	
	@RequestMapping(value = "/uploadQamEnvForm", method = RequestMethod.POST)
	public UploadResponse uploadFileData(@RequestParam("file") MultipartFile uploadedFile,@RequestParam("userId") Long userId,@RequestParam("macIdU") Long macId
			,@RequestParam("jurisdictionUText") Long jurisdictionId,@RequestParam("formType") String formType){
		log.debug("--> uploadFileData:");
		UploadResponse response = new UploadResponse();
		QamEnvironmentChangeForm qamEnvironmentChangeForm = new QamEnvironmentChangeForm();
		try {
			Date currentDateTime = new Date();
			String currentDateString = radServicesUtilityFunctions.convertToStringFromDate(currentDateTime);
			Date finalDate = radServicesUtilityFunctions.convertToDateFromString(currentDateString);
			RadUser radUser = radUserService.findById(userId);
			
			qamEnvironmentChangeForm.setMacLookupId(macId);
			qamEnvironmentChangeForm.setJurisdictionId(jurisdictionId);
			qamEnvironmentChangeForm.setDocumentName(uploadedFile.getOriginalFilename());
			qamEnvironmentChangeForm.setDescription("");
			qamEnvironmentChangeForm.setFormType(formType);
			qamEnvironmentChangeForm.setFileType(uploadedFile.getContentType());
			qamEnvironmentChangeForm.setDocumentContent(uploadedFile.getBytes());
			qamEnvironmentChangeForm.setUserId(userId);
			qamEnvironmentChangeForm.setRecordStatus(GenericConstants.RECORD_STATUS_ACTIVE);
			qamEnvironmentChangeForm.setCreatedBy(radUser.getUserName());
			qamEnvironmentChangeForm.setCreatedDate(finalDate);
			qamEnvironmentChangeForm.setUpdatedBy(radUser.getUserName());
			qamEnvironmentChangeForm.setUpdateddDate(finalDate);
			qamEnvironmentChangeForm = qamEnvironmentChangeFormService.createQamEnvironmentChangeForm(qamEnvironmentChangeForm);
			response.setStatus("File Uploaded Succesfully");
		} catch(Exception e) {
			log.error("Error while uploading QamEnvironmentChangeForm",e);
			response.setStatus("ERROR");
			response.setErroMessage(e.getMessage());
		}
		
		log.debug("<-- uploadFileData");
		return response;
	}
	
	
	
	@RequestMapping(value = "/qamEnvListMonths", method = RequestMethod.GET)
	public List<Object[]> getQamEnvListMonths(@RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("macIdS") String macLookupIdList, @RequestParam("jurisdictionS") String jurisdictionList){
		log.debug("--> getQamEnvListMonths:");
		List<Object[] > finalDataSet = null;
		
		List<Object[] > data = qamEnvironmentChangeFormService.getQamListMonths(from, to, macLookupIdList.substring(1,macLookupIdList.length()-1), jurisdictionList.substring(1,jurisdictionList.length()-1));
		if(data == null || data.size() == 0) {
			data = new ArrayList();
		} else {
			HashMap <Long, String> macIdHashMap = new HashMap<Long, String>();
			List<MacLookup> macData = macLookupService.findAll();
			for(MacLookup macLookup: macData) {
				macIdHashMap.put(macLookup.getId(), macLookup.getMacName());
			}
			
			HashMap <Long, String> jurisdictionHashMap = new HashMap<Long, String>();
			List<Jurisdiction> jurisdictionData = jurisdictionService.findAll();
			for(Jurisdiction jurisdiction: jurisdictionData) {
				jurisdictionHashMap.put(jurisdiction.getId(), jurisdiction.getJurisdictionName());
			}
			finalDataSet = new ArrayList();
			int index = 0;
			for(Object[] testObject: data) {
				String macName = macIdHashMap.get(testObject[2]);
				String jurisdictionName = jurisdictionHashMap.get(testObject[3]);
				testObject[2] = macName;
				testObject[3] = jurisdictionName;
				finalDataSet.add(testObject);
				index ++;
			}
		}
		log.debug("<-- getQamEnvListMonths");
		return finalDataSet;
	}	
	
	
	 /*@RequestMapping(value = "/download-document")
	    //public HttpServletResponse  downloadDocument(@RequestParam("docId") Long docId, HttpServletResponse response) throws IOException {
	 public HttpServletResponse  downloadDocument(@RequestParam("docId") Long docId, HttpServletResponse response) throws IOException {
		
		 try {
			 QamEnvironmentChangeForm qamEnvironmentChangeForm = qamEnvironmentChangeFormService.getQamEnvironmentChangeForm(docId);
		 	 //UserDocument document = userDocumentService.findById(docId);
		     response.setContentType(qamEnvironmentChangeForm.getType());
		     response.setContentLength(qamEnvironmentChangeForm.getDocumentContent().length);
		     response.setHeader("Content-Disposition","attachment; filename=\"" + qamEnvironmentChangeForm.getDocumentName() +".xlsx\"");
		     //InputStream targetStream = new ByteArrayInputStream(qamEnvironmentChangeForm.getDocumentContent());
		     FileCopyUtils.copy(qamEnvironmentChangeForm.getDocumentContent(), response.getOutputStream());
			 response.getOutputStream().flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		 return response;
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
	 
	 
	 
	 @RequestMapping(value = "/qamEnvFormList", method = RequestMethod.GET)
		public List<CsrLists> getQamEnvFormList(@RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("macIdS") String macLookupIdList, @RequestParam("jurisdictionS") String jurisdictionList){
			log.debug("--> getQamEnvFormList:");
			List<CsrLists> finalList = new ArrayList<CsrLists>();
			
			log.debug("<-- getQamEnvFormList");
			return finalList;
	}	
	 
	 @RequestMapping(value = "/findEnvironmentalChangeControlForm",method = RequestMethod.POST)
		public @ResponseBody QamEnvironmentChangeForm findUser(@RequestBody QamEnvironmentChangeForm qamEnvironmentChangeForm ){
			log.debug("--> findEnvironmentalChangeControlForm:");
			QamEnvironmentChangeForm qamEnvironmentChangeFormTemp = null;
			if(qamEnvironmentChangeForm.getQamEnvironmentChangeFormId() != null) {
				qamEnvironmentChangeFormTemp = qamEnvironmentChangeFormService.getQamEnvironmentChangeForm(qamEnvironmentChangeForm.getQamEnvironmentChangeFormId());		
			}
			
			return qamEnvironmentChangeFormTemp;
		}
		
	
}
