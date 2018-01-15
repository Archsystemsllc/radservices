/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.ProgramLookup;
import com.archsystemsinc.qam.repository.ProgramLookupRepository;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class ProgramLookupService {
	private static final Logger log = Logger.getLogger(ProgramLookupService.class);
	@Autowired
	private ProgramLookupRepository programLookupRepository;
	
	
	public List<ProgramLookup> findAll(){
		return programLookupRepository.findAll();
	}
	
}
