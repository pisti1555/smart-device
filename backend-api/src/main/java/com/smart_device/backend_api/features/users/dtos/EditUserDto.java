package com.smart_device.backend_api.features.users.dtos;

public record EditUserDto(
        String password,
        String profilePicture,
        String wallpaper
) {}
