/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.OrganizationLookup;
import com.archsystemsinc.qam.repository.OrganizationLookupRepository;

/**
 * @author 
 *
 */
@Service
@Transactional
public class OrganizationLookupService {
	private static final Logger log = Logger.getLogger(OrganizationLookupService.class);
	@Autowired
	private OrganizationLookupRepository organizationLookupRepository;
	
	
	public List<OrganizationLookup> findAll(){
		return organizationLookupRepository.findAll();
	}
	
}
