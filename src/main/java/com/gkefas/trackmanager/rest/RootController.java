package com.gkefas.trackmanager.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	@GetMapping({"/api", "/api/"})
	public String api() {
		return "api";
	}
}
