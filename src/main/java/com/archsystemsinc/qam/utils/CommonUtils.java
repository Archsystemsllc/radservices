/**
 * 
 */
package com.archsystemsinc.qam.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.archsystemsinc.qam.sec.util.GenericConstants;


/**
 * @author AbdulNissar
 *
 */
public class CommonUtils {

	private static final Logger log = Logger.getLogger(CommonUtils.class);
	public static Map<String, String> monthMap = new HashMap<String, String>();

	static {
		monthMap.put("January", "1");
		monthMap.put("February", "2");
		monthMap.put("March", "3");
		monthMap.put("April", "4");
		monthMap.put("May", "5");
		monthMap.put("June", "6");
		monthMap.put("July", "7");
		monthMap.put("August", "8");
		monthMap.put("September", "9");
		monthMap.put("October", "10");
		monthMap.put("November", "11");
		monthMap.put("December", "12");
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isUploadCompliance() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_MONTH);
		log.debug("isUploadCompliance-Day: "+day);
		if( day > 0 &&  day <6) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Integer getCurrentYearMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH);
		month = month+1;
		String monthString = "";
		if(month<10) {
			monthString ="0"+month;
		} else {
			monthString = String.valueOf(month);
		}
		
		int year = cal.get(Calendar.YEAR);
		log.debug("isUploadCompliance-year: "+year);
		String temp = year+""+monthString;
		log.debug("isUploadCompliance-year: "+temp);
		return new Integer(temp);
		
	}
	
	public static String convertToStringFromLocalDate(LocalDate dateValue) {				
		
		String dateValueString = "";
		if(dateValue!=null) {
			
				 DateTimeFormatter dateFormatter1 = DateTimeFormatter
				            .ofPattern("MM/dd/yyyy");
				dateValueString = dateValue.format(dateFormatter1);
		
			return dateValueString;
		} else return null;
		
	}	
	
	public static LocalDate convertToLocalDateFromString(String dataString) {
		try {
			LocalDate  returnDateValue = null;
					
			returnDateValue = LocalDate.parse(dataString,DateTimeFormatter.ofPattern("MM/dd/yyyy"));			
			
			return returnDateValue;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	private static final SimpleDateFormat usEstDateFormatFullDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
	
	private static final SimpleDateFormat usEstDateFormatOnlyDate = new SimpleDateFormat("MM/dd/yyyy");
	
	private static final SimpleDateFormat usEstDateFormatMonthYearReportDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
	
	public static LocalDate convertToLocalDateFromDateObject(Date dateObject) {
		try {
			
			ZoneId defaultZoneId = ZoneId.systemDefault();
	       
	        //1. Convert Date -> Instant
	        Instant instant = dateObject.toInstant();	       

	        //2. Instant + system default time zone + toLocalDate() = LocalDate
	        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
			
			return localDate;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	public static String convertToStringFromDate(Date dateValue, String dateType) {				
		
		String dateValueString = "";
		if(dateValue!=null) {
			if(dateType.equalsIgnoreCase(GenericConstants.DATE_TYPE_FULL)) {
				//usEstDateFormatFullDate.setTimeZone(tzInAmerica);
				dateValueString = usEstDateFormatFullDate.format(dateValue);
			} else if (dateType.equalsIgnoreCase(GenericConstants.DATE_TYPE_ONLY_DATE)) {
				//usEstDateFormatOnlyDate.setTimeZone(tzInAmerica);
				dateValueString = usEstDateFormatOnlyDate.format(dateValue);
			}
			
			return dateValueString;
		} else return null;
		
	}

	
	public static Date convertToDateFromString(String dataString, String dateType) {
		try {
			Date returnDateValue = null;
			
			if(dateType.equalsIgnoreCase(GenericConstants.DATE_TYPE_FULL)) {
				//usEstDateFormatFullDate.setTimeZone(tzInAmerica);
				returnDateValue = usEstDateFormatFullDate.parse(dataString);				
			} else if (dateType.equalsIgnoreCase(GenericConstants.DATE_TYPE_ONLY_DATE)) {				
				//usEstDateFormatOnlyDate.setTimeZone(tzInAmerica);
				returnDateValue = usEstDateFormatOnlyDate.parse(dataString);			
			}
			return returnDateValue;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
