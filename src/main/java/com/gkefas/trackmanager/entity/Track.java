package com.gkefas.trackmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "track")
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TrackId")
	private Integer trackId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Composer")
	private String composer;

	@Column(name = "Milliseconds")
	private Long milliseconds;

	@Column(name = "Bytes")
	private Integer bytes;

	@Column(name = "UnitPrice")
	private Double unitPrice;

	// Many-to-one relationship with Album
	@ManyToOne
	@JoinColumn(name = "AlbumId", referencedColumnName = "AlbumId")
	private Album album;

	// Many-to-one relationship with Genre
	@ManyToOne
	@JoinColumn(name = "GenreId", referencedColumnName = "GenreId")
	private Genre genre;

	// Many-to-one relationship with MediaTypeRepository
	@ManyToOne
	@JoinColumn(name = "MediaTypeId", referencedColumnName = "MediaTypeId")
	private MediaType mediaType;

	// No-argument constructor for JPA
	public Track() {

	}

	// Optionally, constructor with fields if needed
	public Track(Integer trackId, String name, String composer, Long milliseconds, Integer bytes, Double unitPrice, Album album, Genre genre, MediaType mediaType) {
		this.trackId = trackId;
		this.name = name;
		this.composer = composer;
		this.milliseconds = milliseconds;
		this.bytes = bytes;
		this.unitPrice = unitPrice;
		this.album = album;
		this.genre = genre;
		this.mediaType = mediaType;
	}
}
