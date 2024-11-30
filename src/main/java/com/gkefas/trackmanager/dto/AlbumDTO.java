package com.gkefas.trackmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing an album.<br>
 * Used for transferring album information along with its associated tracks.
 */
@Getter
@Setter
public class AlbumDTO {
	private Integer albumId;
	private String title;
	private String artistName;
	private List<TrackDTO> tracks;

}
