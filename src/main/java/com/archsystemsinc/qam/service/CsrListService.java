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
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.exception.FileUploadException;
import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.repository.CsrListRepository;
import com.archsystemsinc.qam.repository.CsrLogRepository;
import com.archsystemsinc.qam.utils.CommonUtils;
import com.archsystemsinc.qam.utils.PoiUtils;

/**
 * @author Prakash T
 *
 */
@Service
@Transactional
public class CsrListService {
	private static final Logger log = Logger.getLogger(CsrListService.class);
	@Autowired
	private CsrListRepository csrListRepository;
	
	@Autowired
	private CsrLogRepository csrLogRepository;
	
	@Autowired
	private PoiUtils poiUtils;

	
	public void createCsrList(List<CsrLists> data){
		csrListRepository.save(data);
	}
	
	/**
	 * 
	 * @param uploadedFile
	 * @param userId
	 * @param keepCurrentList 
	 * @throws Exception
	 */
	public void uploadFileData(MultipartFile uploadedFile, Long userId, String keepCurrentList) throws FileUploadException,Exception{
		List<CsrLists> data;
		CsrLog clog = constructCsrLog(userId);
		try {
			Integer yearMonth = CommonUtils.getCurrentYearMonth();
			if("true".equals(keepCurrentList)) {
				List<CsrLists> existingRows = existingCsrListByUserMonthYear(userId,yearMonth-1);
				if(existingRows.size() == 0) {
					throw new FileUploadException("For previous month there is no CSR Lists data available!");
				}else {
					data = copyCsrLists(existingRows);
					processCsrLists(userId, data, yearMonth);
				}
			}else {
				data = poiUtils.parseCsrListFile(uploadedFile,userId);
				processCsrLists(userId, data, yearMonth);
			}	
			
			clog.setUploadStatus(1l);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUploadException(e.getMessage());
		}finally {
			if(CommonUtils.isUploadCompliance()) {
				clog.setComplianceStatus(1l);
			}
			csrLogRepository.save(clog);
		}
		
	}

	private void processCsrLists(Long userId, List<CsrLists> data, Integer yearMonth) {
		List<CsrLists> existingRows1 = existingCsrListByUserMonthYear(userId,yearMonth);
		if(existingRows1.size() > 0) {
			int count = csrListRepository.markStatusDeleted(0l, userId, yearMonth, new Date());
			log.debug("updated count:"+count);
		}
		createCsrList(data);
	}

	private List<CsrLists> copyCsrLists(List<CsrLists> existingRows) {
		List<CsrLists> data = new ArrayList<CsrLists>();
		CsrLists list = null;
		for(CsrLists lists: existingRows) {
			list = new CsrLists();
			list.setCreatedBy(lists.getCreatedBy());
			list.setCreatedDate(new Date());
			list.setFisrtName(lists.getFisrtName());
			list.setJurisdiction(lists.getJurisdiction());
			list.setLastName(lists.getLastName());
			list.setLevel(lists.getLevel());
			list.setLocation(lists.getLocation());
			list.setMacLookupId(lists.getMacLookupId());
			list.setMiddleName(lists.getMiddleName());
			list.setProgram(lists.getProgram());
			list.setRecordStatus(1l);
			list.setStatus(lists.getStatus());
			list.setUpdatedBy(lists.getUpdatedBy());
			list.setUpdateddDate(new Date());
			list.setUserId(lists.getUserId());
			data.add(list);
		}
		return data;
	}

	private CsrLog constructCsrLog(Long userId) {
		CsrLog clog = new CsrLog();
		clog.setComplianceStatus(0l);
		clog.setUploadStatus(0l);
		clog.setUserId(userId);
		clog.setCreatedDate(new Date());
		return clog;
	}
	
	public List<CsrLists> existingCsrListByUserMonthYear(Long userId, Integer yearMonth){
		log.debug("countCsrListByUserMonthYear::"+userId+","+yearMonth);
		return csrListRepository.existingCsrListByUserMonthYear(userId, yearMonth);
	}

	public List<CsrLists> getCsrList(String from, String to) {
		String[] fromMonthyear = from.split(" ");
		String fromMonth = CommonUtils.monthMap.get(fromMonthyear[0]);
		String fromYear = fromMonthyear[1];
		log.debug("fromMonth::"+fromMonth);
		log.debug("fromYear::"+fromYear);
		
		String[] toMonthyear = to.split(" ");
		String toMonth = CommonUtils.monthMap.get(toMonthyear[0]);
		String toYear = toMonthyear[1];
		log.debug("toMonth::"+toMonth);
		log.debug("fromYear::"+toYear);
		
		return csrListRepository.findByMonthYearRange(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth));
	}
}
