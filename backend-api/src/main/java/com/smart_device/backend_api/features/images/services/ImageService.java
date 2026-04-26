package com.smart_device.backend_api.features.images.services;

import com.smart_device.backend_api.common.app_models.PagedList;
import com.smart_device.backend_api.features.images.dtos.ImageDto;
import com.smart_device.backend_api.features.images.dtos.UploadImageDto;

import java.util.UUID;

public interface ImageService {
    ImageDto getImage(UUID id, String username);
    PagedList<ImageDto> getPagedByUsername(String username, int page, int size);
    ImageDto save(UploadImageDto dto, String username);
    void delete(UUID id, String username);
}
