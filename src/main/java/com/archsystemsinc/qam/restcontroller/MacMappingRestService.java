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
import com.archsystemsinc.qam.model.MacProgJurisPccMapping;
import com.archsystemsinc.qam.service.MacLookupService;
import com.archsystemsinc.qam.service.MacProgJurisPccMappingService;
import com.archsystemsinc.qam.service.mail.MailService;

	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class MacMappingRestService {
	private static final Logger log = Logger.getLogger(MacMappingRestService.class);
	
	@Autowired
	private MacProgJurisPccMappingService macProgJurisPccMappingService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	@RequestMapping(value = "/macProgJurisPccMappingList", method = RequestMethod.POST)
	public List<MacProgJurisPccMapping> getMacProgJurisPccMappingList(@RequestBody MacLookup macLookupSearchObject){
		List<MacProgJurisPccMapping> data=null;
		
		try {
			log.debug("--> getMacProgJurisPccMappingList:");
			
			data = macProgJurisPccMappingService.findAll();
			log.debug("<-- getMacProgJurisPccMappingList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	
	
	@RequestMapping(value = "/saveOrUpdateMacProgJurisPccMapping", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateMacProgJurisPccMapping(
			@RequestBody MacProgJurisPccMapping macProgJurisPccMapping){
		log.debug("--> saveOrUpdateMacAssignmentObject:");		
		
		try {
			
			macProgJurisPccMappingService.saveOrUpdateMacLookup(macProgJurisPccMapping);
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return "Failure";
			
			
		}
		log.debug("<-- saveOrUpdateMacAssignmentObject");
		return "Success";
	}	
	
	@RequestMapping(value = "/searchMacProgJurisPccMapping", method = RequestMethod.POST)
	public @ResponseBody MacProgJurisPccMapping searchMacProgJurisPccMapping(@RequestBody  Integer id){
		MacProgJurisPccMapping macProgJurisPccMapping = null;
		try {
			log.debug("--> searchScoreCard:");
			
			macProgJurisPccMapping = macProgJurisPccMappingService.findById(id);
					
			log.debug("<-- searchScoreCard");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return macProgJurisPccMapping;
	}		
}