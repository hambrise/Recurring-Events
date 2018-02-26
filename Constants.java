package com.outlookrecurring;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.fortuna.ical4j.model.Recur;

public class Constants {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static String daily ="daily";
	public static String weekly ="weekly";
	public static String absoluteMonthly ="absoluteMonthly";
	public static String relativeMonthly ="relativeMonthly";
	public static String absoluteYearly ="absoluteYearly";
	public static String relativeYearly ="relativeYearly";

	public static final Map<String, String> freq = new HashMap<>();
	static {
		freq.put("daily", Recur.DAILY);
		freq.put("weekly", Recur.WEEKLY);
		freq.put("absoluteMonthly", Recur.MONTHLY);
		freq.put("relativeMonthly", Recur.MONTHLY);
		freq.put("absoluteYearly", Recur.YEARLY);
		freq.put("relativeYearly", Recur.YEARLY);
	}

	public static final Map<String,Integer> indexMap = new HashMap<>();
	static {
		indexMap.put("first",1);
		indexMap.put("second",2);
		indexMap.put("third",3);
		indexMap.put("fourth",4);
		indexMap.put("last",-1);
	}
	
	public static final Map<Integer,String> indexStr = new HashMap<>();
	static {
		indexStr.put(1,"first");
		indexStr.put(2,"second");
		indexStr.put(3,"third");
		indexStr.put(4,"fourth");
		indexStr.put(-1,"last");
	}
	
	public static final Map<String, String> patternType = new HashMap<>();
	static {
		patternType.put(Recur.DAILY, "daily");
		patternType.put(Recur.WEEKLY, "weekly");
		patternType.put(Recur.MONTHLY, "relativeMonthly");
		patternType.put(Recur.YEARLY, "relativeYearly");		
	}
	
	public static final Map<String, String> patternAbsoluteType = new HashMap<>();
	static {
		patternAbsoluteType.put(Recur.MONTHLY, "absoluteMonthly");
		patternAbsoluteType.put(Recur.YEARLY, "absoluteYearly");
	}
	
	public static String SUNDAY = "sunday";
	public static String MONDAY = "monday";
	public static String TUESDAY = "tuesday";
	public static String WEDNESDAY = "wednesday";
	public static String THURSDAY = "thursday";
	public static String FRIDAY = "friday";
	public static String SATURDAY = "saturday";
}
