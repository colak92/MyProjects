package com.eng.backend.exception;

@SuppressWarnings("serial")
public class MyStudentException extends RuntimeException {

	public MyStudentException(String examName) {
		super("Can't delete student because has exam: " + examName);
	}

}
