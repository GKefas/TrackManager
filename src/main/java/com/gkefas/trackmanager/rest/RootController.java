package com.gkefas.trackmanager.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
	@GetMapping({"/api", "/api/"})
	public String api() {
		return "index";
	}
}
