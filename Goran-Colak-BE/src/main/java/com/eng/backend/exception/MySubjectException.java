package com.eng.backend.exception;

@SuppressWarnings("serial")
public class MySubjectException extends RuntimeException {

	public MySubjectException(String examName) {
		super("Can't delete subject because has exam: " + examName);
	}
}
