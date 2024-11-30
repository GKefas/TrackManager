package com.gkefas.trackmanager.util;

import com.gkefas.trackmanager.dto.AlbumDTO;
import com.gkefas.trackmanager.dto.ArtistDTO;
import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.entity.Track;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * A utility class for mapping entities to their respective Data Transfer Objects (DTOs).
 * <p>This class provides methods to convert {@link Artist}, {@link Album}, and {@link Track} entities into their corresponding DTOs.</p>
 * <p>It simplifies the process of transferring data between the persistence layer (entities) and the presentation layer (DTOs).</p>
 * <p>The class is marked with {@link Component} to allow Spring to manage it as a bean.</p>
 *
 * @see ArtistDTO
 * @see AlbumDTO
 * @see TrackDTO
 * @see Artist
 * @see Album
 * @see Track
 */
@Component
public class MapperUtil {

	/**
	 * Converts an {@link Artist} entity to an {@link ArtistDTO}.
	 *
	 * @param artist the {@link Artist} entity to be converted.
	 * @return the corresponding {@link ArtistDTO} or {@code null} if the input entity is {@code null}.
	 */
	public ArtistDTO toArtistDTO(Artist artist) {
		if (artist == null) {
			return null;
		}
		ArtistDTO artistDTO = new ArtistDTO();
		artistDTO.setArtistId(artist.getArtistId());
		artistDTO.setName(artist.getName());
		// Do not map tracks here, it's handled in the
		return artistDTO;
	}

	/**
	 * Converts an {@link Album} entity to an {@link AlbumDTO}.
	 *
	 * @param album the {@link Album} entity to be converted.
	 * @return the corresponding {@link AlbumDTO} or {@code null} if the input entity is {@code null}.
	 */
	public AlbumDTO toAlbumDTO(Album album) {
		if (album == null) {
			return null;
		}

		AlbumDTO albumDTO = new AlbumDTO();
		albumDTO.setAlbumId(album.getAlbumId());
		albumDTO.setTitle(album.getTitle());
		albumDTO.setArtistName(album.getArtist().getName());

		// Map the tracks
		albumDTO.setTracks(album.getTracks().stream()
				.map(this::toTrackDTO)
				.collect(Collectors.toList()));
		return albumDTO;
	}

	/**
	 * Converts a {@link Track} entity to a {@link TrackDTO}.
	 *
	 * @param track the {@link Track} entity to be converted.
	 * @return the corresponding {@link TrackDTO} or {@code null} if the input entity is {@code null}.
	 */
	public TrackDTO toTrackDTO(Track track) {
		if (track == null) {
			return null;
		}

		TrackDTO trackDTO = new TrackDTO();
		trackDTO.setTrackId(track.getTrackId());
		trackDTO.setName(track.getName());
		trackDTO.setComposer(track.getComposer());
		trackDTO.setMilliseconds(track.getMilliseconds());
		trackDTO.setBytes(track.getBytes());
		trackDTO.setUnitPrice(track.getUnitPrice());
		trackDTO.setGenre(track.getGenre().getName());
		trackDTO.setMediaType(track.getMediaType().getName());
		trackDTO.setAlbum(track.getAlbum().getTitle());
		return trackDTO;
	}
}
