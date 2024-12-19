package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.rest.exception.NotFoundException;
import com.gkefas.trackmanager.service.TrackService;
import com.gkefas.trackmanager.util.GlobalInitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The TrackController is a REST controller that handles HTTP requests related to tracks.
 * It provides endpoints for retrieving all tracks and fetching a track by its unique ID.
 * <p>Endpoints:</p>
 * <ul>
 *   <li>GET /api/tracks: Retrieves all tracks.</li>
 *   <li>GET /api/tracks/{id}: Retrieves a specific track by ID.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <ul>
 *   <li>GET /api/tracks</li>
 *   <li>GET /api/tracks/{id}</li>
 * </ul>
 *
 * @see TrackService
 * @see NotFoundException
 */
@RestController
@RequestMapping("/api/tracks")
public class TrackController {

	private final TrackService trackService;
	private final GlobalInitBinder globalInitBinder;

	/**
	 * Constructor for initializing the TrackController.
	 *
	 * @param trackService     the service class for track-related operations
	 * @param globalInitBinder the global binder to handle data binding
	 */
	@Autowired
	public TrackController(TrackService trackService, GlobalInitBinder globalInitBinder) {
		this.trackService = trackService;
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
	 * Retrieves a list of tracks based on the provided filters.
	 * If no filters are provided, returns all tracks.
	 *
	 * @param params a map containing optional Query Parameters:
	 * @param pageable for pagination
	 * @return a list of all TrackDTOs if no params provided OR<br>
	 * a list of TrackDTOs matching the provided filters
	 */
	@GetMapping({"", "/"})
	public List<TrackDTO> getAllTracks(@RequestParam(required = false) Map<String, String> params,
	                                   Pageable pageable) {
		return params.isEmpty() ? trackService.getAllTracks()
				: trackService.getTracksByFilters(params, pageable);
	}

	/**
	 * Retrieves a track by its unique ID.
	 * If the track with the given ID does not exist, a NotFoundException is thrown.
	 *
	 * @param id the unique identifier of the track
	 * @return an Optional containing the TrackDTO if found
	 * @throws NotFoundException if the track with the given ID does not exist.
	 */
	@GetMapping("/{id}")
	public Optional<TrackDTO> getTrackById(@PathVariable Integer id) {
		if (id <= 0 || id > trackService.getAllTracks().size()) {
			throw new NotFoundException("Track id not found - " + id);
		}
		return trackService.getTrackById(id);
	}
}
