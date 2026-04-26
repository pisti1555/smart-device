package com.smart_device.app_device.models.common.errors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Map;

@NoArgsConstructor
@Getter @Setter
public class ErrorModel implements AppError {
    private int status;
    private String title;
    private String message;

    @Override
    public Map<String, Collection<String>> getErrors() {
        return null;
    }

    @Override
    public boolean isValidationError() {
        return false;
    }

    @Override
    public String toString() {
        return "Error: \n" +
                "Status: " + status + '\n' +
                "Title: " + title + '\n' +
                "Message: " + message + '\n';
    }
}
