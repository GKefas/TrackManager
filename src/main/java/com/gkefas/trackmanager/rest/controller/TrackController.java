package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.TrackDTO;
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

	private TrackService trackService;

	@Autowired
	public TrackController(TrackService trackService) {
		this.trackService = trackService;
	}

	@GetMapping({"", "/"})
	public List<TrackDTO> getAllTracks() {
		return trackService.getAllTracks();
	}

	@GetMapping("/{id}")
	public Optional<TrackDTO> getTrackById(@PathVariable int id) {
		return trackService.getTrackById(id);
	}
}
