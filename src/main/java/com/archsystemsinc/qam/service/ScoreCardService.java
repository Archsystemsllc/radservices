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

import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.repository.ScoreCardRepository;
import com.archsystemsinc.qam.repository.specifications.ScoreCardSpecifications;
import com.archsystemsinc.qam.sec.util.GenericConstants;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class ScoreCardService {
	private static final Logger log = Logger.getLogger(ScoreCardService.class);
	@Autowired
	private ScoreCardRepository scoreCardRepository;	
	
	public List<ScoreCard> findAll(){
		return scoreCardRepository.findAll();
	}	
	
	public ScoreCard saveOrUpdateScoreCard(ScoreCard scoreCard) {
		scoreCard = scoreCardRepository.save(scoreCard);
		return scoreCard;
	}
	
	public List<ScoreCard> retrieveFailedCallList(){
		return scoreCardRepository.findAllByFailedReason("Fail");
	}
	
	public List<ScoreCard> retrieveFailedCallListByMacIdJurisId(ScoreCard scoreCard){
		
		Specifications< ScoreCard > specifications = Specifications.where
				(ScoreCardSpecifications.searchByMacId(scoreCard.getMacId()))
				.and(ScoreCardSpecifications.searchByJurIdList(scoreCard.getJurIdList()))	
				.and(ScoreCardSpecifications.findByCallMonitoringDateBetween(scoreCard.getFilterFromDate(), scoreCard.getFilterToDate()))
				.and(ScoreCardSpecifications.searchByFinalScoreCardStatus(GenericConstants.FAIL_STRING))
			;
		return scoreCardRepository.findAll(specifications);
	}

	public List< ScoreCard > search( ScoreCard scoreCard ){
		
		Specifications< ScoreCard > specifications = Specifications.where
					(ScoreCardSpecifications.searchByQamFullName(scoreCard.getQamFullName()))
				.and(ScoreCardSpecifications.searchByCallResult(scoreCard.getCallResult()))				
				.and(ScoreCardSpecifications.searchByScorecardType(scoreCard.getScorecardType()))
				.and(ScoreCardSpecifications.searchByMacId(scoreCard.getMacId()))
				.and(ScoreCardSpecifications.searchByJurisdictionId(scoreCard.getJurId()))
				.and(ScoreCardSpecifications.searchByProgramId(scoreCard.getProgramId()))
				.and(ScoreCardSpecifications.searchByJurIdList(scoreCard.getJurIdList()))	
				.and(ScoreCardSpecifications.searchByProgramIdList(scoreCard.getProgramIdList()))
				.and(ScoreCardSpecifications.searchByProgramId(scoreCard.getProgramIdReportSearchString()))
				.and(ScoreCardSpecifications.searchByUserId(scoreCard.getUserId()))
				.and(ScoreCardSpecifications.findByCallMonitoringDateBetween(scoreCard.getFilterFromDate(), scoreCard.getFilterToDate()))
				//.and(ScoreCardSpecifications.findByUptoFilterToDate(scoreCard.getFilterToDate()))
				.and(ScoreCardSpecifications.searchByCallResultList(scoreCard.getMacCallResultList()))
				.and(ScoreCardSpecifications.searchByQamCalibrationStatus(scoreCard.getQamCalibrationStatus()))
				.and(ScoreCardSpecifications.searchByCmsCalibrationStatus(scoreCard.getCmsCalibrationStatus()))
				.and(ScoreCardSpecifications.searchByFinalScoreCardStatus(scoreCard.getFinalScoreCardStatus()))
				;
															
		return scoreCardRepository.findAll(specifications);
	}
}