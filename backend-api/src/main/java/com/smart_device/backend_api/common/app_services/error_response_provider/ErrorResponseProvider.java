package com.smart_device.backend_api.common.app_services.error_response_provider;

import com.smart_device.backend_api.common.app_models.error_responses.ErrorResponseModel;
import com.smart_device.backend_api.common.exceptions.AppException;

public interface ErrorResponseProvider {
    ErrorResponseModel provide(AppException exception, String customMessage);
}
