package com.gkefas.trackmanager.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TokenConfig {
	@Value("${access.code}")
	private String accessCode;
}
