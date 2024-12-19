package com.gkefas.trackmanager.service;

import com.gkefas.trackmanager.entity.MediaType;
import com.gkefas.trackmanager.repository.MediaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing mediaTypes. It provides methods to retrieve mediaTypes
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllMediaTypes(Pageable pageable)} - Retrieves a list of all MediaTypes.</li>
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

	public List<MediaType> getAllMediaTypes(Pageable pageable) {
		return mediaTypeRepository.findAll(pageable).getContent();
	}
}
