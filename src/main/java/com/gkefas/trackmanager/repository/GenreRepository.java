package com.gkefas.trackmanager.repository;

import com.gkefas.trackmanager.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
