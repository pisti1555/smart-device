package com.smart_device.backend_api.common.exceptions.custom_exceptions;

import com.smart_device.backend_api.common.exceptions.AppException;

public class NotFoundException extends AppException {
    public NotFoundException(String errorMessage) {
        super(404, errorMessage);
    }
}
