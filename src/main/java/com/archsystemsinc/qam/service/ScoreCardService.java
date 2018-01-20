/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.repository.ScoreCardRepository;

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
}