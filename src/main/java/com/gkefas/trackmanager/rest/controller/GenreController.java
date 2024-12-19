package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.entity.Genre;
import com.gkefas.trackmanager.service.GenreService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The GenreController is a REST controller that handles HTTP requests related to genre.
 * It provides endpoints to retrieve all genre.
 * <p>Endpoints:</p>
 *   <li>GET /api/genre: Retrieves all genre</li>
 *
 * @see GenreService
 */
@RestController
@RequestMapping("/api/genre")
public class GenreController {
	private final GenreService genreService;

	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping({"", "/"})
	public List<Genre> getAllGenre(Pageable pageable) {
		return genreService.getAllGenres(pageable);
	}
}
