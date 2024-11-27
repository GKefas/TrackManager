package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.dto.ArtistDTO;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.rest.exception.NotFoundException;
import com.gkefas.trackmanager.service.ArtistService;
import com.gkefas.trackmanager.util.GlobalInitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

	private final ArtistService artistService;
	private final GlobalInitBinder globalInitBinder;

	@Autowired
	public ArtistController(ArtistService artistService, GlobalInitBinder globalInitBinder) {
		this.artistService = artistService;
		this.globalInitBinder = globalInitBinder;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		globalInitBinder.initBinder(binder);
	}

	@GetMapping({"", "/"})
	public List<Artist> getAllAlbums(@RequestParam(required = false) String name) {
		if (name == null || name.isEmpty())
			return artistService.getAllArtists();
		Artist artist = artistService.getArtistByName(name);
		return artist != null ? List.of(artist) : List.of();
	}

	@GetMapping("/{id}")
	public ArtistDTO getArtistById(@PathVariable Integer id) {
		if (id <= 0 || id > artistService.getAllArtists().size()) {
			throw new NotFoundException("Artist id not found - " + id);
		}
		return artistService.getArtistWithTracks(id);
	}
}
