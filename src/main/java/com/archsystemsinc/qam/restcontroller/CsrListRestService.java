/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.service.CsrListService;
import com.archsystemsinc.qam.utils.UploadResponse;
	
	/**
 * @author Prakash T
 *
 */
@RestController
@RequestMapping("api")
public class CsrListRestService {
	private static final Logger log = Logger.getLogger(CsrListRestService.class);
	
	@Autowired
	private CsrListService csrListService;
	
	@RequestMapping(value = "/keepCurrentList", method = RequestMethod.GET)
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
	}
	
	@RequestMapping(value = "/uploadCsrList", method = RequestMethod.POST)
	public UploadResponse uploadFileData(@RequestParam("file") MultipartFile uploadedFile,@RequestParam("userId") Long userId,@RequestParam("macIdU") Long macId,@RequestParam("jurisdictionU") String jurisdictionList){
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
	
	@RequestMapping(value = "/csrList", method = RequestMethod.GET)
	public List<CsrLists> getCsrList(@RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("macIdS") String macLookupIdList, @RequestParam("jurisdictionS") String jurisdictionList){
		log.debug("--> getCsrList:");
		List<CsrLists> data = csrListService.getCsrList(from, to, macLookupIdList.substring(1,macLookupIdList.length()-1), jurisdictionList.substring(1,jurisdictionList.length()-1));
		log.debug("<-- getCsrList");
		
		return data;
	}	
	
	@RequestMapping(value = "/csrListMonths", method = RequestMethod.GET)
	public List<Object[]> getCsrListMonths(@RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("macIdS") String macLookupIdList, @RequestParam("jurisdictionS") String jurisdictionList){
		log.debug("--> getCsrListMonths:");
		List<Object[]> data = csrListService.getCsrListMonths(from, to, macLookupIdList.substring(1,macLookupIdList.length()-1), jurisdictionList.substring(1,jurisdictionList.length()-1));
		log.debug("<-- getCsrListMonths");
		return data;
	}	
	
	
	@RequestMapping(value = "/csrListNames", method = RequestMethod.GET)
	public List<CsrLists> getCsrListNames(@RequestParam("term")  String csrLName,@RequestParam("macIdS") String macLookupId, @RequestParam("jurisdictionS") String jurisdiction, @RequestParam("programS") String program) {
		
		macLookupId = macLookupId.toLowerCase();
		List<CsrLists> data = csrListService.getCsrNames(csrLName, Long.valueOf(macLookupId), jurisdiction,program);			
		if(data==null || data.size()==0) {
			data = new ArrayList<CsrLists>();
			CsrLists csrListTemp= new CsrLists();
			csrListTemp.setFirstName("No CSR's Found");
			data.add(csrListTemp);
		}		
		return data;
	}
}
