package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.rest.exception.NotFoundException;
import com.gkefas.trackmanager.service.TrackService;
import com.gkefas.trackmanager.util.GlobalInitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

	private final TrackService trackService;
	private final GlobalInitBinder globalInitBinder;

	@Autowired
	public TrackController(TrackService trackService, GlobalInitBinder globalInitBinder) {
		this.trackService = trackService;
		this.globalInitBinder = globalInitBinder;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		globalInitBinder.initBinder(binder);
	}

	@GetMapping({"", "/"})
	public List<TrackDTO> getAllTracks() {
		List<TrackDTO> tracks = trackService.getAllTracks();
		if (tracks.isEmpty()) {
			throw new NotFoundException("No tracks found");
		}
		return tracks;
	}

	@GetMapping("/{id}")
	public Optional<TrackDTO> getTrackById(@PathVariable int id) {
		if (id <= 0 || id > trackService.getAllTracks().size()) {
			throw new NotFoundException("Track id not found - " + id);
		}
		return trackService.getTrackById(id);
	}
}
