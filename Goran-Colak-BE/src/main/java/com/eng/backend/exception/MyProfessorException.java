package com.eng.backend.exception;

import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class MyProfessorException extends RuntimeException {
	
	public MyProfessorException(Map<String, String> parameters) {
		super("Professor with this data already exists, change Email: " + parameters);
	}

	public MyProfessorException(List<String> examNames) {
		super("Can't delete professor because has exams: " + examNames);
	}

}
