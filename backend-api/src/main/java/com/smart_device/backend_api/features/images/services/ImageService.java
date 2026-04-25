package com.smart_device.backend_api.features.images.services;

import com.smart_device.backend_api.app_models.PagedList;
import com.smart_device.backend_api.features.images.dtos.ImageDto;

public interface ImageService {
    ImageDto getById(String id);
    PagedList<ImageDto> getPagedByUsername(String username, int page, int size);
}
