package com.smart_device.backend_api.features.images.enums;

import com.smart_device.backend_api.common.exceptions.custom_exceptions.BadRequestException;
import lombok.Getter;

@Getter
public enum ImageType {
    PROFILE_PICTURE("profile"),
    WALLPAPER("wallpaper");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType createFromType(String type) {
        for (ImageType imageType : ImageType.values()) {
            if (imageType.type.equalsIgnoreCase(type)) {
                return imageType;
            }
        }

        throw new BadRequestException("Invalid image type.");
    }
}
