package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

	private final ArtistRepository artistRepository;

	@Autowired
	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}

	public List<Artist> getAllArtists() {
		return artistRepository.findAll();
	}

	public Optional<Artist> getArtistById(int id) {
		return artistRepository.findById(id);
	}
}
