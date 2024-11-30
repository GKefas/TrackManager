package com.gkefas.trackmanager.rest.exception;

/**
 * Custom exception class for handling "Not Found" errors.
 * This exception is thrown when a requested resource (e.g., an album, track, or artist) is not found.
 * It extends the {@link RuntimeException} class, allowing it to be thrown during runtime without explicit declaration.
 * <p>Constructors:</p>
 * <ul>
 *   <li>{@link #NotFoundException(String message)} - Constructs a new exception with the specified detail message.</li>
 *   <li>{@link #NotFoundException(String message, Throwable cause)} - Constructs a new exception with the specified detail message and cause.</li>
 *   <li>{@link #NotFoundException(Throwable cause)} - Constructs a new exception with the specified cause.</li>
 * </ul>
 * <p>This exception can be used across the application to indicate when a resource cannot be found.</p>
 *
 * @see RuntimeException
 */
public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
