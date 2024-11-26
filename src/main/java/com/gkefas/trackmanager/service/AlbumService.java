package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.repository.AlbumRepository;
import com.gkefas.trackmanager.dto.AlbumDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

	private final AlbumRepository albumRepository;

	@Autowired
	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

	// Get all albums
	public List<AlbumDTO> getAllAlbums() {
		List<Album> albums = albumRepository.findAll();
		return albums.stream()
				.map(AlbumDTO::new)
				.collect(Collectors.toList());
	}

	// Get album by ID
	public Optional<AlbumDTO> getAlbumById(Integer id) {
		Optional<Album> album = albumRepository.findById(id);
		return album.map(AlbumDTO::new);
	}

}
