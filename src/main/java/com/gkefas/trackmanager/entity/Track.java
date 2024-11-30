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


	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "AlbumId", referencedColumnName = "AlbumId")
	private Album album;


	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "GenreId", referencedColumnName = "GenreId")
	private Genre genre;


	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "MediaTypeId", referencedColumnName = "MediaTypeId")
	private MediaType mediaType;


	public Track() {

	}


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
