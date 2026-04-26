package com.smart_device.app_device.features.images.get_images;

import com.smart_device.app_device.features._common.Request;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.PagedList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetImagesRequest implements Request<PagedList<ImageModel>> {
    private int page;
    private int size;
}
