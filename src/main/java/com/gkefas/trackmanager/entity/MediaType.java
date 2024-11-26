package com.gkefas.trackmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "MediaType")
public class MediaType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MediaTypeId")
	private Integer mediaTypeId;

	@Column(name = "Name")
	private String name;

	// No-argument constructor for JPA
	public MediaType() {
	}

	// Optionally, constructor with fields if needed
	public MediaType(Integer mediaTypeId, String name) {
		this.mediaTypeId = mediaTypeId;
		this.name = name;
	}
}
