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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.cmts.sec.util.GenericConstants;
import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.service.RebuttalService;
import com.archsystemsinc.qam.service.mail.MailService;
import com.archsystemsinc.qam.utils.EmailObject;

	
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
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
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
	
	/*@RequestMapping(value = "/{id}/image", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional(rollbackFor = Exception.class)
	public byte[] setImage(@PathVariable("id") Long userId,
	        @RequestParam("file") MultipartFile file) throws IOException {
	    // Upload logic
	}*/
	
	@RequestMapping(value = "/saveOrUpdateRebuttal", method = RequestMethod.POST)
	public @ResponseBody Rebuttal saveOrUpdateRebuttal(@RequestBody Rebuttal rebuttal){
		log.debug("--> saveOrUpdateRebuttal:");		
		
		Rebuttal rebuttalResult = null;
		boolean newRebuttal = false;
		
		try {
			
			if (rebuttal.getId() == 0) {
				newRebuttal = true;
			}
			rebuttalResult = rebuttalService.saveOrUpdateRebuttal(rebuttal);
			
			if(newRebuttal) {
				EmailObject emailObject;
				
				emailObject = new EmailObject();
				emailObject.setFromEmail(fromEmail);
				emailObject.setEmailType(GenericConstants.EMAIL_TYPE_SC_CREATE);
				emailObject.setToEmail("nissar.msis@gmail.com,sheiknissu4@gmail.com");
				emailObject.setMacName(rebuttal.getMacName());
				emailObject.setJurisidctionName(rebuttal.getJurisName());
				emailObject.setLink(radUIEndPoint);
				mailService.sendEmail(emailObject);
				
			} else {
				
				EmailObject emailObject;
				
				emailObject = new EmailObject();
				emailObject.setFromEmail(fromEmail);
				emailObject.setEmailType(GenericConstants.EMAIL_TYPE_RB_UPDATE);
				emailObject.setToEmail("nissar.msis@gmail.com,sheiknissu4@gmail.com");
				emailObject.setMacName(rebuttal.getMacName());
				emailObject.setJurisidctionName(rebuttal.getJurisName());
				emailObject.setLink(radUIEndPoint);
				mailService.sendEmail(emailObject);
			}
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return rebuttalResult;
			
		}
		log.debug("<-- saveOrUpdateRebuttal");
		return rebuttalResult;
	}	
}