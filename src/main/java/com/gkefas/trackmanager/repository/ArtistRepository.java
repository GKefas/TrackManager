package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}
