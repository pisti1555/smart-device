package com.smart_device.backend_api.features.auth.controllers;

import com.smart_device.backend_api.features.auth.dtos.LoginDto;
import com.smart_device.backend_api.features.auth.dtos.RegistrationDto;
import com.smart_device.backend_api.features.auth.services.AuthService;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid LoginDto dto) {
        return authService.login(dto);
    }

    @PostMapping("/register")
    @Secured("ROLE_ADMIN")
    public UserDto register(@RequestBody @Valid RegistrationDto dto) {
        return authService.register(dto);
    }
}
