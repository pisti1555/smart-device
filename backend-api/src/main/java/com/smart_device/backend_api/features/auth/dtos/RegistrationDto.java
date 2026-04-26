package com.smart_device.backend_api.features.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationDto(
        @NotBlank(message = "Username must not be blank.")
        String username,

        @NotBlank(message = "Password must not be blank.")
        @Size(min = 6, max = 100, message = "Password must be at least 6, and maximum 100 characters long.")
        String password,

        @NotBlank(message = "Password confirmation must not be blank.")
        String repeatPassword,

        @NotBlank(message = "Role must not be blank.")
        String role
) {}