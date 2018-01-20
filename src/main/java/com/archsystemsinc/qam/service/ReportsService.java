/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.Date;
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
public class ReportsService {
	private static final Logger log = Logger.getLogger(ReportsService.class);
	@Autowired
	private ScoreCardRepository scoreCardRepository;	
	
	public List<ScoreCard> findAll(){
		return scoreCardRepository.findAll();
	}	
	
	public List<ScoreCard> retrieveMacJurisReport(String macId, String jurisId, Date fromDate, Date toDate){
		List<ScoreCard> reportResults = null;
		
		if(macId.equalsIgnoreCase("ALL") && jurisId.equalsIgnoreCase("ALL")) {
			reportResults = scoreCardRepository.macJuriReport_AllMacAllJuris(fromDate, toDate);	
		} else if(macId.equalsIgnoreCase("ALL") ) {
			reportResults = scoreCardRepository.macJuriReport_AllMac(Integer.valueOf(jurisId), fromDate, toDate);	
		} else if(jurisId.equalsIgnoreCase("ALL")) {
			reportResults = scoreCardRepository.macJuriReport_AllJuris(Integer.valueOf(macId), fromDate, toDate);	
		} else {
			reportResults = scoreCardRepository.macJurisReport(Integer.valueOf(macId), Integer.valueOf(jurisId), fromDate, toDate);			
		}
		
		return reportResults;
	}	
}