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

/**
 * Service class for managing albums. It provides methods to retrieve all albums,
 * retrieve albums by ID, and retrieve albums based on specific filters such as
 * artist name and album title.
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllAlbums()} - Retrieves a list of all albums.</li>
 *   <li>{@link #getAlbumById(Integer id)} - Retrieves an album by its ID.</li>
 *   <li>{@link #getAlbumsByFilters(Map params)} - Retrieves albums filtered by artist name and/or title.</li>
 * </ul>
 * <p>This service interacts with the {@link AlbumRepository} to fetch album data
 * and uses {@link MapperUtil} to convert the {@link Album} entities into {@link AlbumDTO} objects
 * for the response.</p>
 *
 * @see AlbumRepository
 * @see MapperUtil
 * @see AlbumDTO
 */
@Service
public class AlbumService {

	private final AlbumRepository albumRepository;
	private final MapperUtil mapperUtil;

	@Autowired
	public AlbumService(AlbumRepository albumRepository, MapperUtil mapperUtil) {
		this.albumRepository = albumRepository;
		this.mapperUtil = mapperUtil;
	}

	public List<AlbumDTO> getAllAlbums() {
		List<Album> albums = albumRepository.findAll();
		return albums.stream().map(mapperUtil::toAlbumDTO).collect(Collectors.toList());
	}


	public Optional<AlbumDTO> getAlbumById(Integer id) {
		Optional<Album> album = albumRepository.findById(id);
		return album.map(mapperUtil::toAlbumDTO);
	}

	/**
	 * Retrieves albums based on provided filters.
	 * <p>This method fetches albums filtered by artist name and/or album title. It returns all
	 * albums if no filters are provided.</p>
	 *
	 * @param filters a map containing optional filters:<br>
	 *                 - "artistName": part or full name of the artist<br>
	 *                 - "title": part or full title of the album
	 * @return a list of {@link AlbumDTO} objects matching the provided filters.
	 */
	public List<AlbumDTO> getAlbumsByFilters(Map<String, String> filters) {
		String artistName = filters.get("artistName");
		String title = filters.get("title");

		List<Album> albums = albumRepository.findAlbumsByArtistAndTitle(artistName, title);

		return albums.stream()
				.map(mapperUtil::toAlbumDTO)
				.collect(Collectors.toList());
	}
}
