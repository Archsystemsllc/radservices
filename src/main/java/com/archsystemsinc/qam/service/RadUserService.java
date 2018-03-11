/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.model.Role;
import com.archsystemsinc.qam.repository.RadUserRepository;
import com.archsystemsinc.qam.repository.RoleRepository;
import com.archsystemsinc.qam.repository.specifications.RadUserSpecifications;

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
	
	public List< RadUser > search( RadUser radUser ){
		
		if(radUser.getRoleString() != null && !radUser.getRoleString().equalsIgnoreCase("")) {
			Role roleTemp = roleRepository.findOne(Long.valueOf(radUser.getRoleString()));
			radUser.setRole(roleTemp);
		}
		
		Specifications< RadUser > specifications = Specifications.where
					(RadUserSpecifications.searchById(radUser.getId()))
				.and(RadUserSpecifications.searchByUserName(radUser.getUserName()))				
				.and(RadUserSpecifications.searchByMacId(radUser.getMacId()))
				.and(RadUserSpecifications.searchByJurIdList(radUser.getJurIdList()))				
				.and(RadUserSpecifications.searchByRole(radUser.getRole()))
				.and(RadUserSpecifications.searchByLastName(radUser.getLastName()))
				.and(RadUserSpecifications.searchByOrganizationLookup(radUser.getOrganizationLookup()))
				;
															
		return radUserRepository.findAll(specifications);
	}

	public List<Role> listRoles() {
		log.debug("--> listRoles:");
		List<Role> data = roleRepository.findAll();
		log.debug("<-- listRoles");
		return data;
	}

	/**
	 * 
	 * @return
	 */

	public List<RadUser> listUsers() {
		log.debug("--> listUsers:");
		List<RadUser> data = radUserRepository.findUsers(new Long(2));
		log.debug("<-- listUsers");
		return data;
	}

	public Integer updateUserLastLoggedinDate(Long userId, Date lastLoggedinDate){
		return radUserRepository.updateUserLastLoggedinDate(userId, lastLoggedinDate);
	}
	/**
	 * 
	 * @return
	 */

	public RadUser updateUser(RadUser radUser) {
		log.debug("--> updateUser:" + radUser);
		//The callee is sending encoded pwd
		//BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		//radUser.setPassword(b.encode(radUser.getPassword()));
		
		radUser = radUserRepository.save(radUser);
		/*Integer count = radUserRepository.updateUserData(radUser.getPassword(),
				radUser.getRole().getId(), radUser.getOrganizationLookup()
						.getId(), radUser.getMacId(), radUser.getPccId(),
				radUser.getEmailId(), radUser.getFirstName(), radUser
						.getMiddleName(), radUser.getLastName(), radUser
						.getId(), new Date(), radUser.getUserName());*/
		log.debug("<-- updateUser");
		return radUser;
	}

	/**
	 * 
	 * @return
	 */
	
	@Autowired
	ApplicationEventPublisher eventPublisher;

	public RadUser createUser(RadUser radUser) {
		log.debug("--> createUser:");
		try {
			BCryptPasswordEncoder b = new BCryptPasswordEncoder();
			radUser.setPassword(b.encode(radUser.getPassword()));
			radUser.setCreatedDate(new Date());
			radUser.setUpdateDate(new Date());
			radUser = radUserRepository.save(radUser);
			
			if (radUser == null) {
				radUser = null;
		    }
		  /*  try {
		        //String appUrl = request.getContextPath();
		        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
		          (radUser, request.getLocale(), appUrl));
		    } catch (Exception me) {
		    	radUser = null;
		    }
		   */
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("<-- createUser");
		return radUser;
	}

	/**
	 * 
	 * @return
	 */

	public Integer updateStatus(Long userId, Long status, String updatedBy) {
		log.debug("--> createUser:");
		Integer cunt = radUserRepository.updateStatus(status, userId,
				new Date(), updatedBy);
		log.debug("<-- createUser");
		return cunt;
	}
	
	/**
	 * 
	 * @return
	 */

	public Integer updateStatus(Long status, String updatedBy) {
		log.debug("--> createUser:");
		Integer cunt = radUserRepository.updateStatus(status,new Date(), updatedBy);
		log.debug("<-- createUser");
		return cunt;
	}

	public RadUser findUser(String userName) {
		log.debug("--> findUser:" + userName);
		RadUser radUser = radUserRepository.findByUserName(userName);
		log.debug("<-- findUser:");
		return radUser;
	}

	public Integer updateUserPassword(Long userId, String newPassword) {
		log.debug("--> updateUserPassword:" + userId);
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		newPassword = b.encode(newPassword);
		Integer count = radUserRepository.updateUserPassword(userId,
				newPassword);
		log.debug("<-- updateUser");
		return count;
	}

	public List<RadUser> filterUser(String lastName, String roleId, String orgId, String macId, String jurisId) {
		log.debug("--> filterUser:");
		List<RadUser> data = null;
		if (lastName != null && !"".equals(lastName.trim()) && !lastName.equalsIgnoreCase("null")) {
			data = radUserRepository.filterUser(lastName, new Long(roleId),
					new Integer(orgId));
		} else if(macId !=null && !macId.equalsIgnoreCase("")) {
			data = radUserRepository.findByMacIdAndJurisId(Long.valueOf(macId), Long.valueOf(jurisId));
			
		} else {
			data = radUserRepository.filterUser(new Long(roleId), new Integer(
					orgId));
		}

		log.debug("<-- filterUser");
		return data;
	}

	public RadUser findById(Long id) {
		return radUserRepository.findOne(id);
	}

}
