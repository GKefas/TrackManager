package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.entity.Genre;
import com.gkefas.trackmanager.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The GenreController is a REST controller that handles HTTP requests related to genre.
 * It provides endpoints to retrieve all genre.
 * <p>Endpoints:</p>
 * <ul>
 *   <li>GET /api/genre: Retrieves all genre</li>
 * </ul>
 *
 * @see GenreService
 */
@Service
public class GenreService {

	private final GenreRepository genreRepository;

	public GenreService(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}


	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}
}
