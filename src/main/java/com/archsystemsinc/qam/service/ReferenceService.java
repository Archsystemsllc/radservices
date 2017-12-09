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
public class ReferenceService {
	private static final Logger log = Logger.getLogger(ReferenceService.class);
	@Autowired
	private CsrListRepository csrListRepository;
	
	@Autowired
	private CsrLogRepository csrLogRepository;
	
	@Autowired
	private PoiUtils poiUtils;	
	
}
