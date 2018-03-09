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
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.repository.CsrLogRepository;
import com.archsystemsinc.qam.repository.RebuttalRepository;
import com.archsystemsinc.qam.repository.ScoreCardRepository;
import com.archsystemsinc.qam.repository.specifications.CsrLogSpecifications;
import com.archsystemsinc.qam.repository.specifications.ScoreCardSpecifications;

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
	private RebuttalService rebuttalService;
	
	
	@Autowired
	private RebuttalRepository rebuttalRepository;
	
	public List<ScoreCard> retrieveMacJurisScorecardReport(String macId, String jurisId, String programId, Date fromDate, Date toDate, String scoreCardType, String callResult){
		List<ScoreCard> reportResults = null;
		List<ScoreCard> finalResultsList = null;
		
		if(macId.equalsIgnoreCase("ALL") && jurisId.equalsIgnoreCase("ALL") && programId.equalsIgnoreCase("ALL")) {
			reportResults = scoreCardRepository.scoreCardReport_AllMacAllJurisAllProgram(fromDate, toDate);	
		} else if(macId.equalsIgnoreCase("ALL") && jurisId.equalsIgnoreCase("ALL")&& !programId.equalsIgnoreCase("")) {
			reportResults = scoreCardRepository.scoreCardReport_AllMacAllJuriProgramValue(Integer.valueOf(programId),fromDate, toDate);	
		} else if(macId.equalsIgnoreCase("ALL") && !jurisId.equalsIgnoreCase("")&& programId.equalsIgnoreCase("ALL") ) {
			reportResults = scoreCardRepository.scoreCardReport_AllMacValueJuriAllProgram(Integer.valueOf(jurisId), fromDate, toDate);	
		} else if(macId.equalsIgnoreCase("ALL") && !jurisId.equalsIgnoreCase("") && !programId.equalsIgnoreCase("") ) {
			reportResults = scoreCardRepository.scoreCardReport_AllMacValueJuriValueProgram(Integer.valueOf(programId),Integer.valueOf(jurisId), fromDate, toDate);	
		} else if(!macId.equalsIgnoreCase("") && jurisId.equalsIgnoreCase("ALL")&& programId.equalsIgnoreCase("ALL") ) {
			reportResults = scoreCardRepository.scoreCardReport_ValueMacAllJuriAllProgram(Integer.valueOf(macId), fromDate, toDate);	
		} else if(!macId.equalsIgnoreCase("") && !jurisId.equalsIgnoreCase("")&& programId.equalsIgnoreCase("ALL") ) {
			reportResults = scoreCardRepository.scoreCardReport_ValueMacValueJuriAllProgram(Integer.valueOf(macId),Integer.valueOf(jurisId), fromDate, toDate);	
		}else if(!macId.equalsIgnoreCase("") && !jurisId.equalsIgnoreCase("") && !programId.equalsIgnoreCase("") ){
			reportResults = scoreCardRepository.scoreCardReport(Integer.valueOf(macId), Integer.valueOf(jurisId), Integer.valueOf(programId),fromDate, toDate);			
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
	
	public List<CsrLog> retrieveComplianceReport(String macId, String jurisdiction, String complianceReportType, Date fromDate, Date toDate,
			ArrayList<Integer> macIdList, ArrayList<String> jurisdictionNameList, Long userId){
		List<CsrLog> reportResults = null;
		List<CsrLog> finalResultsList = null;
		
		Specifications< CsrLog > specifications = Specifications.where
				(CsrLogSpecifications.searchByJurisdiction(jurisdiction))
			.and(CsrLogSpecifications.searchByMacId(Integer.valueOf(macId)))				
			.and(CsrLogSpecifications.searchByMacIdList(macIdList))
			.and(CsrLogSpecifications.searchByJurisdictionNameList(jurisdictionNameList))
			.and(CsrLogSpecifications.searchByUserId(userId))				
			;
		
		reportResults = csrLogRepository.findAll(specifications);
		
		if(complianceReportType.equalsIgnoreCase("") || complianceReportType.equalsIgnoreCase("ALL")) {
			finalResultsList = reportResults;
		} else if (complianceReportType.equalsIgnoreCase("Compliant")) {
			finalResultsList = new ArrayList<CsrLog>();
			for(CsrLog csrLogTemp:reportResults) {
				if (csrLogTemp.getComplianceStatus() == 1) {
					finalResultsList.add(csrLogTemp);
				}
			}
			
		}  else if (complianceReportType.equalsIgnoreCase("Non-Compliant")) {
			finalResultsList = new ArrayList<CsrLog>();
			for(CsrLog csrLogTemp:reportResults) {
				if (csrLogTemp.getComplianceStatus() == 0) {
					finalResultsList.add(csrLogTemp);
				}
			}
			
		} 
		return finalResultsList;
	}	
	
	public List< CsrLog > searchComplianceReport( CsrLog csrLog ){
		
		Specifications< CsrLog > specifications = Specifications.where
					(CsrLogSpecifications.searchByJurisdiction(csrLog.getJurisdiction()))
				.and(CsrLogSpecifications.searchByMacId(csrLog.getMacId()))				
				.and(CsrLogSpecifications.searchByMacIdList(csrLog.getMacIdList()))
				.and(CsrLogSpecifications.searchByJurisdictionNameList(csrLog.getJurisdictionNameList()))
				.and(CsrLogSpecifications.searchByUserId(csrLog.getUserId()))				
				;
															
		return csrLogRepository.findAll(specifications);
	}
	
	public List<Rebuttal> retrieveRebuttalReportData(String macId, String jurisdiction, String callCategoryType, String rebuttalStatus, Date fromDate, Date toDate){
		List<Rebuttal> reportResults = null;
		List<Rebuttal> finalResultsList = null;
		
		
		
		if(macId.equalsIgnoreCase("ALL") && jurisdiction.equalsIgnoreCase("ALL")) {
			reportResults = rebuttalRepository.rebuttalReport_AllMacAllJuris(fromDate, toDate);	
		} else if(macId.equalsIgnoreCase("ALL") ) {
			reportResults = rebuttalRepository.rebuttalReport_AllMac(Integer.valueOf(jurisdiction), fromDate, toDate);	
		} else if(jurisdiction.equalsIgnoreCase("ALL")) {
			reportResults = rebuttalRepository.rebuttalReport_AllJuris(Integer.valueOf(macId), fromDate, toDate);	
		} else {
			reportResults = rebuttalRepository.rebuttalReport(Integer.valueOf(macId), Integer.valueOf(jurisdiction), fromDate, toDate);			
		}
		
		if(callCategoryType.equalsIgnoreCase("ALL") && rebuttalStatus.equalsIgnoreCase("ALL")) {
			finalResultsList = reportResults;
		} else if(callCategoryType.equalsIgnoreCase("ALL")) {
			finalResultsList = new ArrayList<Rebuttal>();
			for(Rebuttal rebuttalTemp:reportResults) {
				if (rebuttalTemp.getRebuttalStatus().equalsIgnoreCase(rebuttalStatus)) {
					finalResultsList.add(rebuttalTemp);
				}
			}
			
		}  else if (rebuttalStatus.equalsIgnoreCase("ALL")) {
			finalResultsList = new ArrayList<Rebuttal>();
			for(Rebuttal rebuttalTemp:reportResults) {
				if (rebuttalTemp.getCallCategory().equalsIgnoreCase(callCategoryType)) {
					finalResultsList.add(rebuttalTemp);
				}
			}
			
		} else {
			finalResultsList = new ArrayList<Rebuttal>();
			for(Rebuttal rebuttalTemp:reportResults) {
				if (rebuttalTemp.getCallCategory().equalsIgnoreCase(callCategoryType) && rebuttalTemp.getRebuttalStatus().equalsIgnoreCase(rebuttalStatus)) {
					finalResultsList.add(rebuttalTemp);
				}
			}
		}
		
		return finalResultsList;
	}	
}