/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.Jurisdiction;
import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.model.SystemIssueForm;
import com.archsystemsinc.qam.sec.util.GenericConstants;
import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.service.JurisdictionService;
import com.archsystemsinc.qam.service.MacLookupService;
import com.archsystemsinc.qam.service.SystemIssueFormService;
import com.archsystemsinc.qam.service.RadUserService;
import com.archsystemsinc.qam.utils.UploadResponse;
	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class SystemIssueFormRestService {
	private static final Logger log = Logger.getLogger(SystemIssueFormRestService.class);
	
	@Autowired
	private SystemIssueFormService systemIssueFormService;
	
	@Autowired
	private MacLookupService macLookupService;
	
	@Autowired
	private JurisdictionService jurisdictionService;
	
	@Autowired
	private RadUserService radUserService;
	
	
	@RequestMapping(value = "/uploadSystemIssueForm", method = RequestMethod.POST)
	public UploadResponse uploadSystemIssueFileData(@RequestParam("file") MultipartFile uploadedFile,@RequestParam("userId") Long userId,@RequestParam("macIdU") Long macId,@RequestParam("jurisdictionUText") Long jurisdictionId){
		log.debug("--> uploadSystemIssueFileData:");
		UploadResponse response = new UploadResponse();
		SystemIssueForm SystemIssueForm = new SystemIssueForm();
		try {
			RadUser radUser = radUserService.findById(userId);
			
			SystemIssueForm.setMacLookupId(macId);
			SystemIssueForm.setJurisdictionId(jurisdictionId);
			SystemIssueForm.setDocumentName(uploadedFile.getName());
			SystemIssueForm.setDescription("");
			SystemIssueForm.setType(uploadedFile.getContentType());
			SystemIssueForm.setDocumentContent(uploadedFile.getBytes());
			SystemIssueForm.setUserId(userId);
			SystemIssueForm.setRecordStatus(GenericConstants.RECORD_STATUS_ACTIVE);
			SystemIssueForm.setCreatedBy(radUser.getUserName());
			SystemIssueForm.setCreatedDate(new Date());
			SystemIssueForm.setUpdatedBy(radUser.getUserName());
			SystemIssueForm.setUpdateddDate(new Date());
			SystemIssueForm = systemIssueFormService.createSystemIssueForm(SystemIssueForm);
			response.setStatus("File Uploaded Succesfully");
		} catch(Exception e) {
			log.error("Error while uploading SystemIssueForm",e);
			response.setStatus("ERROR");
			response.setErroMessage(e.getMessage());
		}
		
		log.debug("<-- uploadSystemIssueFileData");
		return response;
	}
	
	
	
	@RequestMapping(value = "/systemIssueListMonths", method = RequestMethod.GET)
	public List<Object[]> getQamEnvListMonths(@RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("macIdS") String macLookupIdList, @RequestParam("jurisdictionS") String jurisdictionList){
		log.debug("--> getQamEnvListMonths:");
		List<Object[] > finalDataSet = null;
		
		List<Object[] > data = systemIssueFormService.getQamListMonths(from, to, macLookupIdList.substring(1,macLookupIdList.length()-1), jurisdictionList.substring(1,jurisdictionList.length()-1));
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
	
	
	 @RequestMapping(value = { "/download-systemIssue-document/{docId}" }, method = RequestMethod.GET)
	    public void downloadDocument(@PathVariable Long docId, HttpServletResponse response) throws IOException {
		 try {
			 SystemIssueForm SystemIssueForm = systemIssueFormService.getSystemIssueForm(docId);
		 	 //UserDocument document = userDocumentService.findById(docId);
		     response.setContentType(SystemIssueForm.getType());
		     response.setContentLength(SystemIssueForm.getDocumentContent().length);
		     response.setHeader("Content-Disposition","attachment; filename=\"" + SystemIssueForm.getDocumentName() +"\"");
  
			 FileCopyUtils.copy(SystemIssueForm.getDocumentContent(), response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	       
	 }
	 
	 
	 @RequestMapping(value = "/systemIssueFormList", method = RequestMethod.GET)
		public List<CsrLists> getQamEnvFormList(@RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("macIdS") String macLookupIdList, @RequestParam("jurisdictionS") String jurisdictionList){
			log.debug("--> getQamEnvFormList:");
			List<CsrLists> finalList = new ArrayList<CsrLists>();
			
			log.debug("<-- getQamEnvFormList");
			return finalList;
		}	
	
}
