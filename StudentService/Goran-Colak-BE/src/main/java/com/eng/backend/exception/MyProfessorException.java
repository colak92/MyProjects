package com.eng.backend.exception;

@SuppressWarnings("serial")
public class MyProfessorException extends RuntimeException {

	public MyProfessorException(String examName) {
		super("Can't delete professor because has exam: " + examName);
	}

}
