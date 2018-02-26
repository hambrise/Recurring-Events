package com.outlookrecurring;

import java.util.List;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.property.RRule;

public class O365RecurringRule 
{

	public static java.util.Date endOfDay(java.util.Date untilDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(untilDate);
		cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}
	
	public static void weekDayList(Recur recur, List<String> weekDays)
	{
		for(String day : weekDays)
		{
			if(day.equals("monday"))
			{
				recur.getDayList().add(WeekDay.MO);
			}
			if(day.equals("tuesday"))
			{
				recur.getDayList().add(WeekDay.TU);
			}
			if(day.equals("wednesday"))
			{
				recur.getDayList().add(WeekDay.WE);
			}
			if(day.equals("thursday"))
			{
				recur.getDayList().add(WeekDay.TH);
			}
			if(day.equals("friday"))
			{
				recur.getDayList().add(WeekDay.FR);
			}
			if(day.equals("saturday"))
			{
				recur.getDayList().add(WeekDay.SA);
			}
			if(day.equals("sunday"))
			{
				recur.getDayList().add(WeekDay.SU);
			}
		}
	}
	
	public static void getDay(Recur recur, String day)
	{
		if(day.equals("monday"))
		{
			recur.getDayList().add(WeekDay.MO);
		}
		if(day.equals("tuesday"))
		{
			recur.getDayList().add(WeekDay.TU);
		}
		if(day.equals("wednesday"))
		{
			recur.getDayList().add(WeekDay.WE);
		}
		if(day.equals("thursday"))
		{
			recur.getDayList().add(WeekDay.TH);
		}
		if(day.equals("friday"))
		{
			recur.getDayList().add(WeekDay.FR);
		}
		if(day.equals("saturday"))
		{
			recur.getDayList().add(WeekDay.SA);
		}
		if(day.equals("sunday"))
		{
			recur.getDayList().add(WeekDay.SU);
		}

	}
	
	public static Recurrence createRecurrenceObject()
	{
		Recurrence recurrence = new Recurrence();
		Pattern pattern = new Pattern();
		Range range = new Range();
		
		pattern.setInterval(1);
		pattern.setFirstDayOfWeek("sunday");
		
		range.setEndDate("2018-02-01");
		range.setStartDate("2017-11-09");
		range.setNumberOfOccurrences(0);
		range.setRecurrenceTimeZone("India Standard Time");
		range.setType("endDate");
		
//		daily(pattern, range);
//		weekly(pattern, range);
//		absoluteMonthly(pattern, range);
//		relativeMonthly(pattern, range);
//		absoluteYearly(pattern, range);
		relativeYearly(pattern, range);
		
		recurrence.setPattern(pattern);
		recurrence.setRange(range);
		
		return recurrence;
	}
	
	public static void daily(Pattern pattern, Range range )
	{
		pattern.setType(Constants.daily);
		pattern.setMonth(0);
		pattern.setDayOfMonth(0);
		pattern.setIndex("first"); 		
	}
	
	public static void weekly(Pattern pattern, Range range )
	{
		pattern.setType(Constants.weekly);
		pattern.setMonth(0);
		pattern.setIndex("first");
		pattern.setDayOfMonth(0);
		
		List<String> weekDays = new ArrayList<>();
		weekDays.add("monday");
		weekDays.add("saturday");	
		
		pattern.setDaysOfWeek(weekDays);
		 		
	}
	
	public static void absoluteMonthly(Pattern pattern, Range range )
	{
		pattern.setType(Constants.absoluteMonthly);
		pattern.setMonth(0);
		pattern.setIndex("first");
		
		pattern.setDayOfMonth(9);
	}

	public static void relativeMonthly(Pattern pattern, Range range )
	{
		pattern.setType(Constants.relativeMonthly);
		pattern.setMonth(0);
		pattern.setIndex("second");
		pattern.setDayOfMonth(0);
		
		List<String> weekDays = new ArrayList<>();
		weekDays.add("thursday");
		pattern.setDaysOfWeek(weekDays);
	}
	
	public static void absoluteYearly(Pattern pattern, Range range )
	{
		pattern.setType(Constants.absoluteYearly);
		pattern.setMonth(11);
		pattern.setIndex("first");
		
		pattern.setDayOfMonth(9);
	}

	public static void relativeYearly(Pattern pattern, Range range )
	{
		pattern.setType(Constants.relativeYearly);
		pattern.setMonth(11);
		pattern.setIndex("second");
		
		pattern.setDayOfMonth(0);
		
		List<String> weekDays = new ArrayList<>();
		weekDays.add("thursday");
		pattern.setDaysOfWeek(weekDays);
	}
	
	public static void generateRrule(Recurrence recurrence)
	{
		Recur recur = new Recur();
		
		Pattern pattern = recurrence.getPattern();
		Range range = recurrence.getRange();
		
		recur.setFrequency(Constants.freq.get(pattern.getType()));
		
		
		if(Constants.weekly.equals(pattern.getType()))
		{
			weekDayList(recur, pattern.getDaysOfWeek());
		}
		
		if(Constants.absoluteMonthly.equals(pattern.getType()))
		{
			recur.getMonthDayList().add(pattern.getDayOfMonth());
		}
		
		if(Constants.relativeMonthly.equals(pattern.getType()))
		{
			recur.getSetPosList().add(Constants.indexMap.get(pattern.getIndex()));
			weekDayList(recur, pattern.getDaysOfWeek());
		}
		
		if(Constants.absoluteYearly.equals(pattern.getType()))
		{
			recur.getMonthList().add(pattern.getMonth());
			recur.getMonthDayList().add(pattern.getDayOfMonth());
		}
		
		if(Constants.relativeYearly.equals(pattern.getType()))
		{
			recur.getSetPosList().add(Constants.indexMap.get(pattern.getIndex()));
			recur.getMonthList().add(pattern.getMonth());
			weekDayList(recur, pattern.getDaysOfWeek());
		}
		
		recur.setInterval(pattern.getInterval());
		
		java.util.Date date = new Date();
		try 
		{
			date = Constants.sdf.parse(range.getEndDate());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		recur.setUntil(new net.fortuna.ical4j.model.Date(endOfDay(date)));
		
		RRule rrule = new RRule(recur);
		System.out.println("O365RecurringRule:generateRrule:rrule:" + rrule);
		
	}

	public static void main(String[] args) 
	{
		generateRrule(createRecurrenceObject());
	}

}
