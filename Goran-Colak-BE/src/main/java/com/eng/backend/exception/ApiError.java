package com.eng.backend.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String message;
	private String debugMessage;
	private String constraint;

	public ApiError() {
		// TODO Auto-generated constructor stub
	}

	public ApiError(HttpStatus status, String message, String debugMessage, String constraint) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = debugMessage;
		this.constraint = constraint;
	}

	public ApiError(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	// getters and setters
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

}
