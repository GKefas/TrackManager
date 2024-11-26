package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Integer> {
}
