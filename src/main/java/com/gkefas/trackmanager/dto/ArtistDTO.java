package com.gkefas.trackmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ArtistDTO {
	private Integer artistId;
	private String name;
	private List<TrackDTO> tracks;

	public ArtistDTO() {
	}

	public ArtistDTO(Integer artistId, String name, List<TrackDTO> tracks) {
		this.artistId = artistId;
		this.name = name;
		this.tracks = new ArrayList<>(tracks);
	}
}
