package com.smart_device.backend_api.features.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "Username must not be blank.")
        String username,

        @NotBlank(message = "Password must not be blank.")
        String password
) {}