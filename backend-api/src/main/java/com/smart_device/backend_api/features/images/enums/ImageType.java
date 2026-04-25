package com.smart_device.backend_api.features.images.enums;

import lombok.Getter;

@Getter
public enum ImageType {
    PROFILE_PICTURE("profile"),
    WALLPAPER("wallpaper");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }
}
