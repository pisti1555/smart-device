package com.smart_device.backend_api.app_services.error_response_provider.services;

import com.smart_device.backend_api.app_services.error_response_provider.models.ErrorResponseModel;
import com.smart_device.backend_api.common.exceptions.AppException;

public interface ErrorResponseProvider {
    ErrorResponseModel provide(AppException exception, String customMessage);
}
