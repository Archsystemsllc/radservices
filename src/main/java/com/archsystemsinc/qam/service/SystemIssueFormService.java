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

import com.archsystemsinc.qam.model.SystemIssueForm;
import com.archsystemsinc.qam.repository.SystemIssueFormRepository;
import com.archsystemsinc.qam.utils.CommonUtils;


/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class SystemIssueFormService {
	private static final Logger log = Logger.getLogger(SystemIssueFormService.class);
	@Autowired
	private SystemIssueFormRepository SystemIssueFormRepository;
	

	public SystemIssueForm createSystemIssueForm(SystemIssueForm data){
		
		try {
			Integer yearMonth = CommonUtils.getCurrentYearMonth();
			SystemIssueFormRepository.markStatusDeleted(0l, data.getUserId(), yearMonth, new Date(), data.getMacLookupId(), data.getJurisdictionId());
			
			data = SystemIssueFormRepository.save(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public SystemIssueForm getSystemIssueForm(Long id){
		SystemIssueForm SystemIssueForm = null;
		try {
				
			SystemIssueForm = SystemIssueFormRepository.getOne(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SystemIssueForm;
	}
	
	public List<Object[]> getQamListMonths(String from, String to, String macLookupIds,String jurisdictions) {
		
		List<Object[]> resultsList = null;
		
		try {
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
			ArrayList<Long> jurisdictionArrayList = new ArrayList<Long>();
			
			boolean jurisdictionAllFlag = false;
			if(jurisdictionStringList.length != 0) {
				
				for(String jurisdictionString: jurisdictionStringList) {
					jurisdictionString=jurisdictionString.substring(1,jurisdictionString.length()-1);
					if(jurisdictionString.equalsIgnoreCase("Select ALL")) {
						jurisdictionAllFlag = true;
						break;
					} else {
						//String jurisName = jurisdictionService.
						jurisdictionArrayList.add(Long.valueOf(jurisdictionString));
						
					}
				}
			}
			
			if(macAllFlag && jurisdictionAllFlag) {
				resultsList = SystemIssueFormRepository.findMonthsByMonthYearRangeAll(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth));
			} else if (macAllFlag) {
				resultsList = SystemIssueFormRepository.findMonthsByMonthYearRangeAllMac(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), jurisdictionArrayList);
			} else if (jurisdictionAllFlag) {
				resultsList = SystemIssueFormRepository.findMonthsByMonthYearRangeAllJuris(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), macLookupIdArrayList);
			} else {
				resultsList = SystemIssueFormRepository.findMonthsByMonthYearRange(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), macLookupIdArrayList, jurisdictionArrayList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultsList;
	}
	
	
	public List<Object[]> getQamEnvList(String from, String to,String macLookupIds,String jurisdictions) {
		
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
		ArrayList<Long> jurisdictionArrayList = new ArrayList<Long>();
		
		boolean jurisdictionAllFlag = false;
		if(jurisdictionStringList.length != 0) {
			
			for(String jurisdictionString: jurisdictionStringList) {
				jurisdictionString=jurisdictionString.substring(1,jurisdictionString.length()-1);
				if(jurisdictionString.equalsIgnoreCase("Select ALL")) {
					jurisdictionAllFlag = true;
					break;
				} else {
					jurisdictionArrayList.add(Long.valueOf(jurisdictionString));
					
				}
			}
		}
		
		if(macAllFlag && jurisdictionAllFlag) {
			resultsList = SystemIssueFormRepository.findByMonthYearRangeAll(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth));
		} else if (macAllFlag) {
			resultsList = SystemIssueFormRepository.findByMonthYearRangeAllMac(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), jurisdictionArrayList);
		} else if (jurisdictionAllFlag) {
			resultsList = SystemIssueFormRepository.findByMonthYearRangeAllJuris(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), macLookupIdArrayList);
		} else {
			resultsList = SystemIssueFormRepository.findByMonthYearRange(new Integer(fromYear+fromMonth), new Integer(toYear+toMonth), macLookupIdArrayList, jurisdictionArrayList);
		}
		
		return resultsList;
	}


	/**
	 * 
	 * @param uploadedFile
	 * @param userId
	 * @param keepCurrentList 
	 * @throws Exception
	 *//*
	public String uploadFileData(MultipartFile uploadedFile, Long userId, String keepCurrentList, Long macId, String jurisdiction) throws FileUploadException,Exception{
		List<SystemIssueForm> data;
		CsrLog clog = constructCsrLog(userId,macId.intValue(),jurisdiction);
		String validationResult = "";
		try {
			Integer yearMonth = CommonUtils.getCurrentYearMonth();
			if("true".equals(keepCurrentList)) {
				
				List<SystemIssueForm> existingRows = existingSystemIssueFormByMacJurisdictionMonthYear(macId,jurisdiction,yearMonth-1);
				if(existingRows.size() == 0) {
					validationResult = "For previous month there is no CSR Lists data available!";
				}else {
					data = copySystemIssueForm(existingRows);
					processSystemIssueForm(userId, data, yearMonth,macId,jurisdiction);
				}
			}else {
				data = poiUtils.parseCsrListFile(uploadedFile,userId,macId);
				validationResult = validateSystemIssueForm(data);
				if (validationResult.equalsIgnoreCase("ValidationSuccessful")) {
					processSystemIssueForm(userId, data, yearMonth,macId,jurisdiction);
					clog.setUploadStatus(1l);
					validationResult ="CSR List Uploaded Successfully";
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
	
	private String validateSystemIssueForm(List<SystemIssueForm> data) {
		String firstNameValidation = "Missing Value/s in First Name at Row/s:";
		String lastNameValidation = "Missing Value/s in Last Name at Row/s:";
		String pccValidation = "Missing Value/s in PCC at Row/s:";
		String csrLevelValidation = "Missing Value/s in CSR Level at Row/s:";
		String jurisdictionValidation = "Missing Value/s in Jurisdiction at Row/s:";
		String programValidation = "Missing Value/s in Program at Row/s:";		
		String statusValidation = "Missing Value/s in Status at Row/s:";
		
		String validationResult = "";
		int rowNum = 1;
		for (SystemIssueForm csrList: data) {
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

	private void processSystemIssueForm(Long userId, List<SystemIssueForm> data, Integer yearMonth,Long macId, String jurisdiction) {
		List<SystemIssueForm> existingRows1 = existingCsrListByMacJurisdictionMonthYear(macId, jurisdiction, yearMonth);
		if(existingRows1.size() > 0) {
			int count = csrListRepository.markStatusDeletedForAdmin(0l, macId, yearMonth, new Date(), jurisdiction);
			log.debug("updated count:"+count);
		}
		createCsrList(data);
	}

	private List<SystemIssueForm> copySystemIssueForm(List<SystemIssueForm> existingRows) {
		List<SystemIssueForm> data = new ArrayList<SystemIssueForm>();
		SystemIssueForm list = null;
		for(SystemIssueForm lists: existingRows) {
			list = new SystemIssueForm();
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
	
	public List<SystemIssueForm> existingCsrListByUserMonthYear(Long userId, Integer yearMonth){
		log.debug("existingCsrListByUserMonthYear::"+userId+","+yearMonth);
		return csrListRepository.existingCsrListByUserMonthYear(userId, yearMonth);
	}
	
	public List<SystemIssueForm> existingCsrListByMacMonthYear(Long macId,String jurisdiction,Integer yearMonth){
		log.debug("existingCsrListByMacMonthYear::"+macId+","+yearMonth);
		return csrListRepository.existingCsrListByMacJurisdictionMonthYear(macId, jurisdiction, yearMonth);
	}
	
	public List<SystemIssueForm> existingCsrListByMacJurisdictionMonthYear(Long macId, String jurisdiction,Integer yearMonth){
		log.debug("existingCsrListByMacMonthYear::"+macId+","+yearMonth);
		return csrListRepository.existingCsrListByMacJurisdictionMonthYear(macId, jurisdiction, yearMonth);
	}

	public List<SystemIssueForm> getCsrList(String from, String to,String macLookupIds,String jurisdictions) {
		
		List<SystemIssueForm> resultsList = null;
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
	
	
	public List<SystemIssueForm> getCsrNames(String csrLName,Long macLookupId,String jurisdiction, String program) {
		
		List<SystemIssueForm> tempSystemIssueForm = null;		
		
		try {
			boolean macAllFlag = false;
			
			tempSystemIssueForm = SystemIssueFormRepository.existingSystemIssueFormByMacIdJurisProgram(csrLName,macLookupId,jurisdiction,program);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return tempSystemIssueForm;
	}*/

	/*public List< SystemIssueForm > search( SystemIssueForm SystemIssueForm ){
	
	Specifications< SystemIssueForm > specifications = Specifications.where
				(SystemIssueFormSpecifications.searchByJurisdiction(csrLists.getJurisdiction()))
			.and(CsrListsSpecifications.searchByMacId(csrLists.getMacLookupId().intValue()))				
			.and(CsrListsSpecifications.searchByProgram(csrLists.getProgram()))
			.and(CsrListsSpecifications.findByCreatedDateBetween(csrLists.getCreatedDate(), null))
			.and(CsrListsSpecifications.searchByRecordStatus(csrLists.getRecordStatus()))			
			;
														
	return csrListRepository.findAll(specifications);
	}*/
}
