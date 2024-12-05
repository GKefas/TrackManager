package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.entity.Genre;
import com.gkefas.trackmanager.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
	private final GenreService genreService;

	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping({"", "/"})
	public List<Genre> getAllGenre() {
		return genreService.getAllGenres();
	}
}
