package com.gkefas.trackmanager.util;

import com.gkefas.trackmanager.dto.AlbumDTO;
import com.gkefas.trackmanager.dto.ArtistDTO;
import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.entity.Album;
import com.gkefas.trackmanager.entity.Artist;
import com.gkefas.trackmanager.entity.Track;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class MapperUtil {

	public ArtistDTO toArtistDTO(Artist artist) {
		if (artist == null) {
			return null;
		}
		ArtistDTO artistDTO = new ArtistDTO();
		artistDTO.setArtistId(artist.getArtistId());
		artistDTO.setName(artist.getName());
		// Do not map tracks here, it's handled in the service
		return artistDTO;
	}

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
		return trackDTO;
	}
}
