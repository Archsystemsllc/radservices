/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.repository.CsrLogRepository;
import com.archsystemsinc.qam.repository.RebuttalRepository;
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
	
	@Autowired
	private CsrLogRepository csrLogRepository;
	
	
	@Autowired
	private RebuttalRepository rebuttalRepository;
	
	public List<ScoreCard> retrieveMacJurisScorecardReport(String macId, String jurisId, Date fromDate, Date toDate, String scoreCardType, String callResult){
		List<ScoreCard> reportResults = null;
		List<ScoreCard> finalResultsList = null;
		
		if(macId.equalsIgnoreCase("ALL") && jurisId.equalsIgnoreCase("ALL")) {
			reportResults = scoreCardRepository.macJuriReport_AllMacAllJuris(fromDate, toDate);	
		} else if(macId.equalsIgnoreCase("ALL") ) {
			reportResults = scoreCardRepository.macJuriReport_AllMac(Integer.valueOf(jurisId), fromDate, toDate);	
		} else if(jurisId.equalsIgnoreCase("ALL")) {
			reportResults = scoreCardRepository.macJuriReport_AllJuris(Integer.valueOf(macId), fromDate, toDate);	
		} else {
			reportResults = scoreCardRepository.macJurisReport(Integer.valueOf(macId), Integer.valueOf(jurisId), fromDate, toDate);			
		}
		
		if(scoreCardType.equalsIgnoreCase("")) {
			finalResultsList = reportResults;
		} else if (scoreCardType.equalsIgnoreCase("Scoreable") && callResult.equalsIgnoreCase("All")) {
			finalResultsList = new ArrayList<ScoreCard>();
			for(ScoreCard scoreCard:reportResults) {
				if (scoreCard.getScorecardType().equalsIgnoreCase("Scoreable")) {
					finalResultsList.add(scoreCard);
				}
			}
			
		}  else if (scoreCardType.equalsIgnoreCase("Scoreable") && callResult.equalsIgnoreCase("Pass")) {
			finalResultsList = new ArrayList<ScoreCard>();
			for(ScoreCard scoreCard:reportResults) {
				if (scoreCard.getScorecardType().equalsIgnoreCase("Scoreable") && scoreCard.getCallResult().equalsIgnoreCase("Pass")) {
					finalResultsList.add(scoreCard);
				}
			}
			
		}  else if (scoreCardType.equalsIgnoreCase("Scoreable") && callResult.equalsIgnoreCase("Fail") ) {
			finalResultsList = new ArrayList<ScoreCard>();
			for(ScoreCard scoreCard:reportResults) {
				if (scoreCard.getScorecardType().equalsIgnoreCase("Scoreable") && scoreCard.getCallResult().equalsIgnoreCase("Fail")) {
					finalResultsList.add(scoreCard);
				}
			}
			
		} else if (scoreCardType.equalsIgnoreCase("Non-Scoreable")) {
			finalResultsList = new ArrayList<ScoreCard>();
			for(ScoreCard scoreCard:reportResults) {
				if (scoreCard.getScorecardType().equalsIgnoreCase("Non-Scoreable")) {
					finalResultsList.add(scoreCard);
				}
			}
			
		} else if (scoreCardType.equalsIgnoreCase("Does Not Count")) {
			finalResultsList = new ArrayList<ScoreCard>();
			for(ScoreCard scoreCard:reportResults) {
				if (scoreCard.getScorecardType().equalsIgnoreCase("Does Not Count")) {
					finalResultsList.add(scoreCard);
				}
			}
			
		}
		
		return finalResultsList;
	}	
	
	public List<CsrLog> retrieveComplianceReport(String macId, String jurisdiction, Date fromDate, Date toDate){
		List<CsrLog> reportResults = null;
		//List<CsrLog> finalResultsList = null;
		
		if(macId.equalsIgnoreCase("ALL") && jurisdiction.equalsIgnoreCase("ALL")) {
			reportResults = csrLogRepository.complianceReport_AllMacAllJuris(fromDate, toDate);	
		} else if(macId.equalsIgnoreCase("ALL") ) {
			reportResults = csrLogRepository.complianceReport_AllMac(jurisdiction, fromDate, toDate);	
		} else if(jurisdiction.equalsIgnoreCase("ALL")) {
			reportResults = csrLogRepository.complianceReport_AllJuris(Integer.valueOf(macId), fromDate, toDate);	
		} else {
			reportResults = csrLogRepository.complianceReport(Integer.valueOf(macId), jurisdiction, fromDate, toDate);			
		}
		return reportResults;
	}	
	
	public List<Rebuttal> retrieveRebuttalReportData(String macId, String jurisdiction, Date fromDate, Date toDate){
		List<Rebuttal> reportResults = null;
		//List<CsrLog> finalResultsList = null;
		
		if(macId.equalsIgnoreCase("ALL") && jurisdiction.equalsIgnoreCase("ALL")) {
			reportResults = rebuttalRepository.rebuttalReport_AllMacAllJuris(fromDate, toDate);	
		} else if(macId.equalsIgnoreCase("ALL") ) {
			reportResults = rebuttalRepository.rebuttalReport_AllMac(Integer.valueOf(jurisdiction), fromDate, toDate);	
		} else if(jurisdiction.equalsIgnoreCase("ALL")) {
			reportResults = rebuttalRepository.rebuttalReport_AllJuris(Integer.valueOf(macId), fromDate, toDate);	
		} else {
			reportResults = rebuttalRepository.rebuttalReport(Integer.valueOf(macId), Integer.valueOf(jurisdiction), fromDate, toDate);			
		}
		return reportResults;
	}	
}