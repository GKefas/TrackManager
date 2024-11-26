package com.gkefas.trackmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "album")
public class Album {

	@Id
	@Column(name = "AlbumId")
	private Integer albumId;

	@Column(name = "Title")
	private String title;

	@ManyToOne
	@JoinColumn(name = "ArtistId", referencedColumnName = "ArtistId")
	private Artist artist;
//
//	@OneToMany
//	@JoinColumn(name = "trackId")
//	private List<Track> tracks = new ArrayList<>();

	// No-argument constructor for JPA
	public Album() {
	}
}
