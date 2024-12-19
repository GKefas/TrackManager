package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.dto.ArtistDTO;
import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.entity.Track;
import com.gkefas.trackmanager.repository.AlbumRepository;
import com.gkefas.trackmanager.repository.ArtistRepository;
import com.gkefas.trackmanager.repository.TrackRepository;
import com.gkefas.trackmanager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class that handles business logic related to artists, albums, and tracks.
 * It provides methods to retrieve artist information along with their associated albums and tracks.
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllArtists(Pageable)} - Retrieves all artists from the database.</li>
 *   <li>{@link #getArtistByName(String name,Pageable)} - Retrieves an artist by their name (case-insensitive).</li>
 *   <li>{@link #getArtistWithTracksById(Integer artistId)} - Retrieves an artist by their ID along with their albums and tracks.</li>
 * </ul>
 * <p>This service interacts with the repositories for {@link Artist}, {@link Album}, and {@link Track} entities.</p>
 * <p>The {@link MapperUtil} is used to convert entity objects to DTOs (Data Transfer Objects) for the response.</p>
 *
 * @see ArtistRepository
 * @see TrackRepository
 * @see AlbumRepository
 * @see MapperUtil
 * @see ArtistDTO
 * @see TrackDTO
 */
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

	public List<Artist> getAllArtists(Pageable pageable) {
		return artistRepository.findAll(pageable).getContent();
	}

	public Page<Artist> getArtistByName(String name, Pageable pageable) {
		return artistRepository.findByNameIgnoreCase(name, pageable);
	}

	public long getArtistsCount() {
		return artistRepository.count();
	}

	/**
	 * Retrieves an artist by their ID along with the list of their tracks.
	 * This method also fetches all albums for the artist and collects tracks from each album.
	 *
	 * @param artistId the ID of the artist.
	 * @return an {@link ArtistDTO} containing artist details along with their tracks.
	 * @throws RuntimeException if the artist is not found.
	 */
	public ArtistDTO getArtistWithTracksById(Integer artistId) {
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
