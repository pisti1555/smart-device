package com.smart_device.backend_api.middlewares;

import com.smart_device.backend_api.common.app_models.error_responses.ErrorResponseModel;
import com.smart_device.backend_api.common.app_models.error_responses.ValidationErrorResponseModel;
import com.smart_device.backend_api.common.app_services.error_response_provider.ErrorResponseProvider;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.*;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@RestControllerAdvice
public class ErrorHandlerMiddleware {
    private final ErrorResponseProvider errorResponseProvider;

    @Autowired
    public ErrorHandlerMiddleware(ErrorResponseProvider errorResponseProvider) {
        this.errorResponseProvider = errorResponseProvider;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseModel handleNotFound(NotFoundException ex) {
        return errorResponseProvider.provide(ex, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseModel handleBadRequest(BadRequestException ex) {
        return errorResponseProvider.provide(ex, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseModel handleUnauthorized(UnauthorizedException ex) {
        return errorResponseProvider.provide(ex, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseModel handleForbidden(ForbiddenException ex) {
        return errorResponseProvider.provide(ex, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseModel handleTypeMismatch(TypeMismatchException ex) {
        var badRequestEx = new BadRequestException(ex.getMessage());
        return errorResponseProvider.provide(badRequestEx, "Data type mismatch.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponseModel handleValidation(BindException ex) {
        var errors = new HashMap<String, Collection<String>>();

        ex.getBindingResult().getFieldErrors().forEach(e -> {
            String fieldName = e.getField();
            errors.putIfAbsent(fieldName, new ArrayList<>());

            if (e.getCode().equals("typeMismatch")) {
                errors.get(fieldName).add("Invalid data type.");
            } else {
                errors.get(fieldName).add(e.getDefaultMessage());
            }
        });

        return new ValidationErrorResponseModel(HttpStatus.BAD_REQUEST.value(), "Validation failed.", errors);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseModel handleNoResourceFoundException(NoResourceFoundException ex) {
        var notFoundEx = new NotFoundException(ex.getMessage());
        return errorResponseProvider.provide(notFoundEx, "No resource found.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseModel handleInternalServerError(Throwable ex) {
        var unexpectedException = new UnexpectedException(ex.getMessage());
        return errorResponseProvider.provide(unexpectedException, "An error occurred on the server side :(");
    }
}
