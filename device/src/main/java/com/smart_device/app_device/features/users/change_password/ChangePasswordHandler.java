package com.smart_device.app_device.features.users.change_password;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RequestHandler;
import com.smart_device.app_device.models.UserModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordHandler implements RequestHandler<ChangePasswordRequest, UserModel> {
    private final Communicator communicator;

    @Autowired
    public ChangePasswordHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<UserModel> handle(ChangePasswordRequest request) {
        String editUserUrl = "/users";
        return communicator.PUT(editUserUrl, request, new  TypeReference<>() {});
    }
}
