package com.gkefas.trackmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@Column(name = "GenreId")
	private Integer genreId;

	@Column(name = "Name")
	private String name;

	// No-argument constructor for JPA
	public Genre() {
	}

	// Optionally, constructor with fields if needed
	public Genre(Integer genreId, String name) {
		this.genreId = genreId;
		this.name = name;
	}
}

