package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.AlbumDTO;
import com.gkefas.trackmanager.rest.exception.NotFoundException;
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
		List<AlbumDTO> albums = albumService.getAlbumsByFilters(filters);
		if (albums.isEmpty()) {
			throw new NotFoundException("No albums found");
		}
		return albums;
	}

	@GetMapping({"/{id}"})
	public Optional<AlbumDTO> getAlbumById(@PathVariable Integer id) {
		if (id <= 0 || id > albumService.getAllAlbums().size()) {
			throw new NotFoundException("Album id not found - " + id);
		}
		return albumService.getAlbumById(id);
	}
}
