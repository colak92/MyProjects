package com.eng.backend.exception;

@SuppressWarnings("serial")
public class MyExamException extends RuntimeException {
	
	public MyExamException() {
		super("You have chosen a date that is not in the exam period!");
	}
	
	public MyExamException(String subjectName) {
		super("There is already an exam period for this subject: " + subjectName);
	}
	
	public MyExamException(Integer yearOfStudy) {
		super("You can't register exam for this year of study: " + yearOfStudy);
	}
	
}
