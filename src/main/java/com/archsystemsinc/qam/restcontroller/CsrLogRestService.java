/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.service.CsrLogService;
import com.archsystemsinc.qam.utils.EmailObject;
	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class CsrLogRestService {
	private static final Logger log = Logger.getLogger(CsrLogRestService.class);
	
	@Autowired
	private CsrLogService csrLogService;
	
	@RequestMapping(value = "/csrloglist", method = RequestMethod.POST)
	public List<CsrLog> getCsrLogList(@RequestBody CsrLog csrLog){
		List<CsrLog> data=null;
		try {
			log.debug("--> getCsrLogList:");
			
			data = csrLogService.search(csrLog);
			log.debug("<-- getCsrLogList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}	
	
	@RequestMapping(value = "/saveOrUpdateCsrLog", method = RequestMethod.POST)
	public @ResponseBody CsrLog saveOrUpdateCsrLog(@RequestBody  CsrLog csrLog){
		log.debug("--> saveOrUpdateCsrLog:");		
		
		boolean newCsrLog = false;
		EmailObject emailObject = null;
		CsrLog csrLogResult = null;
		try {
			if (csrLog.getId() == 0) {
				newCsrLog = true;
			}
			csrLogResult = csrLogService.saveOrUpdateCsrLog(csrLog);
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return csrLogResult;			
		}
		log.debug("<-- saveOrUpdateCsrLog");
		return csrLogResult;
	}
}
