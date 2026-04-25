package com.smart_device.backend_api.features.images.services;

import com.smart_device.backend_api.app_models.PagedList;
import com.smart_device.backend_api.features.images.dtos.ImageDto;
import com.smart_device.backend_api.features.images.dtos.UploadImageDto;

public interface ImageService {
    ImageDto getImage(String id, String username);
    PagedList<ImageDto> getPagedByUsername(String username, int page, int size);
    ImageDto save(UploadImageDto dto, String username);
    void delete(String id, String username);
}
