package com.eng.backend.exception;

@SuppressWarnings("serial")
public class MyProfileException extends RuntimeException {
	
	public MyProfileException() {
		super("Confirm password must be the same as password!");
	}

}
