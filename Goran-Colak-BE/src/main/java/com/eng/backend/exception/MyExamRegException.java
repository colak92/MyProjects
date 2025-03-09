package com.eng.backend.exception;

import java.util.HashMap;
import java.util.List;

@SuppressWarnings("serial")
public class MyExamRegException extends RuntimeException {
	
	public MyExamRegException( HashMap<String, Integer> wrongYearOfStudyMap) {
		super("You can't register exam(s): " + wrongYearOfStudyMap.keySet() + " for this year(s) of study: " + wrongYearOfStudyMap.values());
	}
	
	public MyExamRegException(String examPeriodStart, Long numberOfDays, List<String> examNamesList) {
		super("You can register the exam(s) " + examNamesList + " in the last week before the currently active exam period: " + examPeriodStart + ", you need to wait (days): " + numberOfDays + ", without this day");
	}

	public MyExamRegException(String studentName, List<String> examNamesList) {
		super("This student: " + studentName + " already registered this exam(s): " + examNamesList);
	}

}
