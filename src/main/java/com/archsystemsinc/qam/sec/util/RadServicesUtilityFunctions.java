package com.archsystemsinc.qam.sec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class RadServicesUtilityFunctions {
	
	public HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	
	private static final SimpleDateFormat usEstDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

	
	public String convertToStringFromDate(Date dateValue) {
				
		TimeZone tzInAmerica = TimeZone.getTimeZone("America/New_York");
		usEstDateFormat.setTimeZone(tzInAmerica);
		if(dateValue!=null) {
			String dateValueString = usEstDateFormat.format(dateValue);
			return dateValueString;
		} else return null;
		
	}

	
	public Date convertToDateFromString(String dataString) {
		try {
			TimeZone tzInAmerica = TimeZone.getTimeZone("America/New_York");
			usEstDateFormat.setTimeZone(tzInAmerica);
			return usEstDateFormat.parse(dataString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
