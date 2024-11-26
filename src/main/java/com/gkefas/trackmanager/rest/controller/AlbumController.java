package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.AlbumDTO;
import com.gkefas.trackmanager.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

	private final AlbumService albumService;

	@Autowired
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	// params = title || artistName
	@GetMapping({"", "/"})
	public List<AlbumDTO> getAllAlbums(@RequestParam(required = false) Map<String, String> filters) {
		System.out.println("Filters: " + filters);
		return albumService.getAlbumsByFilters(filters);
	}

	@GetMapping({"/{id}"})
	public Optional<AlbumDTO> getAlbumById(@PathVariable Integer id) {
		return albumService.getAlbumById(id);
	}
}
