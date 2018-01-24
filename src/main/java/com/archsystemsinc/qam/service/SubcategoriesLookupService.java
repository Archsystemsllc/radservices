/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.SubcategoriesLookup;
import com.archsystemsinc.qam.repository.SubcategoriesLookupRepository;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class SubcategoriesLookupService {
	private static final Logger log = Logger.getLogger(SubcategoriesLookupService.class);
	@Autowired
	private SubcategoriesLookupRepository subcategoriesLookupRepository;
	
	
	public List<SubcategoriesLookup> findAll(){
		return subcategoriesLookupRepository.findAll();
	}
}