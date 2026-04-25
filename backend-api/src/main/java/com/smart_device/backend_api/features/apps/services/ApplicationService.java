package com.smart_device.backend_api.features.apps.services;

import com.smart_device.backend_api.app_models.PagedList;
import com.smart_device.backend_api.features.apps.dtos.ApplicationDto;

public interface ApplicationService {
    ApplicationDto getById(String id);
    PagedList<ApplicationDto> getAllByUsername(String username, int page, int size);
    ApplicationDto addToUser(String id, String username);
    void deleteFromUser(String id, String username);
}
