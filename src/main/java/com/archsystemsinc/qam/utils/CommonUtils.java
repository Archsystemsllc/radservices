/**
 * 
 */
package com.archsystemsinc.qam.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author PrakashTotta
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
		int year = cal.get(Calendar.YEAR);
		log.debug("isUploadCompliance-year: "+year);
		String temp = year+""+month;
		log.debug("isUploadCompliance-year: "+temp);
		return new Integer(temp);
		
	}

}
