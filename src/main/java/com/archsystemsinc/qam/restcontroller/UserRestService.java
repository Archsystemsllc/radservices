/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.model.Role;
import com.archsystemsinc.qam.service.RadUserService;
	
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
		Integer count = radUserService.updateUser(radUser);
		log.debug("<-- updateUser");
		return count;
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
	@RequestMapping(value = "/findUser/{userName}", method = RequestMethod.GET)
	public RadUser findUser(@PathVariable("userName") String userName){
		log.debug("--> findUser:"+userName);
		RadUser radUser = radUserService.findUser(userName);
		log.debug("<-- findUser");
		return radUser;
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
	@RequestMapping(value = "/findUser/{lastName}/{roleId}/{orgId}", method = RequestMethod.GET)
	public List<RadUser> filterUser(@PathVariable("lastName") String lastName,@PathVariable("roleId") String roleId,@PathVariable("orgId") String orgId){
		log.debug("--> filterUser:"+lastName);
		log.debug("--> roleId:"+roleId);
		log.debug("--> orgId:"+orgId);
		List<RadUser> radUser = radUserService.filterUser(lastName,roleId,orgId);
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
		List<RadUser> radUser = radUserService.filterUser(null,roleId,orgId);
		log.debug("<-- filterUserById");
		return radUser;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public RadUser createUser(RadUser radUser){
		log.debug("--> createUser:");
		radUser = radUserService.createUser(radUser);
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
