package com.smart_device.backend_api.features.apps.services;

import com.smart_device.backend_api.common.app_models.PagedList;
import com.smart_device.backend_api.features.apps.dtos.ApplicationDto;

import java.util.UUID;

public interface ApplicationService {
    ApplicationDto getById(UUID id);
    PagedList<ApplicationDto> getAllByUsername(String username, int page, int size);
    ApplicationDto addToUser(UUID id, String username);
    void deleteFromUser(UUID id, String username);
}
