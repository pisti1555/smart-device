package com.smart_device.app_device.features.users.get_profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.UserModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProfileHandler implements RemoteRequestHandler<GetProfileRequest, UserModel> {
    private final Communicator communicator;

    @Autowired
    public GetProfileHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<UserModel> handle(GetProfileRequest request) {
        String getProfileUrl = "/users";
        return communicator.GET(getProfileUrl, new TypeReference<>() {});
    }
}
