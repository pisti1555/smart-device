package com.smart_device.app_device.features.images.upload_image;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadImageHandler implements RemoteRequestHandler<UploadImageRequest, ImageModel> {
    private final Communicator communicator;

    @Autowired
    public UploadImageHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<ImageModel> handle(UploadImageRequest request) {
        String uploadImageUrl = "/images";
        return communicator.POST(uploadImageUrl, request, new TypeReference<>() {});
    }
}
