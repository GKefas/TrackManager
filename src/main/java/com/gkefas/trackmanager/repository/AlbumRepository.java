package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Album;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing Album entities.<br>
 * Provides methods for querying albums.
 */
public interface AlbumRepository extends JpaRepository<Album, Integer> {
	List<Album> findByArtist_ArtistId(Integer artistId);


	/**
	 * Retrieves albums based on artist name and/or album title.
	 * If either or both parameters are null, the corresponding filter is ignored.
	 *
	 * @param artistName the partial or full name of the artist.
	 * @param title      the partial or full title of the album.
	 * @param pageable   for pagination
	 * @return a list of albums matching the provided filters.
	 */
	@Query("SELECT a FROM Album a " +
			"JOIN a.artist ar " +
			"WHERE (:artistName IS NULL OR LOWER(ar.name) LIKE LOWER(CONCAT('%', :artistName, '%'))) " +
			"AND (:title IS NULL OR LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))) ")
	List<Album> findAlbumsByArtistAndTitle(@Param("artistName") String artistName, @Param("title") String title, Pageable pageable);
}
