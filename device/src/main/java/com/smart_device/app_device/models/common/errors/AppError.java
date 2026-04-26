package com.smart_device.app_device.models.common.errors;

import java.util.Collection;
import java.util.Map;

public interface AppError {
    String getTitle();
    int getStatus();
    String getMessage();
    Map<String, Collection<String>> getErrors();
    boolean isValidationError();
}
