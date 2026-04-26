package com.smart_device.backend_api.features.images.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UploadImageDto {
    @NotBlank(message = "Image URL must not be blank.")
    private String url;
}
