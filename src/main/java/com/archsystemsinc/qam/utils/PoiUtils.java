/**
 * 
 */
package com.archsystemsinc.qam.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.repository.RadUserRepository;

/**
 * @author Prakash T
 *
 */
@Service
public class PoiUtils {
	
	
	@Autowired
	private RadUserRepository radUserRepository;
	
	private static final Logger log = Logger.getLogger(PoiUtils.class);


	public List<CsrLists> parseCsrListFile(MultipartFile uploadedFile, Long userId, Long macId) throws Exception {
		log.debug("--> parseCsrListFile");	
		List<CsrLists> dataList = null;
		try {
			RadUser radUser = radUserRepository.findOne(userId);
			if(radUser == null) throw new Exception("No User Found for the Id:"+userId);
			//Long macId = radUser.getMacId();
			String userName = radUser.getUserName();
			dataList = new ArrayList<CsrLists>();
			Workbook providersFileWorkbook = null;
			String resultString ="";
			try {
				providersFileWorkbook = WorkbookFactory.create(uploadedFile.getInputStream());
				Sheet providersFileSheet = providersFileWorkbook.getSheetAt(0);
				Iterator<Row> providersFileRowIterator = providersFileSheet.rowIterator();
				int providersFileRowCount = providersFileSheet.getPhysicalNumberOfRows();
				int totalNumberOfRows = providersFileRowCount - 1;
				log.debug("totalNumberOfRows::" + totalNumberOfRows);
				CsrLists data = null;
				while (providersFileRowIterator.hasNext()) {
					Row providersFileRow = (Row) providersFileRowIterator.next();

					if (providersFileRow.getRowNum() > 0 && providersFileRow.getRowNum() <= providersFileRowCount) {
						data = new CsrLists();
						log.debug("ROW - " + providersFileRow.getRowNum());
						Iterator<Cell> iterator = providersFileRow.cellIterator();
						while (iterator.hasNext()) {
							Cell hssfCell = (Cell) iterator.next();
							int cellIndex = hssfCell.getColumnIndex();
							switch (cellIndex) {
							case 0:
								data.setFirstName(hssfCell.getStringCellValue());
								break;
							case 1:
								
								data.setMiddleName(hssfCell.getStringCellValue());
								break;
								
							case 2:
								data.setLastName(hssfCell.getStringCellValue());
								break;
							case 3:
								data.setLocation(hssfCell.getStringCellValue());
								break;
							case 4:
								data.setLevel(hssfCell.getStringCellValue());
								break;
							case 5:
								data.setJurisdiction(hssfCell.getStringCellValue());
								
								break;
							case 6:
								data.setProgram(hssfCell.getStringCellValue());
								break;
							case 7:
								data.setStatus(hssfCell.getStringCellValue());							
								break;
							default:
								log.debug("No Matching column");
								break;
							}

						}
						data.setCreatedDate(new Date());
						data.setUpdateddDate(new Date());
						data.setUserId(userId);
						data.setCreatedBy(userName);
						data.setUpdatedBy(userName);
						data.setRecordStatus(1l);
						data.setMacLookupId(macId);
						dataList.add(data);
					}
				}
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				e.printStackTrace();
				throw new Exception("Failed to Process the Excelsheet, Invalid Data",e);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception("No User Found for the Id:"+userId);
		}
		log.debug("<-- parseCsrListFile");
		return dataList;
	}

}
