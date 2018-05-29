/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.cmts.sec.util.GenericConstants;
import com.archsystemsinc.qam.model.MacAssignmentObject;
import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.service.MacAssignmentObjectService;
import com.archsystemsinc.qam.service.RebuttalService;
import com.archsystemsinc.qam.service.mail.MailService;
import com.archsystemsinc.qam.utils.EmailObject;

	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class MacAssigmentObjectRestService {
	private static final Logger log = Logger.getLogger(MacAssigmentObjectRestService.class);
	
	@Autowired
	private MacAssignmentObjectService macAssignmentObjectService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	@RequestMapping(value = "/macAssignmentList", method = RequestMethod.POST)
	public List<Object[]> getMacAssignmentList(@RequestBody MacAssignmentObject macAssignmentObject){
		List<Object[]> data=null;
		
		try {
			log.debug("--> getMacAssignmentList:");
			
			data = macAssignmentObjectService.searchMacAssignmentObjectForList(macAssignmentObject);
			log.debug("<-- getMacAssignmentList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	@RequestMapping(value = "/macAssignmentWithMonthYear", method = RequestMethod.POST)
	public List<MacAssignmentObject> getMacAssignmentListForCurrentMonth(@RequestBody MacAssignmentObject macAssignmentObject){
		List<MacAssignmentObject> data=null;
		
		try {
			log.debug("--> getMacAssignmentListForCurrentMonth:");
			
			data = macAssignmentObjectService.searchWithCurrentMonthYear(macAssignmentObject);
			log.debug("<-- getMacAssignmentListForCurrentMonth");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	
	
	@RequestMapping(value = "/saveOrUpdateMacAssignment", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateMacAssignmentObject(
			@RequestBody  ArrayList<MacAssignmentObject> macAssignmentObjectList){
		log.debug("--> saveOrUpdateMacAssignmentObject:");		
		//Rebuttal rebuttal = new Rebuttal();
		MacAssignmentObject macAssignmentObjectResult = null;
		boolean newMacAssignmentObject = false;
		
		try {
			
			for(MacAssignmentObject macAssignmentObject: macAssignmentObjectList) {
				if(macAssignmentObject.getId() == 0) {
					macAssignmentObject.setCreatedDate(new Date());
				} else {
					macAssignmentObject.setUpdatedDate(new Date());
				}
				macAssignmentObjectResult = macAssignmentObjectService.saveOrUpdateMacAssignmentObject(macAssignmentObject);
			}
			
			
			/*if(newMacAssignmentObject) {
				EmailObject emailObject;
				
				emailObject = new EmailObject();
				
				mailService.sendEmail(emailObject);
				
			} else {
				
				EmailObject emailObject;
				
				emailObject = new EmailObject();
				
				mailService.sendEmail(emailObject);
			}*/
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return "Failure";
			
			
		}
		log.debug("<-- saveOrUpdateMacAssignmentObject");
		return "Success";
	}	
}