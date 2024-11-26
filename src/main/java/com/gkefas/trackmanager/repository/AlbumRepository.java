package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
	List<Album> findByArtist_ArtistId(Integer artistId);

	@Query("SELECT a FROM Album a " +
			"JOIN a.artist ar " +
			"WHERE (:artistName IS NULL OR LOWER(ar.name) LIKE LOWER(CONCAT('%', :artistName, '%'))) " +
			"AND (:title IS NULL OR LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))) ")
	List<Album> findAlbumsByArtistAndTitle(String artistName, String title);
}
