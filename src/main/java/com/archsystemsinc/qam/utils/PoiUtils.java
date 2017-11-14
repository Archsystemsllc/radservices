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
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.exception.FileUploadException;
import com.archsystemsinc.logging.monitor.StageMonitor;
import com.archsystemsinc.qam.model.Address;
import com.archsystemsinc.qam.model.Category;
import com.archsystemsinc.qam.model.CsrList;
import com.archsystemsinc.qam.model.FileData;
import com.archsystemsinc.qam.model.HealthCommunity;
import com.archsystemsinc.qam.model.HealthDataTemplateConfig;
import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.model.SocialMedia;
import com.archsystemsinc.qam.model.TemplateConfigData;

/**
 * @author Prakash T
 *
 */
public class PoiUtils {

	private static final Logger log = Logger.getLogger(PoiUtils.class);

	public static List<FileData> parseFile(MultipartFile uploadedFile, TemplateConfigData configData) {
		log.debug("--> parseFile");
		List<FileData> dataList = new ArrayList<FileData>();
		Workbook providersFileWorkbook = null;
		try {
			providersFileWorkbook = WorkbookFactory.create(uploadedFile.getInputStream());
			Sheet providersFileSheet = providersFileWorkbook.getSheetAt(0);
			Iterator<Row> providersFileRowIterator = providersFileSheet.rowIterator();
			int providersFileRowCount = providersFileSheet.getPhysicalNumberOfRows();
			int totalNumberOfRows = providersFileRowCount - 1;
			log.debug("totalNumberOfRows::" + totalNumberOfRows);
			FileData data = null;
			while (providersFileRowIterator.hasNext()) {
				Row providersFileRow = (Row) providersFileRowIterator.next();

				if (providersFileRow.getRowNum() > 0 && providersFileRow.getRowNum() <= providersFileRowCount) {
					data = new FileData();
					data.setTemplateId(configData.getTemplateId());
					log.debug("ROW - " + providersFileRow.getRowNum());
					Iterator<Cell> iterator = providersFileRow.cellIterator();
					while (iterator.hasNext()) {
						Cell hssfCell = (Cell) iterator.next();
						int cellIndex = hssfCell.getColumnIndex();

						updateFileData(cellIndex, hssfCell, data, configData);
					}
					dataList.add(data);
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {

			e.printStackTrace();
		}

		log.debug("<-- parseFile");
		return dataList;
	}

	private static void updateFileData(int cellIndex, Cell cellData, FileData data, TemplateConfigData configData) {
		// log.debug("cellIndex::"+cellIndex);
		// log.debug("configData::"+configData);
		if (configData.getColumnA() != null && configData.getColumnA() == cellIndex) {
			data.setColumnA(cellData.getStringCellValue());
		} else if (configData.getColumnB() != null && configData.getColumnB() == cellIndex) {
			data.setColumnB(cellData.getStringCellValue());
		} else if (configData.getColumnC() != null && configData.getColumnC() == cellIndex) {
			data.setColumnC(cellData.getStringCellValue());
		} else if (configData.getColumnD() != null && configData.getColumnD() == cellIndex) {
			data.setColumnD(new Double(cellData.getNumericCellValue()).longValue());
		} else if (configData.getColumnE() != null && configData.getColumnE() == cellIndex) {
			data.setColumnE(cellData.getStringCellValue());
		} else if (configData.getColumnF() != null && configData.getColumnF() == cellIndex) {
			data.setColumnF(cellData.getStringCellValue());
		} else if (configData.getColumnG() != null && configData.getColumnG() == cellIndex) {
			data.setColumnG(new Double(cellData.getNumericCellValue()).longValue());
		}
	}

	public static List<HealthCommunity> parseHealthDataFile(MultipartFile uploadedFile,
			HealthDataTemplateConfig configData, StageMonitor monitor) throws FileUploadException {
		log.debug("--> parseHealthFile");
		monitor.appendMessage(monitor.getCurrentStage(), "Parse file name: " + uploadedFile.getName());
		List<HealthCommunity> dataList = new ArrayList<HealthCommunity>();
		Workbook providersFileWorkbook = null;

		try {
			providersFileWorkbook = WorkbookFactory.create(uploadedFile.getInputStream());
			Sheet providersFileSheet = providersFileWorkbook.getSheetAt(0);
			Iterator<Row> providersFileRowIterator = providersFileSheet.rowIterator();
			int providersFileRowCount = providersFileSheet.getPhysicalNumberOfRows();
			int totalNumberOfRows = providersFileRowCount - 1;
			log.debug("totalNumberOfRows::" + totalNumberOfRows);
			monitor.appendMessage(monitor.getCurrentStage(),
					"Parse file name,number of rows: " + uploadedFile.getName() + "' " + totalNumberOfRows);
			HealthCommunity data = null;
			long rowNum = 0;
			while (providersFileRowIterator.hasNext()) {
				try {
					Row providersFileRow = (Row) providersFileRowIterator.next();

					if (providersFileRow.getRowNum() > 0 && providersFileRow.getRowNum() <= providersFileRowCount) {
						data = new HealthCommunity();
						data.setTemplateId(configData.getTemplateId());
						log.debug("ROW - " + providersFileRow.getRowNum());
						rowNum = providersFileRow.getRowNum();
						Iterator<Cell> iterator = providersFileRow.cellIterator();
						while (iterator.hasNext()) {
							Cell hssfCell = (Cell) iterator.next();
							int cellIndex = hssfCell.getColumnIndex();
							updateHealthFileData(cellIndex, hssfCell, data, configData);
							// log.debug(data);
						}
						dataList.add(data);
					}
				} catch (Exception e) {
					// So this will be reported with the file name, not randomly threaded through
					// the console output.
					// this is a key area for error reporting in an enterprise context. The file
					// parsing
					// is a common area where a difficult to find issue will occur.
					monitor.appendMessage(monitor.getCurrentStage(), "Failed for row: " + rowNum);
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			monitor.appendMessage(monitor.getCurrentStage(), "Failed parsing file: " + uploadedFile.getName());

			log.error(e);
			throw new FileUploadException(e.getMessage());

		}

		log.debug("<-- parseHealthFile");
		return dataList;
	}

	private static SocialMedia getSocialMedia(HealthCommunity data) {
		if (data.getSocialMedia() == null)
			return new SocialMedia(data);
		else
			return data.getSocialMedia();
	}

	private static Address getAddress(HealthCommunity data) {
		if (data.getAddress() == null)
			return new Address(data);
		else
			return data.getAddress();
	}

	private static void updateHealthFileData(int cellIndex, Cell cellData, HealthCommunity data,
			HealthDataTemplateConfig configData) {
		if (configData.getCategoryName() != null && configData.getCategoryName() == cellIndex) {
			Category category = new Category(data);
			category.setCategoryName(cellData.getStringCellValue());
			data.setCategory(category);
		} else if (configData.getLocation() != null && configData.getLocation() == cellIndex) {
			Address address = getAddress(data);
			address.setLocation(cellData.getStringCellValue());
			data.setAddress(address);
		} else if (configData.getCity() != null && configData.getCity() == cellIndex) {
			Address address = getAddress(data);
			address.setCity(cellData.getStringCellValue());
			data.setAddress(address);
		} else if (configData.getState() != null && configData.getState() == cellIndex) {
			Address address = getAddress(data);
			address.setState(cellData.getStringCellValue());
			data.setAddress(address);
		} else if (configData.getStateBase() != null && configData.getStateBase() == cellIndex) {
			Address address = getAddress(data);
			address.setStateBase(cellData.getStringCellValue());
			data.setAddress(address);
		} else if (configData.getPhase1() != null && configData.getPhase1() == cellIndex) {
			Address address = getAddress(data);
			address.setPhase1(cellData.getStringCellValue());
			data.setAddress(address);
		} else if (configData.getPhase2() != null && configData.getPhase2() == cellIndex) {
			Address address = getAddress(data);
			address.setPhase2(cellData.getStringCellValue());
			data.setAddress(address);
		} else if (configData.getFacebook() != null && configData.getFacebook() == cellIndex) {
			SocialMedia socialMedia = getSocialMedia(data);
			socialMedia.setFacebook(cellData.getStringCellValue());
			data.setSocialMedia(socialMedia);
		} else if (configData.getTwitter() != null && configData.getTwitter() == cellIndex) {
			SocialMedia socialMedia = getSocialMedia(data);
			socialMedia.setTwitter(cellData.getStringCellValue());
			data.setSocialMedia(socialMedia);
		} else if (configData.getYoutube() != null && configData.getYoutube() == cellIndex) {
			SocialMedia socialMedia = getSocialMedia(data);
			socialMedia.setYoutube(cellData.getStringCellValue());
			data.setSocialMedia(socialMedia);
		} else if (configData.getWebsite() != null && configData.getWebsite() == cellIndex) {
			SocialMedia socialMedia = getSocialMedia(data);
			socialMedia.setWebsite(cellData.getStringCellValue());
			data.setSocialMedia(socialMedia);
		} else if (configData.getMapDisplay() != null && configData.getMapDisplay() == cellIndex) {
			data.setMapDisplay(cellData.getStringCellValue());
		} else if (configData.getMsaName() != null && configData.getMsaName() == cellIndex) {
			data.setMsaName(cellData.getNumericCellValue() + "");
		} else if (configData.getNameOfInitiative() != null && configData.getNameOfInitiative() == cellIndex) {
			data.setNameOfInitiative(cellData.getStringCellValue());
		} else if (configData.getNotes() != null && configData.getNotes() == cellIndex) {
			data.setNotes(cellData.getStringCellValue());
		} else if (configData.getOrgName() != null && configData.getOrgName() == cellIndex) {
			data.setOrgName(cellData.getStringCellValue());
		}
	}

	public static List<RadUser> parseUserFile(MultipartFile uploadedFile) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<CsrList> parseCsrListFile(MultipartFile uploadedFile) {
		log.debug("--> parseFile");
		List<CsrList> dataList = new ArrayList<CsrList>();
		Workbook providersFileWorkbook = null;
		try {
			providersFileWorkbook = WorkbookFactory.create(uploadedFile.getInputStream());
			Sheet providersFileSheet = providersFileWorkbook.getSheetAt(0);
			Iterator<Row> providersFileRowIterator = providersFileSheet.rowIterator();
			int providersFileRowCount = providersFileSheet.getPhysicalNumberOfRows();
			int totalNumberOfRows = providersFileRowCount - 1;
			log.debug("totalNumberOfRows::" + totalNumberOfRows);
			CsrList data = null;
			while (providersFileRowIterator.hasNext()) {
				Row providersFileRow = (Row) providersFileRowIterator.next();

				if (providersFileRow.getRowNum() > 0 && providersFileRow.getRowNum() <= providersFileRowCount) {
					data = new CsrList();
					log.debug("ROW - " + providersFileRow.getRowNum());
					Iterator<Cell> iterator = providersFileRow.cellIterator();
					while (iterator.hasNext()) {
						Cell hssfCell = (Cell) iterator.next();
						int cellIndex = hssfCell.getColumnIndex();
						switch (cellIndex) {
						case 0:
							data.setFisrtName(hssfCell.getStringCellValue());
							break;
						case 1:
							data.setLastName(hssfCell.getStringCellValue());
							break;
						case 2:
							data.setLocation(hssfCell.getStringCellValue());
							break;
						case 3:
							data.setLevel(hssfCell.getStringCellValue());
							break;
						case 4:
							data.setJurisdiction(hssfCell.getStringCellValue());
							break;
						case 5:
							data.setProgram(hssfCell.getStringCellValue());
							break;
						case 6:
							data.setStatus(hssfCell.getStringCellValue());
							break;
						default:
							log.debug("No Matching column");
							break;
						}

					}
					data.setCreatedDate(new Date());
					data.setUserId(100l);
					data.setMacLookupId(453l);
					dataList.add(data);
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {

			e.printStackTrace();
		}

		log.debug("<-- parseFile");
		return dataList;

	}

}
