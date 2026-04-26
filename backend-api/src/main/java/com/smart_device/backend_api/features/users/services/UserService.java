package com.smart_device.backend_api.features.users.services;

import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.entities.AppUser;

import java.util.List;

public interface UserService {
    UserDto getByUsername(String username);
    AppUser createUserWithRoles(String username, String password, List<String> roleNames);
    boolean isUsernameExists(String username);
}
