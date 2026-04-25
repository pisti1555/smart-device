package com.smart_device.backend_api.features.images.services.impl;

import com.smart_device.backend_api.app_models.PagedList;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.BadRequestException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.ForbiddenException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.NotFoundException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnexpectedException;
import com.smart_device.backend_api.features.images.dtos.ImageDto;
import com.smart_device.backend_api.features.images.dtos.UploadImageDto;
import com.smart_device.backend_api.features.images.entities.AppImage;
import com.smart_device.backend_api.features.images.enums.ImageType;
import com.smart_device.backend_api.features.images.repositories.ImageRepository;
import com.smart_device.backend_api.features.images.services.ImageService;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper mapper;

    @Autowired
    public ImageServiceImpl(UserRepository userRepository, ImageRepository imageRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.mapper = mapper;
    }

    @Override
    public ImageDto getImage(String id, String username) {
        AppImage foundImage = imageRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Image was not found."));

        if (!foundImage.getOwnerUser().getUsername().equals(username)) {
            throw new ForbiddenException("You are not allowed to view this image.");
        }

        return mapper.map(foundImage, ImageDto.class);
    }

    @Override
    public PagedList<ImageDto> getPagedByUsername(String username, int page, int size) {
        Page<AppImage> imagesPage = imageRepository.findAllByOwnerUserUsername(username, PageRequest.of(page, size));
        return PagedList.of(imagesPage, img -> mapper.map(img, ImageDto.class));
    }

    @Override
    public ImageDto save(UploadImageDto dto, String username) {
        AppUser user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->new UnexpectedException("Something went wrong on server side."));
        AppImage image = new AppImage();

        image.setOwnerUser(user);
        image.setName(dto.getName());
        image.setUrl(dto.getUrl());
        image.setType(ImageType.createFromType(dto.getType()));

        AppImage savedImage = imageRepository.save(image);

        return mapper.map(savedImage, ImageDto.class);
    }

    @Override
    public void delete(String id, String username) {
        AppImage foundImage = imageRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Image was not found."));

        if (!foundImage.getOwnerUser().getUsername().equals(username)) {
            throw new ForbiddenException("You are not allowed to delete this image.");
        }

        imageRepository.delete(foundImage);
    }
}
