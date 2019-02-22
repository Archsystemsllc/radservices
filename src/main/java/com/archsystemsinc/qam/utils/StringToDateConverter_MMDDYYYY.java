package com.archsystemsinc.qam.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.AttributeConverter;

public class StringToDateConverter_MMDDYYYY implements AttributeConverter<Date, String> {
	
	private static final SimpleDateFormat mysqlDateFormatString = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public String convertToDatabaseColumn(Date dateValue) {
				
		//TimeZone tzInAmerica = TimeZone.getTimeZone("America/New_York");
		//mysqlDateFormatString.setTimeZone(tzInAmerica);
		return mysqlDateFormatString.format(dateValue);
	}

	@Override
	public Date convertToEntityAttribute(String dateString) {
		try {			
			Date tempDate = mysqlDateFormatString.parse(dateString);			
			return tempDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
