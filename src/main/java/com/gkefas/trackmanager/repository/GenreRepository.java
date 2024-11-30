package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing genre entities.<br>
 * Provides methods for querying genre.
 */
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
