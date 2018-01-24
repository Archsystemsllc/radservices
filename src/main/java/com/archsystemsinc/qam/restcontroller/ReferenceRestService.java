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

import com.archsystemsinc.qam.model.CategoryLookup;
import com.archsystemsinc.qam.model.Jurisdiction;
import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.model.MacProgJurisPccMapping;
import com.archsystemsinc.qam.model.OrganizationLookup;
import com.archsystemsinc.qam.model.PccLocation;
import com.archsystemsinc.qam.model.ProgramLookup;
import com.archsystemsinc.qam.model.Role;
import com.archsystemsinc.qam.model.SubcategoriesLookup;
import com.archsystemsinc.qam.service.CategoryLookupService;
import com.archsystemsinc.qam.service.JurisdictionService;
import com.archsystemsinc.qam.service.MacLookupService;
import com.archsystemsinc.qam.service.MacProgJurisPccMappingService;
import com.archsystemsinc.qam.service.OrganizationLookupService;
import com.archsystemsinc.qam.service.PccLocationService;
import com.archsystemsinc.qam.service.ProgramLookupService;
import com.archsystemsinc.qam.service.RadUserService;
import com.archsystemsinc.qam.service.SubcategoriesLookupService;
	
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
	
	@Autowired
	private OrganizationLookupService organizationLookupService;
	
	@Autowired
	private RadUserService radUserService;	
	
	@Autowired
	private PccLocationService pccLocationService;
	
	@Autowired
	private CategoryLookupService categoryLookupService;
	
	@Autowired
	private SubcategoriesLookupService subcategoriesLookupService;
	
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
	
	@RequestMapping(value = "/pccLocationMap", method = RequestMethod.GET)
	public HashMap<Integer,String> getPccLocationList(){
		log.debug("--> getPccLocationList:");
		HashMap<Integer, String> pccLocationHashMap = new HashMap<Integer, String> ();
		List<PccLocation> data = pccLocationService.findAll();
		
		for(PccLocation pccLocation: data) {
			pccLocationHashMap.put(pccLocation.getId(), pccLocation.getPccLocationName());
		}
		log.debug("<-- getPccLocationList");
		return pccLocationHashMap;
	}
	
	@RequestMapping(value = "/orgMap", method = RequestMethod.GET)
	public HashMap<Integer,String> getOrgMap(){
		log.debug("--> getOrgMap:");
		HashMap<Integer, String> orgHashMap = new HashMap<Integer, String> ();
		List<OrganizationLookup> data = organizationLookupService.findAll();
		
		for(OrganizationLookup org: data) {
			orgHashMap.put(org.getId(), org.getOrganizationName());
		}
		log.debug("<-- getOrgMap");
		return orgHashMap;
	}
	
	@RequestMapping(value = "/roleMap", method = RequestMethod.GET)
	public HashMap<Integer,String> getRoleMap(){
		log.debug("--> getRoleMap:");
		HashMap<Integer, String> roleHashMap = new HashMap<Integer, String> ();
		List<Role> data = radUserService.listRoles();
		
		for(Role role: data) {
			roleHashMap.put(role.getId().intValue(), role.getRoleName());
		}
		log.debug("<-- getRoleMap");
		return roleHashMap;
	}
	
	@RequestMapping(value = "/macPrgmJurisPccList", method = RequestMethod.GET)
	public List<MacProgJurisPccMapping> getMacPrgmJurisPccList(){
		log.debug("--> getMacPrgmJurisPccList:");
		List<MacProgJurisPccMapping> data = macProgJurisPccMappingService.findAll();		
		log.debug("<-- getMacPrgmJurisPccList");
		return data;
	}
	
	@RequestMapping(value = "/callCategoryMap", method = RequestMethod.GET)
	public HashMap<Integer,String> getCallCategoryMap(){
		log.debug("--> getCallCategoryMap:");
		HashMap<Integer, String> callCategoryHashMap = new HashMap<Integer, String> ();
		List<CategoryLookup> data = categoryLookupService.findAll();
		for(CategoryLookup categoryLookup: data) {
			callCategoryHashMap.put(categoryLookup.getId().intValue(), categoryLookup.getCategoryName());
		}
		log.debug("<-- getCallCategoryMap");
		return callCategoryHashMap;
	}
	
	@RequestMapping(value = "/callSubcategoriesMap", method = RequestMethod.GET)
	public HashMap<Integer,HashMap<Integer,String>> getCallSubcategoriesMap(){
		log.debug("--> getCallSubcategoriesMap:");
		HashMap<Integer,HashMap<Integer,String>> callSubcategoriesHashMap = new HashMap<Integer,HashMap<Integer,String>>();
		List<SubcategoriesLookup> data = subcategoriesLookupService.findAll();
		
		for(SubcategoriesLookup subcategoriesLookup: data) {
			String subcategory = subcategoriesLookup.getSubCategoryName();
			HashMap<Integer,String> subCategoryMap = callSubcategoriesHashMap.get(subcategoriesLookup.getCategoryId());
			if (subCategoryMap == null) {
				subCategoryMap = new HashMap<Integer,String>();
				subCategoryMap.put(subcategoriesLookup.getId(), subcategory);
			} else {
				subCategoryMap.put(subcategoriesLookup.getId(), subcategory);
			}			
			callSubcategoriesHashMap.put(subcategoriesLookup.getCategoryId(), subCategoryMap);
		}		
		return callSubcategoriesHashMap;
	}
	
}
