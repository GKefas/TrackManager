package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
