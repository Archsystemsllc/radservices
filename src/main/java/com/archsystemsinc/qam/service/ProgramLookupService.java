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
 * @author 
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
	
	public ProgramLookup saveOrUpdateProgram(ProgramLookup programLookup) {
		programLookup = programLookupRepository.save(programLookup);
		return programLookup;
	}
	
	public ProgramLookup findById(Integer id) {
		return programLookupRepository.findOne(id);
	}
}
