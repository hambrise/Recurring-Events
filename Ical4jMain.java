package com.recurring;

import java.text.ParseException;
import java.util.Scanner;

public class Ical4jMain {
	
	public static void main(String[] args)
	{
		Integer count = 0;
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);  
		System.out.println("Enter the Event Details");
		//Get Event name
		System.out.println("RecurringEventLoginTest:main:Enter Event Name");
		String subject = input.nextLine();
		//Get start Date
		System.out.println("RecurringEventLoginTest:main:Enter Start Date");
		String startDateStr = input.nextLine();
		//Get End Date
		System.out.println("RecurringEventLoginTest:main:Enter End Date");
		String endDateStr = input.nextLine();
		//Get Frequency
		System.out.println("RecurringEventLoginTest:main:Enter Frequency");
		String frequency = input.nextLine();
		// Get interval
		System.out.println("RecurringEventLoginTest:main:Enter Interval");
		Integer interval = input.nextInt();
		input.nextLine();
		System.out.println("RecurringEventLoginTest:main:Enter until date");
		String until = input.nextLine();
//		System.out.println("RecurringEventLoginTest:main:Enter count");
//		Integer count = input.nextInt();

		System.out.println("*************Ical4Test:main:Event*********" );

		//startdate and endDate convert to calendar
		java.util.Date startDate = null;
		java.util.Date untilDate = null;
		java.util.Date endDate = null;
		try {
			startDate = Ical4jUtil.sdf.parse(startDateStr);
			endDate = Ical4jUtil.sdf.parse(endDateStr);
			untilDate = Ical4jUtil.tformat.parse(until);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Ical4Test:main:untilDate" + untilDate);

		if("DAILY".equals(frequency))
		{
			Ical4jUtil.icalMethodForDay(subject, startDate, endDate, interval, count, untilDate);
		}
		if("WEEKLY".equals(frequency))
		{
			Ical4jUtil.icalMethodForWeek(subject, startDate, endDate, interval, count, untilDate);
		}

		if("MONTHLYDAY".equals(frequency))
		{
			Ical4jUtil.icalMethodForMonthByDay(subject, startDate, endDate, interval, count, untilDate);
		}

		if("MONTHLYWEEK".equals(frequency))
		{
			Ical4jUtil.icalMethodForMonthDayOfWeek(subject, startDate, endDate, interval, count, untilDate);
		}

		if("YEARLY".equals(frequency))
		{
			Ical4jUtil.icalMethodForYear(subject, startDate, endDate, interval, count, untilDate);
		}
	}
}