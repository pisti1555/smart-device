package com.smart_device.app_device.features.images.get_images;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RequestHandler;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.models.common.PagedList;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetImagesHandler implements RequestHandler<GetImagesRequest, PagedList<ImageModel>> {
    private final Communicator communicator;

    @Autowired
    public GetImagesHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<PagedList<ImageModel>> handle(GetImagesRequest request) {
        String getImagesUrl = "/images?page=" + request.getPage() + "&size=" + request.getSize();
        return communicator.GET(getImagesUrl, new TypeReference<>() {});
    }
}
