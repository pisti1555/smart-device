package com.smart_device.backend_api.features.users.controllers;

import com.smart_device.backend_api.features.users.dtos.EditUserDto;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto getAuthenticationUser(Authentication authentication) {
        return userService.getByUsername(authentication.getName());
    }

    @PutMapping
    @Secured("ROLE_USER")
    public UserDto editUser(@RequestBody EditUserDto dto, Authentication authentication) {
        return userService.editUser(dto, authentication.getName());
    }
}
