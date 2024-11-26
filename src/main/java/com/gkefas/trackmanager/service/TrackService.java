package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.entity.Track;
import com.gkefas.trackmanager.repository.TrackRepository;
import com.gkefas.trackmanager.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackService {

	private final TrackRepository trackRepository;
	private final MapperUtil mapperUtil;

	public TrackService(TrackRepository trackRepository, MapperUtil mapperUtil) {
		this.trackRepository = trackRepository;
		this.mapperUtil = mapperUtil;
	}

	public List<TrackDTO> getAllTracks() {
		List<Track> tracks = trackRepository.findAll();
		return tracks.stream()
				.map(mapperUtil::toTrackDTO)
				.collect(Collectors.toList());
	}

	public Optional<TrackDTO> getTrackById(int id) {
		Optional<Track> track = trackRepository.findById(id);
		return track.map(mapperUtil::toTrackDTO);
	}
}
