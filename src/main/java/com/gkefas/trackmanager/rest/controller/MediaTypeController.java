package com.gkefas.trackmanager.rest.controller;

import com.gkefas.trackmanager.entity.MediaType;
import com.gkefas.trackmanager.service.MediaTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The MediaTypeController is a REST controller that handles HTTP requests related to mediaTypes.
 * It provides endpoints to retrieve all mediaTypes.
 * <p>Endpoints:</p>
 * <ul>
 *   <li>GET /api/mediaType: Retrieves all mediaTypes</li>
 * </ul>
 *
 * @see MediaTypeService
 */
@RestController
@RequestMapping("/api/mediaType")
public class MediaTypeController {

	private final MediaTypeService mediaTypeService;

	public MediaTypeController(MediaTypeService mediaTypeService) {
		this.mediaTypeService = mediaTypeService;
	}

	@GetMapping({"/", ""})
	public List<MediaType> getAllMediaTypes(Pageable pageable) {
		return mediaTypeService.getAllMediaTypes(pageable);
	}
}
