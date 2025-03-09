package com.eng.backend.exception;

import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class MyStudentException extends RuntimeException {
	
	public MyStudentException(Map<String, String> parameters) {
		super("Student with this data already exists, change IndexNumber or IndexYear or Email: " + parameters);
	}

	public MyStudentException(List<String> examNames) {
		super("You can't delete a student who has exams: " + examNames);
	}
	

}
