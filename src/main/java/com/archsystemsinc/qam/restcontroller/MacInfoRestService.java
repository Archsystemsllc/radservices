/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.service.MacLookupService;
import com.archsystemsinc.qam.service.mail.MailService;

	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class MacInfoRestService {
	private static final Logger log = Logger.getLogger(MacInfoRestService.class);
	
	@Autowired
	private MacLookupService macLookupService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	@RequestMapping(value = "/macInfoList", method = RequestMethod.POST)
	public List<MacLookup> getMacInfoList(@RequestBody MacLookup macLookupSearchObject){
		List<MacLookup> data=null;
		
		try {
			log.debug("--> getMacInfoList:");
			
			data = macLookupService.findAll();
			log.debug("<-- getMacInfoList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	
	
	@RequestMapping(value = "/saveOrUpdateMacInfo", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateMacInfo(
			@RequestBody MacLookup macLookupObject){
		log.debug("--> saveOrUpdateMacAssignmentObject:");		
		//Rebuttal rebuttal = new Rebuttal();
		
		boolean newMacAssignmentObject = false;
		
		try {
			
			macLookupService.saveOrUpdateMacLookup(macLookupObject);
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return "Failure";
			
			
		}
		log.debug("<-- saveOrUpdateMacAssignmentObject");
		return "Success";
	}	
	
	@RequestMapping(value = "/searchMacInfo", method = RequestMethod.POST)
	public @ResponseBody MacLookup searchMacInfo(@RequestBody  MacLookup macLookup){
		
		try {
			log.debug("--> searchScoreCard:");
			
			macLookup = macLookupService.findById(macLookup.getId());
					
			log.debug("<-- searchScoreCard");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return macLookup;
	}		
}