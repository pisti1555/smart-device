package com.smart_device.backend_api.features.images.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ImageDto {
    private String id;
    private String name;
    private String url;
    private String type;
}
