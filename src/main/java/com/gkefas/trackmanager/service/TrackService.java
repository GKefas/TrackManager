package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.entity.Track;
import com.gkefas.trackmanager.repository.TrackRepository;
import com.gkefas.trackmanager.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing tracks. It provides methods to retrieve tracks
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllTracks()} - Retrieves a list of all tracks.</li>
 *   <li>{@link #getTrackById(int id)} - Retrieves a track by its ID.</li>
 * </ul>
 * <p>This service interacts with the {@link TrackRepository} to fetch track data
 * and uses {@link MapperUtil} to convert the {@link Track} entities into {@link TrackDTO} objects
 * for the response.</p>
 *
 * @see TrackRepository
 * @see MapperUtil
 * @see TrackDTO
 */
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
