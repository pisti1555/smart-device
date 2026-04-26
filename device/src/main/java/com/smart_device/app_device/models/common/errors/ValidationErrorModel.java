package com.smart_device.app_device.models.common.errors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Map;

@NoArgsConstructor
@Getter @Setter
public class ValidationErrorModel implements AppError {
    private int status;
    private String title;
    private Map<String, Collection<String>> errors;

    @Override
    public String getMessage() {
        return "Validation failed.";
    }

    @Override
    public boolean isValidationError() {
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Validation error: \n")
                .append("Status: ").append(status).append('\n')
                .append("Title: ").append(title).append('\n')
                .append("Errors: \n");

        errors.forEach((key, value) -> {
            sb.append(" ").append(key).append(": \n");
            value.forEach(val ->
                    sb.append("  ").append(val).append('\n')
            );
        });

        return sb.toString();
    }
}
