package com.outlookrecurring;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.WeekDayList;
import net.fortuna.ical4j.model.property.RRule;

public class O365Recurring {

	public static List<String> getWeekDaysList(WeekDayList weekDayList)
	{
		List<String> weekdays = new ArrayList<>();
		
		for(WeekDay day : weekDayList)
		{
			if(WeekDay.MO.equals(day))
			{
				weekdays.add(Constants.MONDAY);
			}
			if(WeekDay.TU.equals(day))
			{
				weekdays.add(Constants.TUESDAY);
			}
			if(WeekDay.WE.equals(day))
			{
				weekdays.add(Constants.WEDNESDAY);
			}
			if(WeekDay.TH.equals(day))
			{
				weekdays.add(Constants.THURSDAY);
			}
			if(WeekDay.FR.equals(day))
			{
				weekdays.add(Constants.FRIDAY);
			}
			if(WeekDay.SA.equals(day))
			{
				weekdays.add(Constants.SATURDAY);
			}
			if(WeekDay.SU.equals(day))
			{
				weekdays.add(Constants.SUNDAY);
			}
		}
		return weekdays; 
	}
	
	public static RRule createRrule()
	{
		Recur recur = new Recur();
		recur.setInterval(1);
		java.util.Date untilStr = new java.util.Date();
		try 
		{
			untilStr = Constants.sdf.parse("2018-02-01");
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		recur.setUntil(new Date(untilStr));
		
//		daily(recur);
//		weekly(recur);
//		absoluteMonthly(recur);
//		relativeMonthly(recur);
//		absoluteYearly(recur);
		relativeYearly(recur);
		
		RRule rrule = new RRule(recur);
		System.out.println("O365Recurring:createRrule:rrule:::" +rrule );
		
		return rrule;
	}
	
	public static void daily(Recur recur)
	{
		recur.setFrequency(Recur.DAILY);	
	}
	
	public static void weekly(Recur recur)
	{
		recur.setFrequency(Recur.WEEKLY);	
		recur.getDayList().add(WeekDay.MO);
		recur.getDayList().add(WeekDay.TU);
		recur.getDayList().add(WeekDay.FR);
	}
	
	public static void absoluteMonthly(Recur recur)
	{
		recur.setFrequency(Recur.MONTHLY);	
		recur.getMonthDayList().add(9);
	}
	
	public static void relativeMonthly(Recur recur)
	{
		recur.setFrequency(Recur.MONTHLY);
		recur.getSetPosList().add(2);
		recur.getDayList().add(WeekDay.TH);
	}
	
	public static void absoluteYearly(Recur recur)
	{
		recur.setFrequency(Recur.YEARLY);	
		recur.getMonthList().add(11);
		recur.getMonthDayList().add(9);
	}
	
	public static void relativeYearly(Recur recur)
	{
		recur.setFrequency(Recur.YEARLY);	
		recur.getMonthList().add(11);
		recur.getDayList().add(WeekDay.TH);
		recur.getSetPosList().add(2);
	}
	
	public static void generateO365RecurrenceObjects(RRule rrule)
	{
		Recur recur = rrule.getRecur();
		
		Recurrence recurrence = new Recurrence();
		Pattern pattern = new Pattern();
		Range range = new Range();
		
		pattern.setType(Constants.patternType.get(recur.getFrequency()));
		
		pattern.setInterval(recur.getInterval());
		pattern.setFirstDayOfWeek("sunday");
		pattern.setMonth(0);
		pattern.setDayOfMonth(0);
		pattern.setIndex(Constants.indexStr.get(1));
		
		if(recur.getMonthDayList().size() > 0)
		{
			pattern.setType(Constants.patternAbsoluteType.get(recur.getFrequency()));
			pattern.setDayOfMonth(recur.getMonthDayList().get(0));
		}
		
		if(recur.getDayList() != null && recur.getDayList().size() > 0) 
		{
			List<String> weekDays = getWeekDaysList(recur.getDayList());
			pattern.setDaysOfWeek(weekDays);
		}
		
		if(Recur.YEARLY.equals(recur.getFrequency()) && recur.getMonthList().size() > 0)
		{
			pattern.setMonth(recur.getMonthList().get(0));
		}
		
		if(recur.getSetPosList().size() > 0)
		{
			pattern.setIndex(Constants.indexStr.get(recur.getSetPosList().get(0)));
		}
		
		Date untilDate = recur.getUntil();
		range.setEndDate(Constants.sdf.format(untilDate));
		range.setStartDate("2017-11-09");
		range.setNumberOfOccurrences(0);
		range.setRecurrenceTimeZone("India Standard Time");
		range.setType("endDate");
		
		recurrence.setPattern(pattern);
		recurrence.setRange(range);
		
		 Gson gson = new Gson();
		 System.out.println("O365Recurring:generateO365RecurrenceObjects:" + gson.toJson(recurrence));
	}
	
	public static void main(String[] args) 
	{
		generateO365RecurrenceObjects(createRrule());
	}
}
