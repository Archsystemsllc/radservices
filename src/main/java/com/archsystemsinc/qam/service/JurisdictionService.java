/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.Jurisdiction;
import com.archsystemsinc.qam.repository.JurisdictionRepository;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class JurisdictionService {
	private static final Logger log = Logger.getLogger(JurisdictionService.class);
	@Autowired
	private JurisdictionRepository jurisdictionRepository;	
	
	public List<Jurisdiction> findAll(){
		return jurisdictionRepository.findAll();
	}	
}