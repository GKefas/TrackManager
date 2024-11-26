package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
	List<Album> findByArtist_ArtistId(Integer artistId);
}
