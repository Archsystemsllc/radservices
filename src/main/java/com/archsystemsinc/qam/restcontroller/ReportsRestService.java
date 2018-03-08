/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.model.ReportsForm;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.service.ReportsService;
import com.archsystemsinc.qam.service.ScoreCardService;
	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class ReportsRestService {
	private static final Logger log = Logger.getLogger(ReportsRestService.class);
	
	@Autowired
	private ReportsService reportsService;
	
	@Autowired
	private ScoreCardService scoreCardService;
	
	
	@RequestMapping(value = "/getMacJurisReport", method = RequestMethod.POST)
	public @ResponseBody HashMap<Integer, ScoreCard> getMacJurisReport(@RequestBody  ReportsForm reportsForm){
		List<ScoreCard> data=null;
		HashMap <Integer, ScoreCard> resultsMap = new HashMap<Integer, ScoreCard> ();
		ScoreCard scoreCardReportObject = new ScoreCard();
		try {
			log.debug("--> getMacJurisReport:");
			if(reportsForm.getMacId() !=null && !reportsForm.getMacId().equalsIgnoreCase("")) {
				scoreCardReportObject.setMacIdReportSearchString(reportsForm.getMacId());
			}
			if(!reportsForm.getJurisId().equalsIgnoreCase("")) {
				scoreCardReportObject.setJurisIdReportSearchString(reportsForm.getJurisId());
			}
			if(!reportsForm.getProgramId().equalsIgnoreCase("")) {
				scoreCardReportObject.setProgramIdReportSearchString(reportsForm.getProgramId());
			}		
			
			scoreCardReportObject.setFilterFromDate(reportsForm.getFromDate());
			scoreCardReportObject.setFilterToDate(reportsForm.getToDate());
			scoreCardReportObject.setScorecardType(reportsForm.getScoreCardType());
			scoreCardReportObject.setCallResult(reportsForm.getCallResult());
			scoreCardReportObject.setJurIdList(reportsForm.getJurIdList());
						
			data = scoreCardService.search(scoreCardReportObject);
			
			for(ScoreCard scoreCard: data) {
				resultsMap.put(scoreCard.getId(), scoreCard);
			}
			log.debug("<-- getMacJurisReport");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultsMap;
	}		
	
	@RequestMapping(value = "/getComplianceReport", method = RequestMethod.POST)
	public @ResponseBody HashMap<Long, CsrLog> getComplianceReportData(@RequestBody  ReportsForm reportsForm){
		List<CsrLog> data=null;
		HashMap <Long, CsrLog> resultsMap = new HashMap<Long, CsrLog> ();
		Long userId = null;
		try {
			log.debug("--> getComplianceReportData:");
			if(reportsForm.getUserId() !=null) {
				userId = Long.valueOf(reportsForm.getUserId());
			}
			 
			data = reportsService.retrieveComplianceReport(reportsForm.getMacId(), reportsForm.getJurisdictionName(), reportsForm.getComplianceReportType(),
					reportsForm.getFromDate(), reportsForm.getToDate(), reportsForm.getMacIdList(), reportsForm.getJurisdictionNameList(), userId);
			
			for(CsrLog csrLog: data) {
				resultsMap.put(csrLog.getId(), csrLog);
			}
			log.debug("<-- getComplianceReportData");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultsMap;
	}	
	
	@RequestMapping(value = "/getRebuttalReport", method = RequestMethod.POST)
	public @ResponseBody HashMap<Integer, Rebuttal> getRebuttalReportData(@RequestBody  ReportsForm reportsForm){
		List<Rebuttal> data=null;
		HashMap <Integer, Rebuttal> resultsMap = new HashMap<Integer, Rebuttal> ();
		try {
			log.debug("--> getRebuttalReportData:");
			data = reportsService.retrieveRebuttalReportData(reportsForm.getMacId(), reportsForm.getJurisId(), reportsForm.getCallCategoryType(), reportsForm.getRebuttalStatus(),
					reportsForm.getFromDate(), reportsForm.getToDate());
			
			for(Rebuttal rebuttal: data) {
				resultsMap.put(rebuttal.getId(), rebuttal);
			}
			log.debug("<-- getRebuttalReportData");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultsMap;
	}
}