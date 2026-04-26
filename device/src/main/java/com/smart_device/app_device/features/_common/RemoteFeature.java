package com.smart_device.app_device.features._common;

import com.smart_device.app_device._device.screens.Screen;

public interface RemoteFeature<Handler extends RemoteRequestHandler<? extends RemoteRequest<?>, ?>> extends Screen {
}
