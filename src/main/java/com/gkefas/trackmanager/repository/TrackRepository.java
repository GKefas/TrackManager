package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing track entities.<br>
 * Provides methods for querying tracks.
 */
public interface TrackRepository extends JpaRepository<Track, Integer> {
	List<Track> findByAlbum_AlbumId(Integer albumId);
}
