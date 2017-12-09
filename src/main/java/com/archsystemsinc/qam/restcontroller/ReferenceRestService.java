/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.qam.model.Jurisdiction;
import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.service.JurisdictionService;
import com.archsystemsinc.qam.service.MacLookupService;
	
	/**
 * @author Abdul Nissar S
 *
 */
@RestController
@RequestMapping("api")
public class ReferenceRestService {
	private static final Logger log = Logger.getLogger(ReferenceRestService.class);
	
	@Autowired
	private MacLookupService macLookupService;
	
	@Autowired
	private JurisdictionService jurisdictionService;
	
	@RequestMapping(value = "/macList", method = RequestMethod.GET)
	public List<MacLookup> getMACList(){
		log.debug("--> getMacLookupList:");
		List<MacLookup> data = macLookupService.findAll();
		log.debug("<-- getMacLookupList");
		return data;
	}
	
	@RequestMapping(value = "/jurisdictionList", method = RequestMethod.GET)
	public List<Jurisdiction> getJurisdictionList(){
		log.debug("--> getJurisdictionList:");
		List<Jurisdiction> data = jurisdictionService.findAll();
		log.debug("<-- getJurisdictionList");
		return data;
	}
	
}
