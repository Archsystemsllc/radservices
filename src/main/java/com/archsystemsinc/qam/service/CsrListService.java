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
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.exception.FileUploadException;
import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.repository.CsrListRepository;
import com.archsystemsinc.qam.repository.CsrLogRepository;
import com.archsystemsinc.qam.repository.specifications.CsrListsSpecifications;
import com.archsystemsinc.qam.repository.specifications.ScoreCardSpecifications;
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
	public String uploadFileData(MultipartFile uploadedFile, Long userId, String keepCurrentList, Long macId, String jurisdiction) throws FileUploadException,Exception{
		List<CsrLists> data;
		CsrLog clog = constructCsrLog(userId,macId.intValue(),jurisdiction);
		String validationResult = "";
		try {
			Integer yearMonth = CommonUtils.getCurrentYearMonth();
			if("true".equals(keepCurrentList)) {
				
				List<CsrLists> existingRows = existingCsrListByMacJurisdictionMonthYear(macId,jurisdiction,yearMonth-1);
				if(existingRows.size() == 0) {
					validationResult = "For previous month there is no CSR Lists data available!";
				}else {
					data = copyCsrLists(existingRows);
					processCsrLists(userId, data, yearMonth,macId,jurisdiction);
				}
			}else {
				data = poiUtils.parseCsrListFile(uploadedFile,userId,macId);
				validationResult = validateCsrLists(data);
				if (validationResult.equalsIgnoreCase("ValidationSuccessful")) {
					processCsrLists(userId, data, yearMonth,macId,jurisdiction);
					clog.setUploadStatus(1l);
					validationResult ="CSR List uploaded successfully";
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUploadException(e.getMessage());
		}finally {
			if(CommonUtils.isUploadCompliance()) {
				clog.setComplianceStatus(1l);
			}
			csrLogRepository.save(clog);
		}
		return validationResult;		
	}
	
	private String validateCsrLists(List<CsrLists> data) {
		String firstNameValidation = "Missing Value/s in First Name at Row/s:";
		String lastNameValidation = "Missing Value/s in Last Name at Row/s:";
		String pccValidation = "Missing Value/s in PCC at Row/s:";
		String csrLevelValidation = "Missing Value/s in CSR Level at Row/s:";
		String jurisdictionValidation = "Missing Value/s in Jurisdiction at Row/s:";
		String programValidation = "Missing Value/s in Program at Row/s:";		
		String statusValidation = "Missing Value/s in Status at Row/s:";
		
		String validationResult = "";
		int rowNum = 1;
		for (CsrLists csrList: data) {
			if(csrList.getFirstName().equalsIgnoreCase("")) {
				firstNameValidation+=rowNum+":";
			}
			if(csrList.getLastName().equalsIgnoreCase("")) {
				lastNameValidation+=rowNum+":";
			}
			if(csrList.getLocation().equalsIgnoreCase("")) {
				pccValidation+=rowNum+":";
			}
			if(csrList.getLevel().equalsIgnoreCase("")) {
				csrLevelValidation+=rowNum+":";
			}
			
			if(csrList.getJurisdiction().equalsIgnoreCase("")) {
				jurisdictionValidation+=rowNum+":";
			}
			
			if(csrList.getProgram().equalsIgnoreCase("")) {
				programValidation+=rowNum+":";
			}
			
			if(csrList.getStatus().equalsIgnoreCase("")) {
				statusValidation+=rowNum+":";
			}
			rowNum ++;			
		}
		
		if(!firstNameValidation.equalsIgnoreCase("Missing Value/s in First Name at Row/s:")) {
			validationResult += firstNameValidation;
		}
		if(!lastNameValidation.equalsIgnoreCase("Missing Value/s in Last Name at Row/s:")) {
			validationResult += programValidation;
		}
		
		if(!pccValidation.equalsIgnoreCase("Missing Value/s in PCC at Row/s:")) {
			validationResult += lastNameValidation;
		}
		if(!csrLevelValidation.equalsIgnoreCase("Missing Value/s in CSR Level at Row/s:")) {
			validationResult += csrLevelValidation;
		}
		
		if(!jurisdictionValidation.equalsIgnoreCase("Missing Value/s in Jurisdiction at Row/s:")) {
			validationResult += jurisdictionValidation;
		}
		if(!programValidation.equalsIgnoreCase("Missing Value/s in Program at Row/s:")) {
			validationResult += programValidation;
		}
		
		if(!statusValidation.equalsIgnoreCase("Missing Value/s in Status at Row/s:")) {
			validationResult += statusValidation;
		}
		if(validationResult.equalsIgnoreCase("")) {
			validationResult ="ValidationSuccessful";
		} else {
			validationResult+="Please upload a new file after fixing the issues";
		}
		return validationResult;
	}

	private void processCsrLists(Long userId, List<CsrLists> data, Integer yearMonth,Long macId, String jurisdiction) {
		List<CsrLists> existingRows1 = existingCsrListByMacJurisdictionMonthYear(macId, jurisdiction, yearMonth);
		if(existingRows1.size() > 0) {
			int count = csrListRepository.markStatusDeletedForAdmin(0l, macId, yearMonth, new Date(), jurisdiction);
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
			list.setFirstName(lists.getFirstName());
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

	private CsrLog constructCsrLog(Long userId, Integer macId, String jurisdiction) {
		CsrLog clog = new CsrLog();
		clog.setComplianceStatus(0l);
		clog.setUploadStatus(0l);
		clog.setUserId(userId);
		clog.setCreatedDate(new Date());
		clog.setMacId(macId);
		clog.setJurisdiction(jurisdiction);
		return clog;
	}
	
	public List<CsrLists> existingCsrListByUserMonthYear(Long userId, Integer yearMonth){
		log.debug("existingCsrListByUserMonthYear::"+userId+","+yearMonth);
		return csrListRepository.existingCsrListByUserMonthYear(userId, yearMonth);
	}
	
	public List<CsrLists> existingCsrListByMacMonthYear(Long macId,String jurisdiction,Integer yearMonth){
		log.debug("existingCsrListByMacMonthYear::"+macId+","+yearMonth);
		return csrListRepository.existingCsrListByMacJurisdictionMonthYear(macId, jurisdiction, yearMonth);
	}
	
	public List<CsrLists> existingCsrListByMacJurisdictionMonthYear(Long macId, String jurisdiction,Integer yearMonth){
		log.debug("existingCsrListByMacMonthYear::"+macId+","+yearMonth);
		return csrListRepository.existingCsrListByMacJurisdictionMonthYear(macId, jurisdiction, yearMonth);
	}

	public List<CsrLists> getCsrList(String from, String to,String macLookupIds,String jurisdictions) {
		
		List<CsrLists> resultsList = null;
		String[] fromMonthyear = from.split("-");
		String fromYear = fromMonthyear[0];
		String fromMonth = fromMonthyear[1];
		log.debug("fromMonth::"+fromMonth);
		log.debug("fromYear::"+fromYear);
		
		String[] toMonthyear = to.split("-");
		String toYear = toMonthyear[0];
		String toMonth = toMonthyear[1];
		log.debug("toMonth::"+toMonth);
		log.debug("fromYear::"+toYear);
		
		String[] macLookupIdStringList = macLookupIds.split(",");
		ArrayList<Long> macLookupIdArrayList = new ArrayList<Long>();
		
		boolean macAllFlag = false;
		if(macLookupIdStringList.length != 0) {
			
			for(String macLookupIdString: macLookupIdStringList) {
				macLookupIdString = macLookupIdString.substring(1,macLookupIdString.length()-1);
				if(macLookupIdString.equalsIgnoreCase("ALL")) {
					macAllFlag = true;
					break;
				} else {
					macLookupIdArrayList.add (Long.valueOf(macLookupIdString));
					
				}
			}
		}
		
		String[] jurisdictionStringList = jurisdictions.split(",");
		ArrayList<String> jurisdictionArrayList = new ArrayList<String>();
		
		boolean jurisdictionAllFlag = false;
		if(jurisdictionStringList.length != 0) {
			
			for(String jurisdictionString: jurisdictionStringList) {
				jurisdictionString=jurisdictionString.substring(1,jurisdictionString.length()-1);
				if(jurisdictionString.equalsIgnoreCase("Select ALL")) {
					jurisdictionAllFlag = true;
					break;
				} else {
					jurisdictionArrayList.add(jurisdictionString);
					
				}
			}
		}
		
		if(macAllFlag && jurisdictionAllFlag) {
			resultsList = csrListRepository.findByMonthYearRangeAll(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth));
		} else if (macAllFlag) {
			resultsList = csrListRepository.findByMonthYearRangeAllMac(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), jurisdictionArrayList);
		} else if (jurisdictionAllFlag) {
			resultsList = csrListRepository.findByMonthYearRangeAllJuris(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), macLookupIdArrayList);
		} else {
			resultsList = csrListRepository.findByMonthYearRange(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), macLookupIdArrayList, jurisdictionArrayList);
		}
		
		return resultsList;
	}
	
	public List<Object[]> getCsrListMonths(String from, String to, String macLookupIds,String jurisdictions) {
		
		List<Object[]> resultsList = null;
		
		String[] fromMonthyear = from.split("-");
		String fromYear = fromMonthyear[0];
		String fromMonth = fromMonthyear[1];
		log.debug("fromMonth::"+fromMonth);
		log.debug("fromYear::"+fromYear);
		
		String[] toMonthyear = to.split("-");
		String toYear = toMonthyear[0];
		String toMonth = toMonthyear[1];
		log.debug("toMonth::"+toMonth);
		log.debug("fromYear::"+toYear);
		
		String[] macLookupIdStringList = macLookupIds.split(",");
		ArrayList<Long> macLookupIdArrayList = new ArrayList<Long>();
		
		boolean macAllFlag = false;
		if(macLookupIdStringList.length != 0) {
			
			for(String macLookupIdString: macLookupIdStringList) {
				macLookupIdString = macLookupIdString.substring(1,macLookupIdString.length()-1);
				if(macLookupIdString.equalsIgnoreCase("ALL")) {
					macAllFlag = true;
					break;
				} else {
					macLookupIdArrayList.add (Long.valueOf(macLookupIdString));
				}
			}
		}
		
		String[] jurisdictionStringList = jurisdictions.split(",");
		ArrayList<String> jurisdictionArrayList = new ArrayList<String>();
		
		boolean jurisdictionAllFlag = false;
		if(jurisdictionStringList.length != 0) {
			
			for(String jurisdictionString: jurisdictionStringList) {
				jurisdictionString=jurisdictionString.substring(1,jurisdictionString.length()-1);
				if(jurisdictionString.equalsIgnoreCase("Select ALL")) {
					jurisdictionAllFlag = true;
					break;
				} else {
					//String jurisName = jurisdictionService.
					jurisdictionArrayList.add(jurisdictionString);
					
				}
			}
		}
		
		if(macAllFlag && jurisdictionAllFlag) {
			resultsList = csrListRepository.findMonthsByMonthYearRangeAll(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth));
		} else if (macAllFlag) {
			resultsList = csrListRepository.findMonthsByMonthYearRangeAllMac(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), jurisdictionArrayList);
		} else if (jurisdictionAllFlag) {
			resultsList = csrListRepository.findMonthsByMonthYearRangeAllJuris(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), macLookupIdArrayList);
		} else {
			resultsList = csrListRepository.findMonthsByMonthYearRange(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), macLookupIdArrayList, jurisdictionArrayList);
		}
		
		return resultsList;
	}
	
	
	public List<CsrLists> getCsrNames(String nameLiteral,Long macLookupId,String jurisdiction, String program) {
		
		List<CsrLists> tempCsrList = null;		
		
		try {
			boolean macAllFlag = false;
			
			tempCsrList = csrListRepository.existingCsrListByMacIdJurisProgram(nameLiteral,macLookupId,jurisdiction,program);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return tempCsrList;
	}

	public List< CsrLists > search( CsrLists csrLists ){
	
	Specifications< CsrLists > specifications = Specifications.where
				(CsrListsSpecifications.searchByJurisdiction(csrLists.getJurisdiction()))
			.and(CsrListsSpecifications.searchByMacId(csrLists.getMacLookupId().intValue()))				
			.and(CsrListsSpecifications.searchByProgram(csrLists.getProgram()))
			.and(CsrListsSpecifications.findByCreatedDateBetween(csrLists.getCreatedDate(), null))
			.and(CsrListsSpecifications.searchByRecordStatus(csrLists.getRecordStatus()))			
			;
														
	return csrListRepository.findAll(specifications);
}
}
