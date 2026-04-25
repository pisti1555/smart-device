package com.smart_device.backend_api.features.auth.services;

import com.smart_device.backend_api.features.users.dtos.UserDto;

public interface AuthService {
    UserDto login(String username, String password);
}
