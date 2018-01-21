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
	
	
	@RequestMapping(value = "/getMacJurisReport", method = RequestMethod.POST)
	public @ResponseBody HashMap<Integer, ScoreCard> getMacJurisReport(@RequestBody  ReportsForm reportsForm){
		List<ScoreCard> data=null;
		HashMap <Integer, ScoreCard> resultsMap = new HashMap<Integer, ScoreCard> ();
		try {
			log.debug("--> getMacJurisReport:");
			data = reportsService.retrieveMacJurisReport(reportsForm.getMacId(), reportsForm.getJurisId(), 
					reportsForm.getFromDate(), reportsForm.getToDate(), reportsForm.getScoreCardType(), reportsForm.getCallResult());
			
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
	
	
}