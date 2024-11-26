package com.gkefas.trackmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

	public Integer getArtistId() {
		return artistId;
	}

	public void setArtistId(Integer artistId) {
		this.artistId = artistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Artist{" +
				"artistId=" + artistId +
				", name='" + name + '\'' +
				'}';
	}
}
