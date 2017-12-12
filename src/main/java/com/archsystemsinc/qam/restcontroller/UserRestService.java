/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
}
