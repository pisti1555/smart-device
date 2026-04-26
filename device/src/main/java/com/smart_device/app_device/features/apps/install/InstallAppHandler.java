package com.smart_device.app_device.features.apps.install;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.ApplicationModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstallAppHandler implements RemoteRequestHandler<InstallAppRequest, ApplicationModel> {
    private final Communicator communicator;

    @Autowired
    public InstallAppHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<ApplicationModel> handle(InstallAppRequest request) {
        String endpoint = "/apps";
        return communicator.POST(endpoint, request, new TypeReference<>() {});
    }
}
