package com.smart_device.app_device.features.apps.delete;

import com.smart_device.app_device.features._common.RemoteRequest;
import com.smart_device.app_device.models.ApplicationModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteAppRequest implements RemoteRequest<ApplicationModel> {
    private String id;
}
