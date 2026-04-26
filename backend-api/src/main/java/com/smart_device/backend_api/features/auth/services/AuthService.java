package com.smart_device.backend_api.features.auth.services;

import com.smart_device.backend_api.features.auth.dtos.LoginDto;
import com.smart_device.backend_api.features.auth.dtos.RegistrationDto;
import com.smart_device.backend_api.features.users.dtos.UserDto;

public interface AuthService {
    UserDto login(LoginDto dto);
    UserDto register(RegistrationDto dto);
}
