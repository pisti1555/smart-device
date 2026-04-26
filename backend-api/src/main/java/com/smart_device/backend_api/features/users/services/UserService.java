package com.smart_device.backend_api.features.users.services;

import com.smart_device.backend_api.features.users.dtos.UserDto;

public interface UserService {
    UserDto getByUsername(String username);
}
