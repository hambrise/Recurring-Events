package com.googlerecurring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.property.RRule;

public class GoogleReccuringRule 
{
	//Generate Recurring Rule
	public static void generateRrule(String frequency, Integer count, Date until, Boolean isdayOfWeekinMonth) throws ParseException
	{
		Recur recur = new Recur();
		recur.setFrequency(frequency);
		recur.setInterval(2);

		if(count > 0 && until == null)
		{
			recur.setCount(5);
		}
		else if(count == 0 && until != null)
		{
			if(Recur.DAILY.equals(frequency))
			{
				recur.setUntil(new net.fortuna.ical4j.model.Date(until));
			}
		}
		
		if(Recur.WEEKLY.equals(frequency))
		{
			recur.getDayList().add(WeekDay.SA);
			recur.getDayList().add(WeekDay.SU);
		}
		
		if(Recur.MONTHLY.equals(frequency))
		{
			if(!isdayOfWeekinMonth)
			{
				recur.getMonthDayList().add(31);
			}
			else
			{
				recur.getSetPosList().add(3);
				recur.getDayList().add(WeekDay.MO);
			}
		}
		
		if(Recur.YEARLY.equals(frequency))
		{
			recur.getMonthList().add(2);
			recur.getMonthDayList().add(15);
		}
		
		RRule rrule = new RRule(recur);
		System.out.println("**rrule:" + rrule);
	}
	
	public static void generateGoogleRrule(String frequency, Integer count, Integer interval, String until, List<String> weekDays, String type) throws ParseException
	{
		String rrule = "RRULE:FREQ="+frequency;
		if(interval > 0)
		{
			rrule = rrule+";INTERVAL="+interval;
		}
		if(count > 0)
		{
			rrule = rrule+";COUNT="+count;
		}
		if(until != null)
		{
			if("WEEKLY".equals(frequency) || ("MONTHLY".equals(frequency) && "dayOfMonth".equals(type)))
			{
				//String to calendar  -- until to untilCal
				//change to endOf the Day -- untilCal to endOftheDay
				//change to GMT 
				//change to GMT - userTimeZone offset
			}
			else
			{
				rrule = rrule+";UNTIL="+until;
			}
			
		}
		if(weekDays != null && !weekDays.isEmpty())
		{
			String weekList = Arrays.toString(weekDays.toArray()).replace("[", "").replace("]", "");
			rrule = rrule+";BYDAY="+weekList;
		}
		
		
		
		System.out.println("GoogleRRULE:" +rrule );
		
	}
	
	public static void main(String[] args) throws ParseException 
	{
		Date until = new SimpleDateFormat("dd-MM-yy").parse("30-09-17");
		Calendar cal = Calendar.getInstance();
		cal.setTime(until);
		cal.add(Calendar.DATE, 1);
		until = cal.getTime();
		String untilStr = new SimpleDateFormat("yyyyMMdd").format(until);
//		generateRrule(Recur.DAILY, 0, until, true);
		generateGoogleRrule(Recur.DAILY, 0, 0, untilStr, null, untilStr);
		
		
//		generateRrule(Recur.WEEKLY, 5, null, true);
//		generateRrule(Recur.MONTHLY, 5, null, true);
//		generateRrule(Recur.YEARLY, 5, null, true);
	}

}

