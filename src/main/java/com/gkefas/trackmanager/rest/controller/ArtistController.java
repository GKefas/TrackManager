package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.ArtistDTO;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.service.AlbumService;
import com.gkefas.trackmanager.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
