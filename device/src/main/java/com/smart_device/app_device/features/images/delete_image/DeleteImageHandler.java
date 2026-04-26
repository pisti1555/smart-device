package com.smart_device.app_device.features.images.delete_image;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteImageHandler implements RemoteRequestHandler<DeleteImageRequest, ImageModel> {
    private final Communicator communicator;

    @Autowired
    public DeleteImageHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<ImageModel> handle(DeleteImageRequest request) {
        String endpoint = "/images/" + request.getId();
        return communicator.DELETE(endpoint, new TypeReference<>() {});
    }
}
