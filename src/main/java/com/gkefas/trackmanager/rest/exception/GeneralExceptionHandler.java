package com.gkefas.trackmanager.rest.exception;

import com.gkefas.trackmanager.dto.infrastructure.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * The GeneralExceptionHandler is a global exception handler that handles various types of exceptions thrown
 * throughout the application. It provides custom responses for different exceptions, returning meaningful error messages
 * and appropriate HTTP status codes.
 * <p>Exception Handling:</p>
 * <ul>
 *   <li>Handles general exceptions like NullPointerException, SQLException, IOException.</li>
 *   <li>Handles method argument type mismatches and provides specific error messages for invalid IDs.</li>
 *   <li>Handles all other exceptions, returning a 400 Bad Request response with the exception message.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <ul>
 *   <li>If a method argument type mismatch occurs, returns a 400 Bad Request with the message: "Can't resolve id - 'xxx'"</li>
 *   <li>For general errors like NullPointerException, SQLException, or IOException, returns a 500 Internal Server Error with the message.</li>
 * </ul>
 *
 * @see ErrorResponse
 * @see Exception
 * @see MethodArgumentTypeMismatchException
 */
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
