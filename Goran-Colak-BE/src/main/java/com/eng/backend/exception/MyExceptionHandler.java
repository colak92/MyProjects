package com.eng.backend.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleResponseDatabaseExceptions(DataIntegrityViolationException ex, WebRequest request) {
		Throwable cause = ex.getRootCause();

		if (cause instanceof SQLIntegrityConstraintViolationException) {
			SQLIntegrityConstraintViolationException consEx = (SQLIntegrityConstraintViolationException) cause;
			String message = "";
			String constraint = "";
			HttpStatus httpStatus = null;
			if (consEx.getMessage().contains("UNIQUE") || consEx.getMessage().contains("Duplicate entry")) {
				message = "Cannot enter the same record twice";
				constraint = "DUPLICATED_RECORD";
				httpStatus = HttpStatus.BAD_REQUEST;
			} else if (consEx.getMessage().contains("foreign key constraint")) {
				message = "Record still have reference from other table";
				constraint = "USED_RECORD";
				httpStatus = HttpStatus.BAD_REQUEST;
			}

			return buildResponseEntity(new ApiError(httpStatus, message, consEx.getMessage(), constraint));
		} else if (ex.getCause() instanceof ConstraintViolationException) {
			return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Database error", ex));
		}
		
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()));
	}

	@ExceptionHandler(value = {	MyStudentException.class, 
								MyProfessorException.class, 
								MySubjectException.class,
								MyExamException.class, 
								MyExamPeriodException.class })
	public ResponseEntity<Object> handleResponseExceptions(Exception ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
