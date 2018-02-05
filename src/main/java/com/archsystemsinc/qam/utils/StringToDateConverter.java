package com.archsystemsinc.qam.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.AttributeConverter;

public class StringToDateConverter implements AttributeConverter<Date, String> {
	
	private static final SimpleDateFormat applicationDateFormatString = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
	
	private static final SimpleDateFormat mysqlDateFormatString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String convertToDatabaseColumn(Date dateValue) {
				
		TimeZone tzInAmerica = TimeZone.getTimeZone("America/New_York");
		mysqlDateFormatString.setTimeZone(tzInAmerica);
		return mysqlDateFormatString.format(dateValue);
	}

	@Override
	public Date convertToEntityAttribute(String dateString) {
		try {
			
			Date tempDate = mysqlDateFormatString.parse(dateString);
			String mySQLDateFormat = mysqlDateFormatString.format(tempDate);
			
			return applicationDateFormatString.parse(mySQLDateFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
