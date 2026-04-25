package com.smart_device.backend_api.common.exceptions.custom_exceptions;

import com.smart_device.backend_api.common.exceptions.AppException;

public class UnexpectedException extends AppException {
    public UnexpectedException(String errorMessage) {
        super(500, errorMessage);
    }
}
