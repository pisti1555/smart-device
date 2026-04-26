package com.smart_device.app_device.features.auth.login;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.UserModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginHandler implements RemoteRequestHandler<LoginRequest, UserModel> {
    private final Communicator communicator;

    @Autowired
    public LoginHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<UserModel> handle(LoginRequest request) {
        String loginUrl = "/auth/login";
        return communicator.POST(loginUrl, request, new TypeReference<>() {});
    }
}
