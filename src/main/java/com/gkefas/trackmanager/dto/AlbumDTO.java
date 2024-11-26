package com.gkefas.trackmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumDTO {
	private Integer albumId;
	private String title;
	private String artistName;
	private List<TrackDTO> tracks;

}
