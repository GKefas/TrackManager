package com.gkefas.trackmanager.dto.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Represents an error response structure.<br>
 * Used for standardizing error responses with status, message, and timestamp.
 * <p>It is designed for reuse across other applications.</p>
 */
@Setter
@Getter
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ErrorResponse {
	private int status;
	private String message;
	private long errorTimeStamp;

	public ErrorResponse() {
	}

	public ErrorResponse(int status, String message, long errorTimeStamp) {
		this.status = status;
		this.message = message;
		this.errorTimeStamp = errorTimeStamp;
	}
}
