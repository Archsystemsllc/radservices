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

import com.archsystemsinc.qam.model.Jurisdiction;
import com.archsystemsinc.qam.service.JurisdictionService;
import com.archsystemsinc.qam.service.mail.MailService;

	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class JurisdictionRestService {
	private static final Logger log = Logger.getLogger(JurisdictionRestService.class);
	
	@Autowired
	private JurisdictionService jurisdictionService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	@RequestMapping(value = "/jurisdictionList", method = RequestMethod.POST)
	public List<Jurisdiction> getJurisdictionList(@RequestBody Jurisdiction jurisdictionSearchObject){
		List<Jurisdiction> data=null;
		
		try {
			log.debug("--> getJurisdictionList:");
			
			data = jurisdictionService.findAll();
			log.debug("<-- getJurisdictionList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	
								
	@RequestMapping(value = "/saveOrUpdateJurisdiction", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateJurisdiction(
			@RequestBody Jurisdiction jurisdictionObject){
		log.debug("--> saveOrUpdateJurisdiction:");		
		
		try {
			
			jurisdictionService.saveOrUpdateJurisdiction(jurisdictionObject);
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return "Failure";
			
			
		}
		log.debug("<-- saveOrUpdateJurisdiction");
		return "Success";
	}	
	
	@RequestMapping(value = "/searchJurisdiction", method = RequestMethod.POST)
	public @ResponseBody Jurisdiction searchJurisdiction(@RequestBody  Jurisdiction jurisdiction){
		
		try {
			log.debug("--> searchScoreCard:");
			
			jurisdiction = jurisdictionService.findById(jurisdiction.getId());
					
			log.debug("<-- searchScoreCard");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jurisdiction;
	}		
}