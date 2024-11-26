package com.gkefas.trackmanager.dto;

import com.gkefas.trackmanager.entity.Album;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AlbumDTO {
	private Integer albumId;
	private String title;
	private String artistName;
	private List<TrackDTO> tracks;

	public AlbumDTO(Album album) {
		this.albumId = album.getAlbumId();
		this.title = album.getTitle();
		this.artistName = album.getArtist().getName();

		// Convert each Track to TrackDTO
		this.tracks = album.getTracks().stream()
				.map(TrackDTO::new)
				.collect(Collectors.toList());
	}
}
