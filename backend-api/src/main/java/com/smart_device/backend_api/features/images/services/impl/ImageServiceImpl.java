package com.smart_device.backend_api.features.images.services.impl;

import com.smart_device.backend_api.common.app_models.PagedList;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.*;
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
    public ImageDto getImage(UUID id, String username) {
        AppImage foundImage = imageRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Image was not found."));

        throwForbiddenIfUserIsNotOwner(foundImage, username);

        return mapper.map(foundImage, ImageDto.class);
    }

    @Override
    public PagedList<ImageDto> getPagedByUsername(String username, int page, int size) {
        Page<AppImage> imagesPage = imageRepository.findAllByOwnerUserUsername(username, PageRequest.of(page, size));
        return PagedList.of(imagesPage, img -> mapper.map(img, ImageDto.class));
    }

    @Override
    public ImageDto save(UploadImageDto dto, String username) {
        AppUser user = getUserByAuthenticationOrThrow(username);
        AppImage image = new AppImage();

        image.setOwnerUser(user);
        image.setName(dto.getName());
        image.setUrl(dto.getUrl());
        image.setType(ImageType.createFromType(dto.getType()));

        AppImage savedImage = imageRepository.save(image);

        return mapper.map(savedImage, ImageDto.class);
    }

    @Override
    public void delete(UUID id, String username) {
        AppImage foundImage = imageRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Image was not found."));

        throwForbiddenIfUserIsNotOwner(foundImage, username);

        imageRepository.delete(foundImage);
    }

    private AppUser getUserByAuthenticationOrThrow(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UnexpectedException("Something went wrong on server side."));
    }

    private void throwForbiddenIfUserIsNotOwner(AppImage image, String username) {
        if (!image.getOwnerUser().getUsername().equals(username)) {
            throw new ForbiddenException("You are not allowed to make this request.");
        }
    }
}
