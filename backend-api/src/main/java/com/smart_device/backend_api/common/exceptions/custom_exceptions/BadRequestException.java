package com.smart_device.backend_api.common.exceptions.custom_exceptions;

import com.smart_device.backend_api.common.exceptions.AppException;

public class BadRequestException extends AppException {
    public BadRequestException(String errorMessage) {
        super(400, errorMessage);
    }
}
