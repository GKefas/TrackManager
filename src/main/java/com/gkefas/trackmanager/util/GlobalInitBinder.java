package com.gkefas.trackmanager.util;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Component
public class GlobalInitBinder {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Register custom editor to trim whitespace around Strings
		StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);  // true to trim both ends
		binder.registerCustomEditor(String.class, trimmerEditor);
	}
}
