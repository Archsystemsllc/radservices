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
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.repository.RebuttalRepository;
import com.archsystemsinc.qam.repository.specifications.RebuttalSpecifications;
import com.archsystemsinc.qam.repository.specifications.ScoreCardSpecifications;

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
							.and(RebuttalSpecifications.searchByJurId(rebuttal.getJurisId()));
															
		return rebuttalRepository.findAll(specifications);
	}
}

