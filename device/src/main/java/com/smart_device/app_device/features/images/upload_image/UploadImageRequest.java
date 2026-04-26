package com.smart_device.app_device.features.images.upload_image;

import com.smart_device.app_device.features._common.RemoteRequest;
import com.smart_device.app_device.models.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UploadImageRequest implements RemoteRequest<ImageModel> {
    private String url;
}
