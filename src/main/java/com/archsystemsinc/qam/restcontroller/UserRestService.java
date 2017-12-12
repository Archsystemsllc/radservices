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

import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.model.Role;
import com.archsystemsinc.qam.repository.RadUserRepository;
import com.archsystemsinc.qam.repository.RoleRepository;
	
/**
 * @author Prakash T
 *
 */
@RestController
@RequestMapping("api")
public class UserRestService {
	private static final Logger log = Logger.getLogger(UserRestService.class);
	
	@Autowired
	private RadUserRepository radUserRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listRoles", method = RequestMethod.GET)
	public List<Role> listRoles(){
		log.debug("--> listRoles:");
		List<Role> data = roleRepository.findAll();
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
		List<RadUser> data = radUserRepository.findAll();
		log.debug("<-- listUsers");
		return data;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public RadUser updateUser(RadUser radUser){
		log.debug("--> updateUser:"+radUser);
		radUser = radUserRepository.save(radUser);
		log.debug("<-- updateUser");
		return radUser;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public RadUser createUser(RadUser radUser){
		log.debug("--> createUser:");
		radUser = radUserRepository.save(radUser);
		log.debug("<-- createUser");
		return radUser;
	}
	
}
