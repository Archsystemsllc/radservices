/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.CategoryLookup;
import com.archsystemsinc.qam.repository.CategoryLookupRepository;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class CategoryLookupService {
	private static final Logger log = Logger.getLogger(CategoryLookupService.class);
	@Autowired
	private CategoryLookupRepository categoryLookupRepository;
	
	
	public List<CategoryLookup> findAll(){
		return categoryLookupRepository.findAll();
	}
	
}
