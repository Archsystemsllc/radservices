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

import com.archsystemsinc.qam.model.ProgramLookup;
import com.archsystemsinc.qam.service.ProgramLookupService;
import com.archsystemsinc.qam.service.mail.MailService;

	
	/**
 * @author Abdul Nissar Shaik
 *
 */
@RestController
@RequestMapping("api")
public class ProgramRestService {
	private static final Logger log = Logger.getLogger(ProgramRestService.class);
	
	@Autowired
	private ProgramLookupService programService;
	
	@Autowired
    MailService mailService;
	
	@Value("${mail.fromEmail}")
    String fromEmail;
	
	@Value("${radui.endpoint}")
    String radUIEndPoint;
	
	@RequestMapping(value = "/programList", method = RequestMethod.POST)
	public List<ProgramLookup> getProgramList(@RequestBody ProgramLookup programSearchObject){
		List<ProgramLookup> data=null;
		
		try {
			log.debug("--> getProgramLookupList:");
			
			data = programService.findAll();
			log.debug("<-- getProgramLookupList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}		
	
	
								
	@RequestMapping(value = "/saveOrUpdateProgram", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateProgram(
			@RequestBody ProgramLookup programObject){
		log.debug("--> saveOrUpdateProgram:");		
		
		try {
			
			programService.saveOrUpdateProgram(programObject);
		} catch (Exception e) {
			log.error("Error while uploading data",e);
			return "Failure";
			
			
		}
		log.debug("<-- saveOrUpdateProgram");
		return "Success";
	}	
	
	@RequestMapping(value = "/searchProgram", method = RequestMethod.POST)
	public @ResponseBody ProgramLookup searchProgram(@RequestBody  ProgramLookup program){
		
		try {
			log.debug("--> searchScoreCard:");
			
			program = programService.findById(program.getId());
					
			log.debug("<-- searchScoreCard");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return program;
	}		
}