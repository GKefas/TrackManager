package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.ArtistDTO;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.service.AlbumService;
import com.gkefas.trackmanager.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

	private final ArtistService artistService;

	@Autowired
	public ArtistController(ArtistService artistService, AlbumService albumService) {
		this.artistService = artistService;
	}

	@GetMapping({"", "/"})
	public List<Artist> getAllAlbums() {
		return artistService.getAllArtists();
	}

	@GetMapping("/{id}")
	public ArtistDTO getArtistById(@PathVariable int id) {
		return artistService.getArtistWithTracks(id);
	}

	@GetMapping("/id")
	public Optional<Artist> getArtistIdByName(@RequestParam String name) {
		name = name.trim();
		return artistService.getArtistByName(name);
	}
}
