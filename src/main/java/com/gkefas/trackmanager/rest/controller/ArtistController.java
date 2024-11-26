package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.repository.ArtistRepository;
import com.gkefas.trackmanager.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

	private final ArtistService artistService;

	@Autowired
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	@GetMapping({"", "/"})
	public List<Artist> getAllAlbums() {
		return artistService.getAllArtists();
	}
}
