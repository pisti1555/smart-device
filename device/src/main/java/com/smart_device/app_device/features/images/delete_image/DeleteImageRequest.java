package com.smart_device.app_device.features.images.delete_image;

import com.smart_device.app_device.features._common.RemoteRequest;
import com.smart_device.app_device.models.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteImageRequest implements RemoteRequest<ImageModel> {
    private String id;
}
