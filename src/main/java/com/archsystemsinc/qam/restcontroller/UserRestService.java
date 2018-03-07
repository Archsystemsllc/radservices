/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.cmts.sec.util.GenericConstants;
import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.model.Role;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.service.RadUserService;
import com.archsystemsinc.qam.service.mail.MailService;
import com.archsystemsinc.qam.utils.EmailObject;
	
/**
 * @author Prakash T
 *
 */
@RestController
@RequestMapping("api")
public class UserRestService {
	private static final Logger log = Logger.getLogger(UserRestService.class);
	
	@Autowired
	private RadUserService radUserService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
	public List<RadUser> searchUsers(@RequestBody RadUser radUser){
		log.debug("--> searchUsers:");
		List<RadUser> data = radUserService.search(radUser);
		log.debug("<-- searchUsers");
		return data;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listRoles", method = RequestMethod.GET)
	public List<Role> listRoles(){
		log.debug("--> listRoles:");
		List<Role> data = radUserService.listRoles();
		log.debug("<-- listRoles");
		return data;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
	public List<RadUser> listUsers(){
		log.debug("--> listUsers:");
		List<RadUser> data = radUserService.listUsers();
		log.debug("<-- listUsers");
		return data;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public Integer updateUser(RadUser radUser){
		log.debug("--> updateUser:"+radUser);
		radUser.setStatus(1l);
		radUser.setUpdateDate(new Date());
		radUser = radUserService.updateUser(radUser);
		log.debug("<-- updateUser");
		return 1;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
	public Integer updateUserPassword(@RequestParam("userId") Long userId, @RequestParam("newPassword") String newPassword){
		log.debug("--> updateUserPassword:"+userId);
		Integer count = radUserService.updateUserPassword(userId,newPassword);
		log.debug("<-- updateUserPassword");
		return count;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateUserLastLoginDate", method = RequestMethod.POST)
	public Integer updateUserLastLoginDate(@RequestParam("userId") Long userId){
		log.debug("--> updateUserLastLoginDate:"+userId);
		Integer count = radUserService.updateUserLastLoggedinDate(userId, new Date());
		log.debug("<-- updateUserLastLoginDate");
		return count;
	}

	
	/**
	 * 
	 * @return
	 */
	

	@RequestMapping(value = "/findUser",method = RequestMethod.POST)
	public @ResponseBody RadUser findUser(@RequestBody RadUser radUser ){
		log.debug("--> findUser:"+radUser.getUserName());
		List<RadUser> radUserReturnList = radUserService.search(radUser);
		RadUser radUserReturn = null;
		if (radUserReturnList != null && radUserReturnList.size() > 0) {
			radUserReturn = radUserReturnList.get(0);
		}
		log.debug("<-- findUser");
		return radUserReturn;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET)
	public RadUser findUserById(@PathVariable("id") Long id){
		log.debug("--> findUserById:"+id);
		RadUser radUser = radUserService.findById(id);
		log.debug("<-- findUserById");
		return radUser;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findUser/{lastName}/{roleId}/{orgId}/{macId}/{jurisId}", method = RequestMethod.GET)
	public List<RadUser> filterUser(@PathVariable("lastName") String lastName,@PathVariable("roleId") String roleId,@PathVariable("orgId") String orgId,
			@PathVariable("macId") String macId,@PathVariable("jurisId") String jurisId){
		log.debug("--> filterUser:"+lastName);
		log.debug("--> roleId:"+roleId);
		log.debug("--> orgId:"+orgId);
		List<RadUser> radUser = radUserService.filterUser(lastName,roleId,orgId,macId,jurisId);
		log.debug("<-- roleId");
		return radUser;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findUser/{roleId}/{orgId}", method = RequestMethod.GET)
	public List<RadUser> filterUserById(@PathVariable("roleId") String roleId,@PathVariable("orgId") String orgId){
		log.debug("--> filterUserById:");
		log.debug("--> roleId:"+roleId);
		log.debug("--> orgId:"+orgId);
		List<RadUser> radUser = radUserService.filterUser(null,roleId,orgId,null,null);
		log.debug("<-- filterUserById");
		return radUser;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public RadUser createUser(@RequestBody RadUser radUser){
		log.debug("--> createUser:");
		radUser = radUserService.createUser(radUser);
		//mailService.sendEmail(GenericConstants.EMAIL_TYPE_UM_CREATE, fromEmail, "nissar.msis@gmail.com,mmohammed@archsystemsinc.com,ashaik@archsystemsinc.com");
		//mailService.sendEmail(null);
		String link = radUIEndPoint ;
		
		
		EmailObject emailObject = new EmailObject();
		emailObject.setFromEmail(fromEmail);
		emailObject.setEmailType(GenericConstants.EMAIL_TYPE_UM_CREATE_ADMIN_EMAIL);
		emailObject.setToEmail("nissar.msis@gmail.com,sheiknissu4@gmail.com");
		//emailObject.setMacName(scoreCard.getMacName());
		//emailObject.setJurisidctionName(scoreCard.getJurisdictionName());
		
		//Send Admin Email
		mailService.sendEmail(emailObject);
		
		emailObject.setUsername(radUser.getUserName());
		emailObject.setPassword(radUser.getPassword());
		emailObject.setEmailType(GenericConstants.EMAIL_TYPE_UM_CREATE_USER_EMAIL);
		emailObject.setLink(link);
		mailService.sendEmail(emailObject);
		
		log.debug("<-- createUser");
		return radUser;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public Integer updateStatus(@RequestParam("userId") Long userId,@RequestParam("status") Long status,@RequestParam("updatedBy") String updatedBy){
		log.debug("--> updateStatus:");
		Integer count = radUserService.updateStatus(userId, status,updatedBy);
		log.debug("<-- updateStatus");
		return count;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateStatusForAll", method = RequestMethod.POST)
	public Integer updateStatusForAll(@RequestParam("status") Long status,@RequestParam("updatedBy") String updatedBy){
		log.debug("--> updateStatus:");
		Integer count = radUserService.updateStatus(status,updatedBy);
		log.debug("<-- updateStatus");
		return count;
	}
}
