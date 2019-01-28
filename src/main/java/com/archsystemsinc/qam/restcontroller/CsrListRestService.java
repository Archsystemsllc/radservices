/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.service.CsrListService;
import com.archsystemsinc.qam.service.MacLookupService;
import com.archsystemsinc.qam.utils.UploadResponse;
	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class CsrListRestService {
	private static final Logger log = Logger.getLogger(CsrListRestService.class);
	
	@Autowired
	private CsrListService csrListService;
	
	@RequestMapping(value = "/keepCurrentList", method = RequestMethod.POST)
	public UploadResponse keepCurrentList(@RequestBody CsrLists csrList){
		log.debug("--> keepCurrentList:");
		UploadResponse response = new UploadResponse();
		String keepCurrentList = "true";
		String statusString = "";
		
		try {
			statusString = csrListService.uploadFileData(null,csrList.getUserId(),keepCurrentList,csrList.getMacLookupId(), csrList.getJurisdiction());
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
	}
	
	@RequestMapping(value = "/uploadCsrList", method = RequestMethod.POST,
		    consumes = {"multipart/form-data"})
	public UploadResponse uploadFileData(@RequestParam("file") MultipartFile uploadedFile,@RequestParam("userId") Long userId,@RequestParam("macIdU") Long macId,@RequestParam("jurisdictionUText") String jurisdictionList){
		log.debug("--> uploadFileData:");
		UploadResponse response = new UploadResponse();
		
		try {
			String uploadResult=csrListService.uploadFileData(uploadedFile,userId,null,macId,jurisdictionList);
			response.setStatus(uploadResult);
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			response.setStatus("ERROR");
			response.setErroMessage(e.getMessage());
		}
		log.debug("<-- uploadFileData");
		return response;
	}
	
	@Autowired
	private MacLookupService macLookupService;
	
	@RequestMapping(value = "/csrList", method = RequestMethod.POST)
	public List<CsrLists> getCsrList(@RequestBody CsrLists csrList){
		log.debug("--> getCsrList:");
		List<CsrLists> data = csrListService.getCsrList(csrList.getCsrSearchFromDate(), csrList.getCsrSearchToDate(), 
				csrList.getMacIdString(), csrList.getJurisdiction());
		
		if(data!=null || data.size()>0) {
			Collections.sort(data);
		} 
		log.debug("<-- getCsrList");
		
		HashMap <Long, String> macIdHashMap = new HashMap<Long, String>();
		List<MacLookup> macData = macLookupService.findAll();
		for(MacLookup macLookup: macData) {
			macIdHashMap.put(macLookup.getId(), macLookup.getMacName());
		}
		
		List<CsrLists> finalList = new ArrayList<CsrLists>();
		for (CsrLists csrListTemp: data) {
			csrListTemp.setMacName(macIdHashMap.get(csrListTemp.getMacLookupId()));
			finalList.add(csrListTemp);
		}
		
		return finalList;
	}	
	
	@RequestMapping(value = "/csrListMonths", method = RequestMethod.POST)
	public List<Object[]> getCsrListMonths(@RequestBody CsrLists csrList){
		log.debug("--> getCsrListMonths:");
		
		List<Object[]> data = csrListService.getCsrListMonths(csrList.getCsrSearchFromDate(), csrList.getCsrSearchToDate(), 
				csrList.getMacIdString(), csrList.getJurisdiction());
		if(data == null || data.size() == 0) {
			data = new ArrayList();
		}
		log.debug("<-- getCsrListMonths");
		return data;
	}	
	
	
	@RequestMapping(value = "/csrListNames", method = RequestMethod.POST)
	public List<CsrLists> getCsrListNames(@RequestBody CsrLists csrList) {
		
		//macLookupId = macLookupId.toLowerCase();
		List<CsrLists> data = csrListService.getCsrNames(csrList.getSearchStringLiteral(), csrList.getMacLookupId(), 
				csrList.getJurisdiction(),csrList.getProgram());			
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
	}
}
