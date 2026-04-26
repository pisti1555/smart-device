package com.smart_device.backend_api.common.app_services.error_response_provider.impl;

import com.smart_device.backend_api.common.app_models.error_responses.ErrorResponseModel;
import com.smart_device.backend_api.common.app_models.error_responses.ProductionErrorResponseModel;
import com.smart_device.backend_api.common.app_services.error_response_provider.ErrorResponseProvider;
import com.smart_device.backend_api.common.exceptions.AppException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class ProductionErrorResponseProvider implements ErrorResponseProvider {
    @Override
    public ErrorResponseModel provide(AppException exception, String customMessage) {
        return new ProductionErrorResponseModel(
                exception.getStatusCode(),
                exception.getTitle(),
                customMessage
        );
    }
}
