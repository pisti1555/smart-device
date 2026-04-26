package com.smart_device.app_device.features._common;

import com.smart_device.app_device.models.common.AppResult;

public interface RemoteRequestHandler<TRequest extends RemoteRequest<ReturnType>, ReturnType> {
    AppResult<ReturnType> handle(TRequest request);
}
