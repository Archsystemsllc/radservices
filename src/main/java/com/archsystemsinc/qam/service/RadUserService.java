/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.repository.RadUserRepository;
import com.archsystemsinc.qam.utils.PoiUtils;

/**
 * @author Prakash T
 *
 */
@Service
@Transactional
public class RadUserService {

	@Autowired
	private RadUserRepository radUserRepository;
	
	
	public void createUser(List<RadUser> data){
		radUserRepository.save(data);
	}
	
	public void uploadFileData(MultipartFile uploadedFile){
		List<RadUser> data = PoiUtils.parseUserFile(uploadedFile);		
		createUser(data);
	}
}
