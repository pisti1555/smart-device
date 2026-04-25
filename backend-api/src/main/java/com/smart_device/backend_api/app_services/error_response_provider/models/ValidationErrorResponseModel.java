package com.smart_device.backend_api.app_services.error_response_provider.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Map;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ValidationErrorResponseModel {
    private int status;
    private String title;
    Map<String, Collection<String>> errors;
}
