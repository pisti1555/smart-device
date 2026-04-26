package com.smart_device.backend_api.features.apps.dtos;

import com.smart_device.backend_api.common.bases.AppDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ApplicationDto extends AppDto {
    private String name;
    private String iconUrl;
    private String category;
    private boolean adultOnly;
}
