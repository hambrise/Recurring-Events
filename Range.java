package com.outlookrecurring;

public class Range {
	
	private String type;
	private String startDate;
	private String endDate;
	private String recurrenceTimeZone;
	private Integer numberOfOccurrences;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRecurrenceTimeZone() {
		return recurrenceTimeZone;
	}
	public void setRecurrenceTimeZone(String recurrenceTimeZone) {
		this.recurrenceTimeZone = recurrenceTimeZone;
	}
	public Integer getNumberOfOccurrences() {
		return numberOfOccurrences;
	}
	public void setNumberOfOccurrences(Integer numberOfOccurrences) {
		this.numberOfOccurrences = numberOfOccurrences;
	}
}
