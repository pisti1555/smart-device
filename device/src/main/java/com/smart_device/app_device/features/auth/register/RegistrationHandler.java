package com.smart_device.app_device.features.auth.register;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.UserModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationHandler implements RemoteRequestHandler<RegistrationRequest, UserModel> {
    private final Communicator communicator;

    @Autowired
    public RegistrationHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<UserModel> handle(RegistrationRequest request) {
        String registrationUrl = "/auth/register";
        return communicator.POST(registrationUrl, request, new TypeReference<>() {});
    }
}
