/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.CsrList;
import com.archsystemsinc.qam.repository.CsrListRepository;
import com.archsystemsinc.qam.utils.PoiUtils;

/**
 * @author Prakash T
 *
 */
@Service
@Transactional
public class CsrListService {

	@Autowired
	private CsrListRepository csrListRepository;
	
	
	public void createCsrList(List<CsrList> data){
		csrListRepository.save(data);
	}
	
	public void uploadFileData(MultipartFile uploadedFile){
		List<CsrList> data = PoiUtils.parseCsrListFile(uploadedFile);		
		createCsrList(data);
	}
	
	public List<CsrList> getCsrList(Date from, Date to){
		return csrListRepository.findByCreatedDateBetween(from, to);
	}
}
