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

import com.archsystemsinc.cmts.sec.util.GenericConstants;
import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.service.RebuttalService;
import com.archsystemsinc.qam.service.mail.MailService;

	
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
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@RequestMapping(value = "/rebuttallist", method = RequestMethod.POST)
	public List<Rebuttal> getRebuttalList(@RequestBody Rebuttal rebuttal){
		List<Rebuttal> data=null;
		try {
			log.debug("--> getRebuttalList:");
			data = rebuttalService.search(rebuttal);
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
		boolean newRebuttal = false;
		
		try {
			rebuttalResult = rebuttalService.saveOrUpdateRebuttal(rebuttal);
			
			if(newRebuttal) {
				mailService.sendEmail(GenericConstants.EMAIL_TYPE_RB_CREATE, fromEmail, "nissar.msis@gmail.com,mmohammed@archsystemsinc.com,ashaik@archsystemsinc.com");
			} else {
				mailService.sendEmail(GenericConstants.EMAIL_TYPE_RB_UPDATE, fromEmail, "nissar.msis@gmail.com,mmohammed@archsystemsinc.com,ashaik@archsystemsinc.com");
			}
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return rebuttalResult;
			
		}
		log.debug("<-- saveOrUpdateRebuttal");
		return rebuttalResult;
	}	
}