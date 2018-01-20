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

import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.service.RebuttalService;

	
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
	
	
	@RequestMapping(value = "/rebuttallist", method = RequestMethod.GET)
	public List<Rebuttal> getRebuttalList(){
		List<Rebuttal> data=null;
		try {
			log.debug("--> getRebuttalList:");
			data = rebuttalService.findAll();
			log.debug("<-- getRebuttalList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	@RequestMapping(value = "/saveOrUpdateRebuttal", method = RequestMethod.POST)
	public @ResponseBody Rebuttal saveOrUpdateRebuttal(@RequestBody  Rebuttal rebuttal){
		log.debug("--> saveOrUpdateRebuttal:");		
		Rebuttal rebuttalResult = null;
		
		try {
			rebuttalResult = rebuttalService.saveOrUpdateRebuttal(rebuttal);
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return rebuttalResult;
			
		}
		log.debug("<-- saveOrUpdateRebuttal");
		return rebuttalResult;
	}	
}