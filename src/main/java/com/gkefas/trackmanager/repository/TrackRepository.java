package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing track entities.<br>
 * Provides methods for querying tracks.
 */
public interface TrackRepository extends JpaRepository<Track, Integer> {
	List<Track> findByAlbum_AlbumId(Integer albumId);

	@Query("SELECT t FROM Track t " +
			"JOIN t.album a " +
			"JOIN a.artist ar " +
			"JOIN t.genre g " +
			"JOIN t.mediaType mt " +
			"WHERE (:name IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
			"AND (:composer IS NULL OR LOWER(t.composer) LIKE LOWER(CONCAT('%', :composer, '%'))) " +
			"AND (:milliseconds IS NULL OR t.milliseconds = :milliseconds) " +
			"AND (:bytes IS NULL OR t.bytes = :bytes) " +
			"AND (:unitPrice IS NULL OR t.unitPrice = :unitPrice) " +
			"AND (:genre IS NULL OR LOWER(g.name) LIKE LOWER(CONCAT('%', :genre, '%'))) " +
			"AND (:mediaType IS NULL OR LOWER(mt.name) LIKE LOWER(CONCAT('%', :mediaType, '%'))) " +
			"AND (:artistName IS NULL OR LOWER(ar.name) LIKE LOWER(CONCAT('%', :artistName, '%'))) " +
			"AND (:title IS NULL OR LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))) ")
	List<Track> findByFilters(
			@Param("name") String name,
			@Param("composer") String composer,
			@Param("milliseconds") Integer milliseconds,
			@Param("bytes") Integer bytes,
			@Param("unitPrice") Float unitPrice,
			@Param("genre") String genre,
			@Param("mediaType") String mediaType,
			@Param("artistName") String artistName,
			@Param("title") String title);
}
