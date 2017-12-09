/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.MacLookup;
import com.archsystemsinc.qam.repository.MacLookupRepository;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class MacLookupService {
	private static final Logger log = Logger.getLogger(MacLookupService.class);
	@Autowired
	private MacLookupRepository macLookupRepository;
	
	
	public List<MacLookup> findAll(){
		return macLookupRepository.findAll();
	}
	
}
