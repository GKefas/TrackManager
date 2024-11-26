package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.repository.AlbumRepository;
import com.gkefas.trackmanager.dto.AlbumDTO;

import com.gkefas.trackmanager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

	private final AlbumRepository albumRepository;
	private final MapperUtil mapperUtil;

	@Autowired
	public AlbumService(AlbumRepository albumRepository, MapperUtil mapperUtil) {
		this.albumRepository = albumRepository;
		this.mapperUtil = mapperUtil;
	}

	// Get album by ID
	public Optional<AlbumDTO> getAlbumById(Integer id) {
		Optional<Album> album = albumRepository.findById(id);
		return album.map(mapperUtil::toAlbumDTO);
	}

	public List<AlbumDTO> getAlbumsByFilters(Map<String, String> filters) {
		String artistName = filters.get("artistName");
		String title = filters.get("title");

		List<Album> albums = albumRepository.findAlbumsByArtistAndTitle(artistName, title);

		return albums.stream()
				.map(mapperUtil::toAlbumDTO)
				.collect(Collectors.toList());
	}
}
