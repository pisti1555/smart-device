package com.smart_device.backend_api.common.exceptions.custom_exceptions;

import com.smart_device.backend_api.common.exceptions.AppException;

public class ForbiddenException extends AppException {
    public ForbiddenException(String errorMessage) {
        super(403, errorMessage);
    }
}
