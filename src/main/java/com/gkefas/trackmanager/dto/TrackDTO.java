package com.gkefas.trackmanager.dto;

import com.gkefas.trackmanager.entity.Track;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDTO {
	private Integer trackId;
	private String name;
	private String composer;
	private Long milliseconds;
	private Integer bytes;
	private Double unitPrice;
	private String genre;
	private String mediaType;

	public TrackDTO() {

	}

	// Constructor to populate from Track entity
	public TrackDTO(Track track) {
		this.trackId = track.getTrackId();
		this.name = track.getName();
		this.composer = track.getComposer();
		this.milliseconds = track.getMilliseconds();
		this.bytes = track.getBytes();
		this.unitPrice = track.getUnitPrice();

		if (track.getGenre() != null) {
			this.genre = track.getGenre().getName();
		}

		if (track.getMediaType() != null) {
			this.mediaType = track.getMediaType().getName();
		}
	}
}
