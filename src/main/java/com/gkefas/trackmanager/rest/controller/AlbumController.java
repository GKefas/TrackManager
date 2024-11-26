package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.AlbumDTO;
import com.gkefas.trackmanager.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

	private final AlbumService albumService;

	@Autowired
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@GetMapping({"", "/"})
	public List<AlbumDTO> getAllAlbums() {
		return albumService.getAllAlbums();
	}

	@GetMapping({"/{id}"})
	public Optional<AlbumDTO> getAlbumById(@PathVariable Integer id) {
		return albumService.getAlbumById(id);
	}
}
