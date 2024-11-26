package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

	private final AlbumRepository albumRepository;

	@Autowired
	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

	// Get all albums
	public List<Album> getAllAlbums() {
		return albumRepository.findAll();
	}

	// Get album by ID
	public Optional<Album> getAlbumById(Integer id) {
		return albumRepository.findById(id);
	}

}
