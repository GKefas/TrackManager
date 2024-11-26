package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
	Optional<Artist> findByName(String name);
}