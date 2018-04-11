/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.cmts.sec.util.GenericConstants;
import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.model.QamEnvironmentChangeForm;
import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.service.CsrListService;
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
	private RadUserService radUserService;
	
	/*@RequestMapping(value = "/keepCurrentList", method = RequestMethod.GET)
	public UploadResponse keepCurrentList(@RequestParam("userId") Long userId,@RequestParam("macIdK") Long macId,@RequestParam("jurisdictionK") String jurisdiction){
		log.debug("--> keepCurrentList:");
		UploadResponse response = new UploadResponse();
		String keepCurrentList = "true";
		String statusString = "";
		
		try {
			statusString = csrListService.uploadFileData(null,userId,keepCurrentList,macId, jurisdiction);
			if(statusString.equalsIgnoreCase("")) {
				response.setStatus("SUCCESS");
			} else {
				response.setStatus("ERROR");			
				response.setErroMessage(statusString);
			}
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			response.setStatus("ERROR");
			response.setErroMessage(e.getMessage());
		}
		log.debug("<-- keepCurrentList");
		return response;
	}*/
	
	@RequestMapping(value = "/uploadQamEnvForm", method = RequestMethod.POST)
	public UploadResponse uploadFileData(@RequestParam("file") MultipartFile uploadedFile,@RequestParam("userId") Long userId,@RequestParam("macIdU") Long macId,@RequestParam("jurisdictionUText") Long jurisdictionId){
		log.debug("--> uploadFileData:");
		UploadResponse response = new UploadResponse();
		QamEnvironmentChangeForm qamEnvironmentChangeForm = new QamEnvironmentChangeForm();
		try {
			RadUser radUser = radUserService.findById(userId);
			
			qamEnvironmentChangeForm.setMacLookupId(macId);
			qamEnvironmentChangeForm.setJurisdictionId(jurisdictionId);
			qamEnvironmentChangeForm.setDocumentName(uploadedFile.getName());
			qamEnvironmentChangeForm.setDescription("");
			qamEnvironmentChangeForm.setType(uploadedFile.getContentType());
			qamEnvironmentChangeForm.setDocumentContent(uploadedFile.getBytes());
			qamEnvironmentChangeForm.setUserId(userId);
			qamEnvironmentChangeForm.setRecordStatus(GenericConstants.RECORD_STATUS_ACTIVE);
			qamEnvironmentChangeForm.setCreatedBy(radUser.getUserName());
			qamEnvironmentChangeForm.setCreatedDate(new Date());
			qamEnvironmentChangeForm.setUpdatedBy(radUser.getUserName());
			qamEnvironmentChangeForm.setUpdateddDate(new Date());
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
	
	@RequestMapping(value = "/qamEnvFormList", method = RequestMethod.GET)
	public List<CsrLists> getQamEnvFormList(@RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("macIdS") String macLookupIdList, @RequestParam("jurisdictionS") String jurisdictionList){
		log.debug("--> getQamEnvFormList:");
		List<CsrLists> finalList = new ArrayList<CsrLists>();
		/*List<CsrLists> data = csrListService.getCsrList(from, to, macLookupIdList.substring(1,macLookupIdList.length()-1), jurisdictionList.substring(1,jurisdictionList.length()-1));
		
		if(data!=null || data.size()>0) {
			Collections.sort(data);
		} 
		log.debug("<-- getQamEnvFormList");
		
		HashMap <Long, String> macIdHashMap = new HashMap<Long, String>();
		List<MacLookup> macData = macLookupService.findAll();
		for(MacLookup macLookup: macData) {
			macIdHashMap.put(macLookup.getId(), macLookup.getMacName());
		}
		
		List<CsrLists> finalList = new ArrayList<CsrLists>();
		for (CsrLists csrListTemp: data) {
			csrListTemp.setMacName(macIdHashMap.get(csrListTemp.getMacLookupId()));
			finalList.add(csrListTemp);
		}*/
		log.debug("<-- getQamEnvFormList");
		return finalList;
	}	
	
	@RequestMapping(value = "/qamEnvListMonths", method = RequestMethod.GET)
	public List<QamEnvironmentChangeForm> getQamEnvListMonths(@RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("macIdS") String macLookupIdList, @RequestParam("jurisdictionS") String jurisdictionList){
		log.debug("--> getQamEnvListMonths:");
		//CsrLists testCsr = new CsrLists();
		//testCsr.setMacLookupId(2l);
		//testCsr.setCreatedDate(new Date());
		//List<CsrLists> testData = csrListService.search(testCsr);
		List<QamEnvironmentChangeForm > data = qamEnvironmentChangeFormService.getQamEnvList(from, to, macLookupIdList.substring(1,macLookupIdList.length()-1), jurisdictionList.substring(1,jurisdictionList.length()-1));
		if(data == null || data.size() == 0) {
			data = new ArrayList();
		}
		log.debug("<-- getQamEnvListMonths");
		return data;
	}	
	
	/*
	@RequestMapping(value = "/csrListNames", method = RequestMethod.GET)
	public List<CsrLists> getCsrListNames(@RequestParam("term")  String csrLName,@RequestParam("macIdS") String macLookupId, @RequestParam("jurisdictionS") String jurisdiction, @RequestParam("programS") String program) {
		
		macLookupId = macLookupId.toLowerCase();
		List<CsrLists> data = csrListService.getCsrNames(csrLName, Long.valueOf(macLookupId), jurisdiction,program);			
		HashMap<String, CsrLists> csrListUniqMap = new HashMap<String, CsrLists> ();
		for(CsrLists csrListTemp: data) {
			
			csrListUniqMap.put(csrListTemp.getFirstName()+"_"+csrListTemp.getLastName(),csrListTemp);
		}
		
		
		Collection<CsrLists> values = csrListUniqMap.values();
        
		//Creating an ArrayList of values
		         
		ArrayList<CsrLists> finalNameData = new ArrayList<CsrLists>(values);
		
		
		if(finalNameData==null || finalNameData.size()==0) {
			finalNameData = new ArrayList<CsrLists>();
			CsrLists csrListTemp= new CsrLists();
			csrListTemp.setFirstName("No CSR's Found");
			csrListTemp.setMiddleName("");
			csrListTemp.setLastName("");
			csrListTemp.setLevel("");
			finalNameData.add(csrListTemp);
		} else {
			Collections.sort(finalNameData);
		}
		
		return finalNameData;
	}*/
}
