/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.model.ReportsForm;
import com.archsystemsinc.qam.repository.RebuttalRepository;
import com.archsystemsinc.qam.repository.specifications.RebuttalSpecifications;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class RebuttalService {
	private static final Logger log = Logger.getLogger(RebuttalService.class);
	@Autowired
	private RebuttalRepository rebuttalRepository;	
	
	public List<Rebuttal> findAll(){
		return rebuttalRepository.findAll();
	}	
	
	public Rebuttal saveOrUpdateRebuttal(Rebuttal rebuttal) {
		rebuttal = rebuttalRepository.save(rebuttal);
		return rebuttal;
	}
	
	public List< Rebuttal > search( Rebuttal rebuttal ){	
		
		Specifications< Rebuttal > specifications = Specifications.where
					(RebuttalSpecifications.searchByUserId(rebuttal.getUserId()))
							.and(RebuttalSpecifications.searchByMacId(rebuttal.getMacId()))
							.and(RebuttalSpecifications.searchByJurIdList(rebuttal.getJurisIdList()))	
							.and(RebuttalSpecifications.findByDatePostedBetween(rebuttal.getFilterFromDate(), rebuttal.getFilterToDate()))	
							.and(RebuttalSpecifications.searchByJurId(rebuttal.getJurisId()));
															
		return rebuttalRepository.findAll(specifications);
	}
	
public List< Rebuttal > searchRebuttalForReport( ReportsForm reportsForm ){	
		
		Specifications< Rebuttal > specifications = Specifications.where
					(RebuttalSpecifications.searchByMacId(Integer.valueOf(reportsForm.getMacId())))
							.and(RebuttalSpecifications.searchByJurIdList(reportsForm.getJurIdList()))
							.and(RebuttalSpecifications.searchByRebuttalStatus(reportsForm.getRebuttalStatus()))
							.and(RebuttalSpecifications.findByDatePostedBetween(reportsForm.getFromDate(),reportsForm.getToDate()))
							.and(RebuttalSpecifications.searchByCallCategory(reportsForm.getCallCategoryType()))
							;
															
		return rebuttalRepository.findAll(specifications);
		

	}
}

