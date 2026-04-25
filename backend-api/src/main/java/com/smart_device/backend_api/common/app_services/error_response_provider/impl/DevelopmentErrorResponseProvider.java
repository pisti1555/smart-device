package com.smart_device.backend_api.common.app_services.error_response_provider.impl;

import com.smart_device.backend_api.common.app_models.error_responses.DevelopmentErrorResponseModel;
import com.smart_device.backend_api.common.app_models.error_responses.ErrorResponseModel;
import com.smart_device.backend_api.common.app_services.error_response_provider.ErrorResponseProvider;
import com.smart_device.backend_api.common.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
@Profile("dev")
@Slf4j
public class DevelopmentErrorResponseProvider implements ErrorResponseProvider {
    @Override
    public ErrorResponseModel provide(AppException exception, String customMessage) {
        StringWriter stackTraceStringWriter = new StringWriter();

        PrintWriter printWriter = new PrintWriter(stackTraceStringWriter);
        exception.printStackTrace(printWriter);

        log.error(stackTraceStringWriter.toString());

        return new DevelopmentErrorResponseModel(
                exception.getStatusCode(),
                exception.getTitle(),
                exception.getMessage(),
                exception.getTimestamp(),
                stackTraceStringWriter.toString()
        );
    }
}
