package com.smart_device.backend_api.features.images.controllers;

import com.smart_device.backend_api.app_models.PagedList;
import com.smart_device.backend_api.features.images.dtos.ImageDto;
import com.smart_device.backend_api.features.images.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ImageDto getImage(@PathVariable String id) {
        return imageService.getById(id);
    }

    @GetMapping
    public PagedList<ImageDto> getImagesOfUser(Authentication authentication, Pageable pageable) {
        return imageService.getPagedByUsername(authentication.getName(), pageable.getPageNumber(), pageable.getPageSize());
    }
}
