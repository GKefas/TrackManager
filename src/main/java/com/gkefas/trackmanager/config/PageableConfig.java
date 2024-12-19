package com.gkefas.trackmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * {@code PageableConfig} is a configuration class that customizes pagination handling in the application.
 * <p>
 * This configuration enables the customization of pagination parameters, including overriding the default
 * page numbering system. It ensures that the application uses a 1-based indexing system for pagination,
 * where {@code page=1} represents the first page of results, instead of the default 0-based index.
 * </p>
 * <p>
 * The configuration integrates a custom pageable resolver to handle pagination logic across the application.
 * </p>
 *
 * @see CustomPageableResolver
 * @see PageableHandlerMethodArgumentResolver
 */
@Configuration
public class PageableConfig implements WebMvcConfigurer {

	private final CustomPageableResolver customPageableResolver;

	@Autowired
	public PageableConfig(CustomPageableResolver customPageableResolver) {
		this.customPageableResolver = customPageableResolver;
	}


	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(customPageableResolver);
	}
}
