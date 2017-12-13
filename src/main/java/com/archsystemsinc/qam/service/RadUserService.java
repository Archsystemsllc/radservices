/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.model.Role;
import com.archsystemsinc.qam.repository.RadUserRepository;
import com.archsystemsinc.qam.repository.RoleRepository;

/**
 * @author Prakash T
 *
 */
@Service
@Transactional
public class RadUserService {
	private static final Logger log = Logger.getLogger(RadUserService.class);
	@Autowired
	private RadUserRepository radUserRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * 
	 * @return
	 */

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
	
	public Integer updateUser(RadUser radUser){
		log.debug("--> updateUser:"+radUser);
		Integer count = radUserRepository.updateUser(radUser.getEmailId(), radUser.getFirstName(), radUser.getMiddleName(), radUser.getLastName(), radUser.getId(), new Date(), radUser.getUserName());
		log.debug("<-- updateUser");
		return count;
	}
	
	/**
	 * 
	 * @return
	 */

	public RadUser createUser(RadUser radUser){
		log.debug("--> createUser:");
		radUser = radUserRepository.save(radUser);
		log.debug("<-- createUser");
		return radUser;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public Integer updateStatus(Long userId, Long status, String updatedBy){
		log.debug("--> createUser:");
		Integer cunt = radUserRepository.updateStatus(status,userId, new Date(), updatedBy);
		log.debug("<-- createUser");
		return cunt;
	}
	
	
}