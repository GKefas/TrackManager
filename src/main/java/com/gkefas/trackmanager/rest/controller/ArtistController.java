package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.ArtistDTO;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.rest.exception.NotFoundException;
import com.gkefas.trackmanager.service.ArtistService;
import com.gkefas.trackmanager.util.GlobalInitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ArtistController is a REST controller that handles HTTP requests related to artists.
 * It provides endpoints to retrieve all artists.
 * <p>Endpoints:</p>
 * <ul>
 *   <li>GET /api/artists: Retrieves all artists or artists by name.</li>
 *   <li>GET /api/albums/{id}: Retrieves an artist by its ID with his tracks.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <ul>
 *   <li>GET /api/artists</li>
 *   <li>GET /api/artists?name=exampleArtistName</li>
 *   <li>GET /api/artists/{id}</li>
 * </ul>
 *
 * @see ArtistService
 * @see NotFoundException
 */
@RestController
@RequestMapping("/api/artists")
public class ArtistController {

	private final ArtistService artistService;
	private final GlobalInitBinder globalInitBinder;

	/**
	 * Constructs an ArtistController with the specified ArtistService and GlobalInitBinder.
	 *
	 * @param artistService    the service for handling artist-related operations.
	 * @param globalInitBinder the binder for global initialization settings.
	 */
	@Autowired
	public ArtistController(ArtistService artistService, GlobalInitBinder globalInitBinder) {
		this.artistService = artistService;
		this.globalInitBinder = globalInitBinder;
	}

	/**
	 * Initializes the binder with global settings.
	 *
	 * @param binder the data binder.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		globalInitBinder.initBinder(binder);
	}

	/**
	 * Retrieves a list of artists, optionally filtered by the artist's name.
	 * If no name is provided, all artists are returned.
	 *
	 * @param name     the name of the artist to filter by (optional)
	 * @param pageable for pagination
	 * @return a list of artists matching the provided name or<br>
	 * all artists if no name is given
	 */
	@GetMapping({"", "/"})
	public List<Artist> getAllArtists(@RequestParam(required = false) String name, Pageable pageable) {
		if (name == null || name.isEmpty())
			return artistService.getAllArtists(pageable);
		Page<Artist> artists = artistService.getArtistByName(name, pageable);
		return artists != null ? artists.getContent() : List.of();
	}

	/**
	 * Retrieves an artist by their unique ID.
	 *
	 * @param id the unique identifier of the artist
	 * @return the ArtistDTO of the artist with the given ID
	 * @throws NotFoundException if the artist with the given ID does not exist
	 */
	@GetMapping("/{id}")
	public ArtistDTO getArtistById(@PathVariable Integer id) {
		if (id <= 0 || id > artistService.getArtistsCount()) {
			throw new NotFoundException("Artist id not found - " + id);
		}
		return artistService.getArtistWithTracksById(id);
	}
}
