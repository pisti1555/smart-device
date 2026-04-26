package com.smart_device.app_device.features._common;

public interface Feature<Handler extends RequestHandler<? extends Request<?>, ?>> {
    void run();
}
