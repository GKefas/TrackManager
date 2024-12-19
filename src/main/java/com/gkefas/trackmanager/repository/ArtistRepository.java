package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Artist entities.<br>
 * Provides methods for querying artists.
 */
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
	Page<Artist> findByNameIgnoreCase(String name, Pageable pageable);

	long count();
}
