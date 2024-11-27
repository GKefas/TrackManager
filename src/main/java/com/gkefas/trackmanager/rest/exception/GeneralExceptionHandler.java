package com.gkefas.trackmanager.rest.exception;

import com.gkefas.trackmanager.dto.NotFoundErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
	private final NotFoundErrorResponse error;

	@Autowired
	public GeneralExceptionHandler(NotFoundErrorResponse error) {
		this.error = error;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<NotFoundErrorResponse> handleException(Exception e) {
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setErrorTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<NotFoundErrorResponse> handleException(NotFoundException e) {
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setErrorTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}


}
