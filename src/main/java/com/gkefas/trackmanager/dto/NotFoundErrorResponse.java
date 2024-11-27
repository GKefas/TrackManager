package com.gkefas.trackmanager.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NotFoundErrorResponse {
	private int status;
	private String message;
	private long errorTimeStamp;

	public NotFoundErrorResponse() {
	}

	public NotFoundErrorResponse(int status, String message, long errorTimeStamp) {
		this.status = status;
		this.message = message;
		this.errorTimeStamp = errorTimeStamp;
	}
}
