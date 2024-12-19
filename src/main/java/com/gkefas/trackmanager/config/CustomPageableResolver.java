package com.gkefas.trackmanager.config;

import com.gkefas.trackmanager.rest.exception.IllegalPaginationParamException;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Custom resolver to handle pagination parameters for the pageable request.
 * If no parameters are provided, defaults are applied (page = 0, size = 10).
 */
@Component
public class CustomPageableResolver extends PageableHandlerMethodArgumentResolver {
	private static final int DEFAULT_PAGE = 1; // Default page number (1-based)
	private static final int DEFAULT_SIZE = 20; // Default page size


	@Override
	public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		String pageParameter = webRequest.getParameter("page");
		String sizeParameter = webRequest.getParameter("size");

		// Guard Clause
		if (pageParameter == null && sizeParameter == null)
			return PageRequest.of(0, Integer.MAX_VALUE);

		int page = parsePageParameter(pageParameter);

		int size = parseSizeParameter(sizeParameter);

		return PageRequest.of(page - 1, size);
	}

	/**
	 * Parses and validates the page parameter.
	 *
	 * @param pageParameter the 'page' parameter from the request
	 * @return the parsed page number
	 * @throws IllegalPaginationParamException if the page number is invalid
	 */
	private int parsePageParameter(String pageParameter) {
		if (pageParameter == null || pageParameter.isEmpty()) {
			return CustomPageableResolver.DEFAULT_PAGE;
		}
		try {
			int page = Integer.parseInt(pageParameter);
			if (page <= 0) {
				throw new IllegalPaginationParamException("Page number must be greater than 0");
			}
			return page;
		} catch (NumberFormatException e) {
			throw new IllegalPaginationParamException("Invalid page number format");
		}
	}

	/**
	 * Parses and validates the size parameter.
	 *
	 * @param sizeParameter the 'size' parameter from the request
	 * @return the parsed page size
	 * @throws IllegalPaginationParamException if the size is invalid
	 */
	private int parseSizeParameter(String sizeParameter) {
		if (sizeParameter == null || sizeParameter.isEmpty()) {
			return CustomPageableResolver.DEFAULT_SIZE;
		}
		try {
			int size = Integer.parseInt(sizeParameter);
			if (size <= 0) {
				throw new IllegalPaginationParamException("Page size must be greater than 0");
			}
			return size;
		} catch (NumberFormatException e) {
			throw new IllegalPaginationParamException("Invalid page size format");
		}
	}
}
