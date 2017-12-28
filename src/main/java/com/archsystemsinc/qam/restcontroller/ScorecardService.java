/**
 * 
 */
package com.archsystemsinc.qam.restcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.service.ScoreCardService;
import com.archsystemsinc.qam.utils.UploadResponse;
	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class ScorecardService {
	private static final Logger log = Logger.getLogger(ScorecardService.class);
	
	@Autowired
	private ScoreCardService scoreCardService;
	
	
	@RequestMapping(value = "/scorecardlist", method = RequestMethod.GET)
	public List<ScoreCard> getScoreCardList(){
		List<ScoreCard> data=null;
		try {
			log.debug("--> getScoreCardList:");
			data = scoreCardService.findAll();
			log.debug("<-- getScoreCardList");
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
		
		try {
			scoreCardResult = scoreCardService.saveOrUpdateScoreCard(scoreCard);
			
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return scoreCardResult;
			
		}
		log.debug("<-- saveOrUpdateScoreCard");
		return scoreCardResult;
	}
}