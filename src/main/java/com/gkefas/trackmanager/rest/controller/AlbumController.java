package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

	private final AlbumService albumService;

	@Autowired
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@GetMapping({"/", ""})
	public List<Album> getAllAlbums() {
		return albumService.getAllAlbums();
	}

}
