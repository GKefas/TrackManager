package com.gkefas.trackmanager.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for a music track.<br>
 * Used for transferring track information.
 */
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
