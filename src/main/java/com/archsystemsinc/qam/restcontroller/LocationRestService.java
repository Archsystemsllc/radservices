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

import com.archsystemsinc.qam.model.PccLocation;
import com.archsystemsinc.qam.service.PccLocationService;
import com.archsystemsinc.qam.service.mail.MailService;

	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class LocationRestService {
	private static final Logger log = Logger.getLogger(LocationRestService.class);
	
	@Autowired
	private PccLocationService locationService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	@RequestMapping(value = "/locationList", method = RequestMethod.POST)
	public List<PccLocation> getlocationList(@RequestBody PccLocation locationSearchObject){
		List<PccLocation> data=null;
		
		try {
			log.debug("--> getlocationLookupList:");
			
			data = locationService.findAll();
			log.debug("<-- getlocationLookupList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	
								
	@RequestMapping(value = "/saveOrUpdateLocation", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdatelocation(
			@RequestBody PccLocation locationObject){
		log.debug("--> saveOrUpdatelocation:");		
		
		try {
			
			locationService.saveOrUpdatePccLocation(locationObject);
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return "Failure";
			
			
		}
		log.debug("<-- saveOrUpdatelocation");
		return "Success";
	}	
	
	@RequestMapping(value = "/searchLocation", method = RequestMethod.POST)
	public @ResponseBody PccLocation searchlocation(@RequestBody  PccLocation location){
		
		try {
			log.debug("--> searchScoreCard:");
			
			location = locationService.findById(location.getId());
					
			log.debug("<-- searchScoreCard");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}		
}