package com.smart_device.app_device.features.apps.delete;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.ApplicationModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteAppHandler implements RemoteRequestHandler<DeleteAppRequest, ApplicationModel> {
    private final Communicator communicator;

    @Autowired
    public DeleteAppHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<ApplicationModel> handle(DeleteAppRequest request) {
        String endpoint = "/apps/" + request.getId();
        return communicator.DELETE(endpoint, new TypeReference<>() {});
    }
}
