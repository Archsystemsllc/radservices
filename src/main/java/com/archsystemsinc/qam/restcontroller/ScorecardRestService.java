/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.cmts.sec.util.GenericConstants;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.service.ScoreCardService;
import com.archsystemsinc.qam.service.mail.MailService;
	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class ScorecardRestService {
	private static final Logger log = Logger.getLogger(ScorecardRestService.class);
	
	@Autowired
	private ScoreCardService scoreCardService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@RequestMapping(value = "/searchScoreCard", method = RequestMethod.POST)
	public List<ScoreCard> searchScoreCard(@RequestBody  ScoreCard scoreCard){
		List<ScoreCard> data=null;
		try {
			log.debug("--> searchScoreCard:");
			data = scoreCardService.search(scoreCard);
			log.debug("<-- searchScoreCard");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	@RequestMapping(value = "/saveOrUpdateScoreCard", method = RequestMethod.POST)
	public @ResponseBody ScoreCard saveOrUpdateScoreCard(@RequestBody  ScoreCard scoreCard){
		log.debug("--> saveOrUpdateScoreCard:");		
		ScoreCard scoreCardResult = null;
		boolean newScorecard = false;
		try {
			if (scoreCard.getId() == 0) {
				newScorecard = true;
			}
			scoreCardResult = scoreCardService.saveOrUpdateScoreCard(scoreCard);
			
			if(newScorecard) {
				mailService.sendEmail(GenericConstants.EMAIL_TYPE_SC_CREATE, fromEmail, "nissar.msis@gmail.com,mmohammed@archsystemsinc.com,ashaik@archsystemsinc.com");
			} else {
				mailService.sendEmail(GenericConstants.EMAIL_TYPE_SC_UPDATE, fromEmail, "nissar.msis@gmail.com,mmohammed@archsystemsinc.com,ashaik@archsystemsinc.com");
			}
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return scoreCardResult;
			
		}
		log.debug("<-- saveOrUpdateScoreCard");
		return scoreCardResult;
	}
	
	@RequestMapping(value = "/retrieveMacCallRefFailList", method = RequestMethod.POST)
	public List<ScoreCard> retrieveMacCallRefFailList(@RequestBody  ScoreCard scoreCard){
		log.debug("--> retrieveMacCallRefFailList:");		
		List<ScoreCard> scoreCardFailList = null;
		
		try {
			scoreCardFailList = scoreCardService.retrieveFailedCallListByMacIdJurisId(scoreCard);
			
		} catch (Exception e) {
			log.error("Error while retrieving failed list",e);
			scoreCardFailList = null;			
		}
		log.debug("<-- retrieveMacCallRefFailList");
		return scoreCardFailList;
	}
}