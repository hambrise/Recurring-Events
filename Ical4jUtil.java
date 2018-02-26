package com.recurring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.PeriodList;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.RRule;

public class Ical4jUtil {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	static SimpleDateFormat tformat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
	public static void icalMethodForDay(String subject, java.util.Date startDate, java.util.Date endDate, Integer interval, Integer count, java.util.Date untilDate)
	{
		Recur recur = null;
		if(untilDate == null && count > 0)
		{
			recur = new Recur(Recur.DAILY, count);
		}
		else
		{
			recur = new Recur(Recur.DAILY, new DateTime(untilDate));
		}
		
		recur.setInterval(interval);
		RRule rrule = new RRule(recur);
		System.out.println("**event..rrule:" + rrule);
		
		System.out.println("Ical4Test:icalMethodForDay:" + recur.getDates(new Date(startDate),new DateTime(untilDate),Value.DATE));

		VEvent vEvent = new VEvent(new DateTime(startDate), new DateTime(endDate), subject);
		System.out.println("**event..:" + vEvent);
		
		vEvent.getProperties().add(rrule);

		printAllPeriodList(startDate, endDate, vEvent,untilDate);

	}
	
	public static void icalMethodForWeek(String subject, java.util.Date startDate, java.util.Date endDate, Integer interval, Integer count, java.util.Date untilDate)
	{
		Recur recur = null;
		if(untilDate == null && count > 0)
		{
			recur = new Recur(Recur.WEEKLY, count);
		}
		else
		{
			recur = new Recur(Recur.WEEKLY, new DateTime(untilDate));
		}
		recur.getDayList().add(WeekDay.SA);
		recur.getDayList().add(WeekDay.SU);
		recur.setInterval(interval);
		RRule rrule = new RRule(recur);
		System.out.println("**event..rrule:" + rrule);

		VEvent vEvent = new VEvent(new DateTime(startDate), new DateTime(endDate), subject);
		System.out.println("**event..:" + vEvent);

		vEvent.getProperties().add(rrule);
		
		printAllPeriodList(startDate, endDate, vEvent,untilDate);

	}
	
	public static void icalMethodForMonthByDay(String subject, java.util.Date startDate, java.util.Date endDate, Integer interval, Integer count, java.util.Date untilDate)
	{
		Recur recur = new Recur(Recur.MONTHLY, count);
		recur.getMonthDayList().add(31);
		recur.setInterval(interval);
		RRule rrule = new RRule(recur);
		System.out.println("**event..rrule:" + rrule);

		VEvent vEvent = new VEvent(new DateTime(startDate), new DateTime(endDate), subject);
		System.out.println("**event..:" + vEvent);

		vEvent.getProperties().add(rrule);
		
		printAllPeriodList(startDate, endDate, vEvent,untilDate);

	}
	
	public static void icalMethodForMonthDayOfWeek(String subject, java.util.Date startDate, java.util.Date endDate, Integer interval, Integer count, java.util.Date untilDate)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		int dayOfWeekInMonth = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		if(dayOfWeekInMonth > 4)
		{
			dayOfWeekInMonth = -1;
		}
		System.out.println("Ical4Test:icalMethodForMonthDayOfWeek:c.get(Calendar.DAY_OF_WEEK_IN_MONTH)" + dayOfWeekInMonth);
		Recur recur = new Recur(Recur.MONTHLY, count);
		recur.getSetPosList().add(dayOfWeekInMonth);
		recur.getDayList().add(getMonthlyWeekDays(startDate));
		recur.setInterval(interval);
		RRule rrule = new RRule(recur);
		System.out.println("**event..rrule:" + rrule);

		VEvent vEvent = new VEvent(new DateTime(startDate), new DateTime(endDate), subject);
		System.out.println("**event..:" + vEvent);

		vEvent.getProperties().add(rrule);
		
		printAllPeriodList(startDate, endDate, vEvent,untilDate);

	}
	
	public static void icalMethodForYear(String subject, java.util.Date startDate, java.util.Date endDate, Integer interval, Integer count, java.util.Date untilDate)
	{
		Recur recur = new Recur(Recur.YEARLY, count);
		recur.getMonthList().add(2);
		recur.getMonthDayList().add(29);
		recur.setInterval(interval);
		RRule rrule = new RRule(recur);
		System.out.println("**event..rrule:" + rrule);

		VEvent vEvent = new VEvent(new DateTime(startDate), new DateTime(endDate), subject);
		System.out.println("**event..:" + vEvent);

		vEvent.getProperties().add(rrule);
		
		printAllPeriodList(startDate, endDate, vEvent,untilDate);

	}
	
	@SuppressWarnings("deprecation")
	public static void printAllPeriodList(java.util.Date startDate, java.util.Date endDate, VEvent vEvent, java.util.Date untilDate)
	{
		DateTime startDt = new DateTime(startDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		endCal.add(Calendar.YEAR, 100);
//		DateTime endDt = new DateTime(endCal.getTime());
		DateTime endDt = new DateTime(untilDate);
//		endDt.setTimeZone((TimeZone) TimeZone.getTimeZone("GMT"));
		
//		if(untilDate != null)
//		{
//			endDt = new DateTime(untilDate);
//		}
		
		Period period = new Period(startDt, endDt);
		System.out.println(period.getDuration());

		PeriodList periodList = vEvent.calculateRecurrenceSet(period);

//		System.out.println("...period list:"+ periodList);
		
		 Iterator<Period> iteratorTimes = periodList.iterator();
		 List<String> list1 = new ArrayList<>();
        while (iteratorTimes.hasNext())
        {
        	Period pn = iteratorTimes.next();
        	String startDateStr = sdf.format(pn.getStart().getTime());
        	String endDateStr = sdf.format(pn.getEnd().getTime());
        	System.out.println("**period:"+ startDateStr + " : "+ endDateStr);
        	
        	System.out.println("pn.getStart().getDate()" + pn.getStart().getDay());
        	System.out.println("**period:inside if:"+ startDateStr + " : "+ endDateStr);
        	list1.add(startDateStr);

        }
        System.out.println("Ical4Test:printAllPeriodList:" + list1);
	}
	
	public static WeekDay getMonthlyWeekDays(java.util.Date startDate)
	{
		WeekDay weekDay = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		if(Calendar.SUNDAY == weekday)
		{
			weekDay = WeekDay.SU;
		}
		if(Calendar.MONDAY == weekday)
		{
			weekDay = WeekDay.MO;
		}
		if(Calendar.TUESDAY == weekday)
		{
			weekDay = WeekDay.TU;
		}
		if(Calendar.WEDNESDAY == weekday)
		{
			weekDay = WeekDay.WE;
		}
		if(Calendar.THURSDAY == weekday)
		{
			weekDay = WeekDay.TH;
		}
		if(Calendar.FRIDAY == weekday)
		{
			weekDay = WeekDay.FR;
		}
		if(Calendar.SATURDAY == weekday)
		{
			weekDay = WeekDay.SA;
		}
		return weekDay;
	}
}