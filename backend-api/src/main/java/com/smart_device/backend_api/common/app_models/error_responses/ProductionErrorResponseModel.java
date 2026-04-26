package com.smart_device.backend_api.common.app_models.error_responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ProductionErrorResponseModel implements ErrorResponseModel {
    private int status;
    private String title;
    private String message;
}
