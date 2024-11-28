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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AlbumId")
	private Integer albumId;

	@Column(name = "Title")
	private String title;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "ArtistId", referencedColumnName = "ArtistId")
	private Artist artist;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "album")
	private List<Track> tracks = new ArrayList<>();

	// No-argument constructor for JPA
	public Album() {
	}

	// Optionally, constructor with fields if needed

	public Album(Integer albumId, String title, Artist artist, List<Track> tracks) {
		this.albumId = albumId;
		this.title = title;
		this.artist = artist;
		this.tracks = tracks;
	}
}
