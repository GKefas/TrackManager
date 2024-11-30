package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.AlbumDTO;
import com.gkefas.trackmanager.rest.exception.NotFoundException;
import com.gkefas.trackmanager.service.AlbumService;
import com.gkefas.trackmanager.util.GlobalInitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The AlbumController class handles HTTP requests for managing albums.
 * It provides endpoints to retrieve all albums, retrieve also albums by parameters,
 * and fetch an album by its unique ID.
 *
 * <p>Endpoints:</p>
 * <ul>
 *   <li>GET /api/albums: Retrieves all albums or filters albums by title/artistName.</li>
 *   <li>GET /api/albums/{id}: Retrieves an album by its ID.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <ul>
 *   <li>GET /api/albums</li>
 *   <li>GET /api/albums?title=exampleTitle</li>
 *   <li>GET /api/albums?artistName=exampleArtist</li>
 *   <li>GET /api/albums/{id}</li>
 * </ul>
 *
 * @see AlbumService
 * @see NotFoundException
 */

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

	private final AlbumService albumService;
	private final GlobalInitBinder globalInitBinder;

	/**
	 * Constructs an AlbumController with the specified AlbumService and GlobalInitBinder.
	 *
	 * @param albumService     the service for handling album-related operations.
	 * @param globalInitBinder the binder for global initialization settings.
	 */
	@Autowired
	public AlbumController(AlbumService albumService, GlobalInitBinder globalInitBinder) {
		this.albumService = albumService;
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
	 * Retrieves a list of albums based on the provided filters.
	 * If no filters are provided, returns all albums.
	 *
	 * @param params a map containing optional Query Parameters:
	 *               <ul>
	 *                   <li>"title": part or full title of the album</li>
	 *                   <li>"artistName": part or full name of the artist</li>
	 *               </ul>
	 * @return a list of all AlbumsDTOs if no params provided OR<br>
	 * a list of AlbumDTOs matching the provided filters
	 */
	@GetMapping({"", "/"})
	public List<AlbumDTO> getAllAlbums(@RequestParam(required = false) Map<String, String> params) {
		return params.isEmpty() ? albumService.getAllAlbums()
				: albumService.getAlbumsByFilters(params);
	}

	/**
	 * Retrieves an album by its unique ID.
	 *
	 * @param id the unique identifier of the album.
	 * @return an Optional containing the AlbumDTO if found.
	 * @throws NotFoundException if the album with the given ID does not exist.
	 */
	@GetMapping({"/{id}"})
	public Optional<AlbumDTO> getAlbumById(@PathVariable Integer id) {
		if (id <= 0 || id > albumService.getAllAlbums().size()) {
			throw new NotFoundException("Album id not found - " + id);
		}
		return albumService.getAlbumById(id);
	}
}
