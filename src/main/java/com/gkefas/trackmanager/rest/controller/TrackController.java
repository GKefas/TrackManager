package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.rest.exception.NotFoundException;
import com.gkefas.trackmanager.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

	private final TrackService trackService;

	@Autowired
	public TrackController(TrackService trackService) {
		this.trackService = trackService;
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
