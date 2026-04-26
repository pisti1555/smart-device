package com.smart_device.app_device.models.common;

import com.smart_device.app_device.models.common.errors.AppError;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class AppResult<T> {
    private boolean isSuccess;
    private String errorMessage;
    private Map<String, Collection<String>> errors;
    private T data;

    public static <T> AppResult<T> success(T data) {
        AppResult<T> result = new AppResult<>();
        result.isSuccess = true;
        result.data = data;
        return result;
    }

    public static <T> AppResult<T> error(AppError error) {
        AppResult<T> result = new AppResult<>();
        result.isSuccess = false;
        result.errorMessage = error.getMessage();
        result.errors = error.getErrors();
        return result;
    }

    public static <T> AppResult<T> error(String errorMessage) {
        AppResult<T> result = new AppResult<>();
        result.isSuccess = false;
        result.errorMessage = errorMessage;
        return result;
    }

    public void printErrorMessage() {
        if (errorMessage == null) return;
        System.out.println(errorMessage + "\n");
    }

    public void printFieldErrors() {
        if (errors == null) return;

        System.out.println("Errors:");
        errors.forEach((field, errorsOnField) -> {
            System.out.println(field + ":");
            errorsOnField.forEach(System.out::println);
        });
        System.out.println("\n");
    }
}
