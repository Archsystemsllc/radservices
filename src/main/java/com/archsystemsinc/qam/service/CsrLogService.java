/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.repository.CsrLogRepository;
import com.archsystemsinc.qam.repository.specifications.CsrLogSpecifications;
import com.archsystemsinc.qam.sec.util.GenericConstants;
import com.archsystemsinc.qam.utils.EmailObject;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class CsrLogService {
	private static final Logger log = Logger.getLogger(CsrLogService.class);
	@Autowired
	private CsrLogRepository csrLogRepository;
	
	
	public List<CsrLog> search(CsrLog csrLog){
		List<CsrLog> reportResults = null;
		List<CsrLog> finalResultsList = null;
		
		Specifications< CsrLog > specifications = Specifications.where
				(CsrLogSpecifications.searchByJurisdiction(csrLog.getJurisdiction()))
			.and(CsrLogSpecifications.searchByMacId(Integer.valueOf(csrLog.getMacId())))				
			.and(CsrLogSpecifications.searchByMacIdList(csrLog.getMacIdList()))
			.and(CsrLogSpecifications.searchByJurisdictionNameList(csrLog.getJurisdictionNameList()))
			.and(CsrLogSpecifications.searchByDateMonth(csrLog.getCreatedDate()))
			.and(CsrLogSpecifications.searchByUserId(csrLog.getUserId()))				
			;
		
		reportResults = csrLogRepository.findAll(specifications);		
		
		return reportResults;
	}	
	
	public CsrLog saveOrUpdateCsrLog(CsrLog csrLog) {
		csrLog = csrLogRepository.save(csrLog);
		return csrLog;
	}
	
	
}
