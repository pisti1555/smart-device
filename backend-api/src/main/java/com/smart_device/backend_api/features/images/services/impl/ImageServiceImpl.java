package com.smart_device.backend_api.features.images.services.impl;

import com.smart_device.backend_api.app_models.PagedList;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.NotFoundException;
import com.smart_device.backend_api.features.images.dtos.ImageDto;
import com.smart_device.backend_api.features.images.entities.AppImage;
import com.smart_device.backend_api.features.images.repositories.ImageRepository;
import com.smart_device.backend_api.features.images.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ModelMapper mapper;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ModelMapper mapper) {
        this.imageRepository = imageRepository;
        this.mapper = mapper;
    }

    @Override
    public ImageDto getById(String id) {
        AppImage image = imageRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Image not found."));

        return mapper.map(image, ImageDto.class);
    }

    @Override
    public PagedList<ImageDto> getPagedByUsername(String username, int page, int size) {
        Page<AppImage> imagesPage = imageRepository.findAllByOwnerUserUsername(username, PageRequest.of(page, size));
        return PagedList.of(imagesPage, img -> mapper.map(img, ImageDto.class));
    }
}
