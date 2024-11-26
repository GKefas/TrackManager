package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.dto.ArtistDTO;
import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.repository.AlbumRepository;
import com.gkefas.trackmanager.repository.ArtistRepository;
import com.gkefas.trackmanager.repository.TrackRepository;
import com.gkefas.trackmanager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistService {

	private final ArtistRepository artistRepository;
	private final TrackRepository trackRepository;
	private final AlbumRepository albumRepository;
	private final MapperUtil mapperUtil;

	@Autowired
	public ArtistService(ArtistRepository artistRepository, TrackRepository trackRepository, AlbumRepository albumRepository, MapperUtil mapperUtil) {
		this.artistRepository = artistRepository;
		this.trackRepository = trackRepository;
		this.albumRepository = albumRepository;
		this.mapperUtil = mapperUtil;
	}

	public List<Artist> getAllArtists() {
		return artistRepository.findAll();
	}

	public Optional<Artist> getArtistByName(String name) {
		Optional<Artist> artist = artistRepository.findByName(name);
		if (artist.isEmpty())
			throw new RuntimeException("Artist not found");
		return artist;

	}

	public ArtistDTO getArtistWithTracks(Integer artistId) {
		// Fetch the artist by ID
		Optional<Artist> artistOptional = artistRepository.findById(artistId);
		if (artistOptional.isEmpty()) {
			throw new RuntimeException("Artist not found");
		}

		// Create ArtistDTO and map artist information
		Artist artist = artistOptional.get();
		ArtistDTO artistDTO = mapperUtil.toArtistDTO(artist);

		// Fetch all albums of the artist
		List<Album> albums = albumRepository.findByArtist_ArtistId(artist.getArtistId());

		// Collect tracks from all albums and map them to TrackDTO
		List<TrackDTO> trackDTOs = albums.stream()
				.flatMap(album -> trackRepository.findByAlbum_AlbumId(album.getAlbumId()).stream())
				.map(mapperUtil::toTrackDTO)
				.collect(Collectors.toList());

		// Set the list of tracks in the ArtistDTO
		artistDTO.setTracks(trackDTOs);
		return artistDTO;
	}

}
