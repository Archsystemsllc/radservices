/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.MacProgJurisPccMapping;
import com.archsystemsinc.qam.repository.MacProgJurisPccMappingRepository;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class MacProgJurisPccMappingService {
	private static final Logger log = Logger.getLogger(MacProgJurisPccMappingService.class);
	@Autowired
	private MacProgJurisPccMappingRepository macProgJurisPccMappingRepository;	
	
	public List<MacProgJurisPccMapping> findAll(){
		return macProgJurisPccMappingRepository.findAll();
	}	
	
	public MacProgJurisPccMapping saveOrUpdateMacLookup(MacProgJurisPccMapping macProgJurisPccMapping) {
		macProgJurisPccMapping = macProgJurisPccMappingRepository.save(macProgJurisPccMapping);
		return macProgJurisPccMapping;
	}
	
	public MacProgJurisPccMapping findById(Integer id) {
		return macProgJurisPccMappingRepository.findOne(id);
	}
}