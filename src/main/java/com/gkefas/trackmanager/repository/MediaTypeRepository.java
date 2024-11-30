package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing mediaTypes entities.<br>
 * Provides methods for querying mediaTypes.
 */
public interface MediaTypeRepository extends JpaRepository<MediaType, Integer> {
}
