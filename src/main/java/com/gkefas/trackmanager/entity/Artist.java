package com.gkefas.trackmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "artist")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ArtistId")
	private Integer artistId;

	@Column(name = "Name")
	private String name;

	// No-argument constructor for JPA
	public Artist() {
	}

	// Optionally, constructor with fields if needed
	public Artist(Integer artistId, String name) {
		this.artistId = artistId;
		this.name = name;
	}
}
