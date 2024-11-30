package com.gkefas.trackmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing an artist.<br>
 * Used for transferring artist information along with their associated tracks.
 */
@Setter
@Getter
public class ArtistDTO {
	private Integer artistId;
	private String name;
	private List<TrackDTO> tracks;
}
