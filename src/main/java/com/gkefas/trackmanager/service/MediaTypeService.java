package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.entity.MediaType;
import com.gkefas.trackmanager.repository.MediaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing tracks. It provides methods to retrieve tracks
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllMediaTypes()} - Retrieves a list of all tracks.</li>
 * </ul>
 * <p>This service interacts with the {@link MediaTypeRepository} to fetch mediaType data </p>
 *
 * @see MediaTypeRepository
 */
@Service
public class MediaTypeService {
	private final MediaTypeRepository mediaTypeRepository;

	@Autowired
	public MediaTypeService(MediaTypeRepository mediaTypeRepository) {
		this.mediaTypeRepository = mediaTypeRepository;
	}

	public List<MediaType> getAllMediaTypes() {
		return mediaTypeRepository.findAll();
	}
}
