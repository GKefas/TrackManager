package com.gkefas.trackmanager.rest.exception;

import com.gkefas.trackmanager.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
	public ResponseEntity<ErrorResponse> handleException() {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
		errorResponse.setMessage("Request timed out. Please try again later.");
		errorResponse.setErrorTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(errorResponse, HttpStatus.REQUEST_TIMEOUT);
	}


	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException e) {
		StringBuilder wrongIdProvided = new StringBuilder("unknown");

		Pattern pattern = Pattern.compile("\"([^\"]*)\"");
		Matcher matcher = pattern.matcher(e.getMessage());

		Stream.of(matcher)
				.filter(Matcher::find)
				.map(m -> m.group(1))
				.findFirst()
				.ifPresent(id -> {
					wrongIdProvided.setLength(0);
					wrongIdProvided.append(id);
				});

		String errorMessage = "Can't resolve id - '%s'".formatted(wrongIdProvided);
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(errorMessage);
		error.setErrorTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


}
