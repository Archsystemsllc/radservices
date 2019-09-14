/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.PccLocation;
import com.archsystemsinc.qam.model.ProgramLookup;
import com.archsystemsinc.qam.repository.PccLocationRepository;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class PccLocationService {
	private static final Logger log = Logger.getLogger(PccLocationService.class);
	@Autowired
	private PccLocationRepository pccLocationRepository;
	
	public List<PccLocation> findAll(){
		return pccLocationRepository.findAll();
	}
	
	public PccLocation saveOrUpdatePccLocation(PccLocation pccLocation) {
		pccLocation = pccLocationRepository.save(pccLocation);
		return pccLocation;
	}
	
	public PccLocation findById(Integer id) {
		return pccLocationRepository.findOne(id);
	}
	
}
