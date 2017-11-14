/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.service.CsrListService;
	
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
	public String uploadFileData(@RequestParam("file") MultipartFile uploadedFile){
		log.debug("--> uploadFileData:");
		csrListService.uploadFileData(uploadedFile);
		log.debug("<-- uploadFileData");
		return "SUCCESS";
	}
	
	
	
}
