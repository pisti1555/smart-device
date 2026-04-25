package com.smart_device.backend_api.common.exceptions.custom_exceptions;

import com.smart_device.backend_api.common.exceptions.AppException;

public class UnauthorizedException extends AppException {
    public UnauthorizedException(String errorMessage) {
        super(401, errorMessage);
    }
}
