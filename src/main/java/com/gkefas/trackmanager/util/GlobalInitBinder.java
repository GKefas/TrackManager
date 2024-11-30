package com.gkefas.trackmanager.util;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * A utility component for initializing custom data binding behavior globally across the application.
 * <p>This class is responsible for configuring a custom editor for binding string fields. Specifically,
 * it trims any leading and trailing whitespace from string inputs automatically.</p>
 * <p>The class is marked with the {@link Component} annotation to make it a Spring bean,
 * and the {@link InitBinder} annotation to bind the custom editor during request processing.</p>
 *
 * @see StringTrimmerEditor
 * @see WebDataBinder
 */
@Component
public class GlobalInitBinder {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);  // true to trim both ends
		binder.registerCustomEditor(String.class, trimmerEditor);
	}
}
