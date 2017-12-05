/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

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
 * @author Abdul Nissar S
 *
 */
@RestController
@RequestMapping("api")
public class ReferenceRestService {
	private static final Logger log = Logger.getLogger(ReferenceRestService.class);
	
	@Autowired
	private CsrListService csrListService;
	
	@RequestMapping(value = "/macList", method = RequestMethod.GET)
	public List<CsrLists> getMACList(){
		log.debug("--> getCsrList:");
		List<CsrLists> data = null;
		log.debug("<-- getCsrList");
		return data;
	}
	
}
