/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.sec.util.GenericConstants;
import com.archsystemsinc.qam.service.ScoreCardService;
import com.archsystemsinc.qam.service.mail.MailService;
import com.archsystemsinc.qam.utils.CommonUtils;
import com.archsystemsinc.qam.utils.EmailObject;
	
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
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	@RequestMapping(value = "/searchScoreCard", method = RequestMethod.POST)
	public @ResponseBody List<ScoreCard> searchScoreCard(@RequestBody  ScoreCard scoreCard){
		List<ScoreCard> data=null;
		List<ScoreCard> finalDataList=new ArrayList<ScoreCard>();
		try {
			log.debug("--> searchScoreCard:");
			if(scoreCard.getMacAssignmentSearchString() != null && !scoreCard.getMacAssignmentSearchString().equalsIgnoreCase("")) {
				
			}
			
			if (scoreCard.getFilterFromDateString() != null && !scoreCard.getFilterFromDateString().equalsIgnoreCase("")) {
				scoreCard.setFilterFromDate(CommonUtils.convertToDateFromString(scoreCard.getFilterFromDateString(),GenericConstants.DATE_TYPE_FULL));
			}
			if (scoreCard.getFilterToDateString() != null && !scoreCard.getFilterToDateString().equalsIgnoreCase("")) {
				scoreCard.setFilterToDate(CommonUtils.convertToDateFromString(scoreCard.getFilterToDateString(),GenericConstants.DATE_TYPE_FULL));
			}
			
			
			data = scoreCardService.search(scoreCard);
			log.debug("<-- searchScoreCard");			
			
			for(ScoreCard scoreCardTemp: data) {
				//scoreCardTemp.setCallMonitoringDate(scoreCardTemp.getCallMonitoringDate().plusDays(1));
				String callMonitoringDateString = CommonUtils.convertToStringFromDate(scoreCardTemp.getCallMonitoringDate(), GenericConstants.DATE_TYPE_ONLY_DATE);
				scoreCardTemp.setCallMonitoringDateString(callMonitoringDateString);
				scoreCardTemp.setCallMonitoringDate(null);
				finalDataList.add( scoreCardTemp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalDataList;
	}		
	
	@RequestMapping(value = "/saveOrUpdateScoreCard", method = RequestMethod.POST)
	public @ResponseBody ScoreCard saveOrUpdateScoreCard(@RequestBody  ScoreCard scoreCard){
		log.debug("--> saveOrUpdateScoreCard:");		
		ScoreCard scoreCardResult = null;
		boolean newScorecard = false;
		EmailObject emailObject = null;
		try {
			if (scoreCard.getId() == 0) {
				newScorecard = true;
			}
			scoreCard.setCallMonitoringDate(CommonUtils.convertToDateFromString(scoreCard.getCallMonitoringDateString(), GenericConstants.DATE_TYPE_FULL));
			scoreCardResult = scoreCardService.saveOrUpdateScoreCard(scoreCard);
			String callMonitoringDateString = CommonUtils.convertToStringFromDate(scoreCardResult.getCallMonitoringDate(), GenericConstants.DATE_TYPE_ONLY_DATE);
			scoreCardResult.setCallMonitoringDateString(callMonitoringDateString);
			scoreCardResult.setCallMonitoringDate(null);
			String link = radUIEndPoint +"quality_manager/view-scorecard/"+scoreCard.getId();
			
			if(newScorecard) {
				emailObject = new EmailObject();
				emailObject.setFromEmail(fromEmail);
				emailObject.setEmailType(GenericConstants.EMAIL_TYPE_SC_CREATE);
				emailObject.setToEmail("nissar.msis@gmail.com,sheiknissu4@gmail.com");
				emailObject.setMacName(scoreCard.getMacName());
				emailObject.setJurisidctionName(scoreCard.getJurisdictionName());
				emailObject.setLink(link);
				mailService.sendEmail(emailObject);
			} else {
				
				emailObject = new EmailObject();
				emailObject.setFromEmail(fromEmail);
				emailObject.setEmailType(GenericConstants.EMAIL_TYPE_SC_UPDATE);
				emailObject.setToEmail("nissar.msis@gmail.com,sheiknissu4@gmail.com");
				emailObject.setMacName(scoreCard.getMacName());
				emailObject.setJurisidctionName(scoreCard.getJurisdictionName());
				emailObject.setLink(link);
				mailService.sendEmail(emailObject);
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
		List<ScoreCard> finalDataList=new ArrayList<ScoreCard>();
		try {
			scoreCardFailList = scoreCardService.retrieveFailedCallListByMacIdJurisId(scoreCard);
			
			for(ScoreCard scoreCardTemp: scoreCardFailList) {
				//scoreCardTemp.setCallMonitoringDate(scoreCardTemp.getCallMonitoringDate().plusDays(1));
				String callMonitoringDateString = CommonUtils.convertToStringFromDate(scoreCardTemp.getCallMonitoringDate(), GenericConstants.DATE_TYPE_ONLY_DATE);
				scoreCardTemp.setCallMonitoringDateString(callMonitoringDateString);
				scoreCardTemp.setCallMonitoringDate(null);
				finalDataList.add( scoreCardTemp);
			}
			
		} catch (Exception e) {
			log.error("Error while retrieving failed list",e);
			scoreCardFailList = null;			
		}
		log.debug("<-- retrieveMacCallRefFailList");
		return finalDataList;
	}
}