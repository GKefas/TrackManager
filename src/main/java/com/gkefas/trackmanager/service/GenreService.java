package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.entity.Genre;
import com.gkefas.trackmanager.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Genre. It provides methods to retrieve Genres
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllGenres(Pageable)} - Retrieves a list of all genres.</li>
 * </ul>
 * <p>This service interacts with the {@link GenreRepository} to fetch mediaType data </p>
 *
 * @see GenreRepository
 */
@Service
public class GenreService {

	private final GenreRepository genreRepository;

	@Autowired
	public GenreService(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	public List<Genre> getAllGenres(Pageable pageable) {
		return genreRepository.findAll(pageable).getContent();
	}
}
