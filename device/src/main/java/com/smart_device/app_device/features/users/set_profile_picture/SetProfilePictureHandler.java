package com.smart_device.app_device.features.users.set_profile_picture;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetProfilePictureHandler implements RemoteRequestHandler<SetProfilePictureRequest, ImageModel> {
    private final Communicator communicator;

    @Autowired
    public SetProfilePictureHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<ImageModel> handle(SetProfilePictureRequest request) {
        String endpoint = "/users";
        return communicator.PUT(endpoint, request, new TypeReference<>() {});
    }
}
