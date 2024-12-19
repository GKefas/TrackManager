package com.gkefas.trackmanager.rest.exception;

/**
 * Custom exception class for handling Pagination parameters errors.
 * This exception is thrown when pagination parameters are invalid.
 * It extends the {@link RuntimeException} class, allowing it to be thrown during runtime without explicit declaration.
 * <p>Constructors:</p>
 * <ul>
 *   <li>{@link #IllegalPaginationParamException(String message)} - Constructs a new exception with the specified detail message.</li>
 *   <li>{@link #IllegalPaginationParamException(String message, Throwable cause)} - Constructs a new exception with the specified detail message and cause.</li>
 *   <li>{@link #IllegalPaginationParamException(Throwable cause)} - Constructs a new exception with the specified cause.</li>
 * </ul>
 *
 * @see RuntimeException
 */
public class IllegalPaginationParamException extends RuntimeException {

	public IllegalPaginationParamException(String message) {
		super(message);
	}

	public IllegalPaginationParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalPaginationParamException(Throwable cause) {
		super(cause);
	}
}
