/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.qam.model.Jurisdiction;
import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.model.MacProgJurisPccMapping;
import com.archsystemsinc.qam.model.ProgramLookup;
import com.archsystemsinc.qam.service.JurisdictionService;
import com.archsystemsinc.qam.service.MacLookupService;
import com.archsystemsinc.qam.service.MacProgJurisPccMappingService;
import com.archsystemsinc.qam.service.ProgramLookupService;
	
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
	private MacProgJurisPccMappingService macProgJurisPccMappingService;
	
	@Autowired
	private JurisdictionService jurisdictionService;
	
	@Autowired
	private ProgramLookupService programLookupService;
	
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
	
	@RequestMapping(value = "/macIdMap", method = RequestMethod.GET)
	public HashMap<Integer,String> getMACIdMap(){
		log.debug("--> getMACIdMap:");
		HashMap<Integer, String> macIdHashMap = new HashMap<Integer, String> ();
		List<MacLookup> data = macLookupService.findAll();
		for(MacLookup macLookup: data) {
			macIdHashMap.put(macLookup.getId().intValue(), macLookup.getMacName());
		}
		log.debug("<-- getMACIdMap");
		return macIdHashMap;
	}
	
	@RequestMapping(value = "/jurisdictionMap", method = RequestMethod.GET)
	public HashMap<Integer,String> getJurisdictionMap(){
		log.debug("--> getJurisdictionMap:");
		List<Jurisdiction> data = jurisdictionService.findAll();
		HashMap<Integer, String> jurisdictionHashMap = new HashMap<Integer, String> ();
		for(Jurisdiction jurisdiction: data) {
			jurisdictionHashMap.put(jurisdiction.getId().intValue(), jurisdiction.getJurisdictionName());
		}
		log.debug("<-- getJurisdictionMap");
		return jurisdictionHashMap;
	}
	
	@RequestMapping(value = "/programMap", method = RequestMethod.GET)
	public HashMap<Integer,String> getProgramList(){
		log.debug("--> getProgramList:");
		HashMap<Integer, String> programHashMap = new HashMap<Integer, String> ();
		List<ProgramLookup> data = programLookupService.findAll();
		
		for(ProgramLookup programLookup: data) {
			programHashMap.put(programLookup.getId(), programLookup.getProgramName());
		}
		log.debug("<-- getProgramList");
		return programHashMap;
	}
	
	@RequestMapping(value = "/macPrgmJurisPccList", method = RequestMethod.GET)
	public List<MacProgJurisPccMapping> getMacPrgmJurisPccList(){
		log.debug("--> getMacPrgmJurisPccList:");
		List<MacProgJurisPccMapping> data = macProgJurisPccMappingService.findAll();		
		log.debug("<-- getMacPrgmJurisPccList");
		return data;
	}
	
}
