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
	private String album;
}
