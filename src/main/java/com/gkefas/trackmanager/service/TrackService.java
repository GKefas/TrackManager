package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.dto.TrackDTO;
import com.gkefas.trackmanager.entity.Track;
import com.gkefas.trackmanager.repository.TrackRepository;
import com.gkefas.trackmanager.util.MapperUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

	/**
	 * Retrieves tracks based on provided filters.
	 *
	 * @param filters  a map containing optional filters
	 * @param pageable for pagination
	 * @return a list of {@link TrackDTO} objects matching the provided filters.
	 */
	public List<TrackDTO> getTracksByFilters(Map<String, String> filters, Pageable pageable) {
		String name = filters.get("name");
		String composer = filters.get("composer");
		Integer milliseconds = filters.containsKey("milliseconds") ? Integer.valueOf(filters.get("milliseconds")) : null;
		Integer bytes = filters.containsKey("bytes") ? Integer.valueOf(filters.get("bytes")) : null;
		Float unitPrice = filters.containsKey("unitPrice") ? Float.valueOf(filters.get("unitPrice")) : null;
		String genre = filters.get("genre");
		String mediaType = filters.get("mediaType");
		String artistName = filters.get("artistName");
		String title = filters.get("title");

		Page<Track> trackPage = trackRepository.findByFilters(
				name,
				composer,
				milliseconds,
				bytes,
				unitPrice,
				genre,
				mediaType,
				artistName,
				title,
				pageable
		);

		return trackPage.stream()
				.map(mapperUtil::toTrackDTO)
				.collect(Collectors.toList());
	}
}

