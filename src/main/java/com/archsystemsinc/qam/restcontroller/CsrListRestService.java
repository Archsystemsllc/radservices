/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	@RequestMapping(value = "/uploadCsrList", method = RequestMethod.POST)
	public UploadResponse uploadFileData(@RequestParam("file") MultipartFile uploadedFile,@RequestParam("userId") Long userId){
		log.debug("--> uploadFileData:");
		UploadResponse response = new UploadResponse();
		
		try {
			csrListService.uploadFileData(uploadedFile,userId,null);
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			response.setStatus("ERROR");
			response.setErroMessage(e.getMessage());
		}
		log.debug("<-- uploadFileData");
		return response;
	}
	
	@RequestMapping(value = "/keepCurrentList", method = RequestMethod.POST)
	public UploadResponse keepCurrentList(@RequestParam("userId") Long userId ){
		log.debug("--> keepCurrentList:");
		UploadResponse response = new UploadResponse();
		String keepCurrentList = "true";
		try {
			csrListService.uploadFileData(null,userId,keepCurrentList);
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			response.setStatus("ERROR");
			response.setErroMessage(e.getMessage());
		}
		log.debug("<-- keepCurrentList");
		return response;
	}
	
	@RequestMapping(value = "/csrList", method = RequestMethod.GET)
	public List<CsrLists> getCsrList(@RequestParam("fromDate") String from, @RequestParam("toDate") String to){
		log.debug("--> getCsrList:");
		List<CsrLists> data = csrListService.getCsrList(from, to);
		log.debug("<-- getCsrList");
		return data;
	}	
	
	@RequestMapping(value = "/csrListMonths", method = RequestMethod.GET)
	public List<Object[]> getCsrListMonths(@RequestParam("fromDate") String from, @RequestParam("toDate") String to){
		log.debug("--> getCsrListMonths:");
		List<Object[]> data = csrListService.getCsrListMonths(from, to);
		log.debug("<-- getCsrListMonths");
		return data;
	}	
	
	
	
}
