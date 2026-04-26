package com.smart_device.backend_api.features.images.controllers;

import com.smart_device.backend_api.common.app_models.PagedList;
import com.smart_device.backend_api.features.images.dtos.ImageDto;
import com.smart_device.backend_api.features.images.dtos.UploadImageDto;
import com.smart_device.backend_api.features.images.services.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ImageDto getImage(@PathVariable UUID id, Authentication authentication) {
        return imageService.getImage(id, authentication.getName());
    }

    @GetMapping
    public PagedList<ImageDto> getImagesOfUser(Authentication authentication, Pageable pageable) {
        return imageService.getPagedByUsername(authentication.getName(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @PostMapping
    public ResponseEntity<ImageDto> uploadImage(@RequestBody @Valid UploadImageDto dto, Authentication authentication) {
        ImageDto createdImage = imageService.save(dto, authentication.getName());
        return ResponseEntity
                .created(URI.create("/api/images/" + createdImage.getId()))
                .body(createdImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable UUID id, Authentication authentication) {
        imageService.delete(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
