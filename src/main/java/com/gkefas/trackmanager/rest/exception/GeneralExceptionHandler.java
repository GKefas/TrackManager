package com.gkefas.trackmanager.rest.exception;

import com.gkefas.trackmanager.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

@RestControllerAdvice
public class GeneralExceptionHandler {
	private final ErrorResponse error;

	@Autowired
	public GeneralExceptionHandler(ErrorResponse error) {
		this.error = error;
	}

	@ExceptionHandler({
			NullPointerException.class,
			SQLException.class,
			IOException.class,
			RuntimeException.class
	})
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(e.getMessage());
		error.setErrorTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleClientError(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// Handle timeout exceptions (408 Request Timeout)
	@ExceptionHandler(SocketTimeoutException.class)
	public ResponseEntity<ErrorResponse> handleTimeoutException() {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
		errorResponse.setMessage("Request timed out. Please try again later.");
		errorResponse.setErrorTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(errorResponse, HttpStatus.REQUEST_TIMEOUT);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NotFoundException e) {
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setErrorTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}


}
