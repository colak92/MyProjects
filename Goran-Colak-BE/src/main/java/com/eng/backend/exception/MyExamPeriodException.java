package com.eng.backend.exception;

@SuppressWarnings("serial")
public class MyExamPeriodException extends RuntimeException {
	
	public MyExamPeriodException() {
		super("It is possible to create only one active exam period!");
	}
	
	public MyExamPeriodException(String startdate, String enddate) {
		super("Can't find free interval for this date interval: " + startdate + " and " + enddate);
	}
	
}
