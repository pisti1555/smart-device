package com.smart_device.backend_api.features.apps.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ApplicationAddToUserDto {
    @NotBlank(message = "Application ID must not be blank.")
    private String id;
}
