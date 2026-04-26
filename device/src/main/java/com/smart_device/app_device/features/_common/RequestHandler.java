package com.smart_device.app_device.features._common;

import com.smart_device.app_device.models.common.AppResult;

public interface RequestHandler<TRequest extends Request<ReturnType>, ReturnType> {
    AppResult<ReturnType> handle(TRequest request);
}
