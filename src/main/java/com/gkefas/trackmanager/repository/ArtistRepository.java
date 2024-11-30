package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Artist entities.<br>
 * Provides methods for querying artists.
 */
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
	Artist findByNameIgnoreCase(String name);
}
