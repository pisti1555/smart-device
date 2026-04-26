package com.smart_device.app_device.features.apps.search;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.features._common.RemoteRequestHandler;
import com.smart_device.app_device.models.ApplicationModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.models.common.PagedList;
import com.smart_device.app_device.network.Communicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchAppsHandler implements RemoteRequestHandler<SearchAppsRequest, PagedList<ApplicationModel>> {
    private final Communicator communicator;

    @Autowired
    public SearchAppsHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public AppResult<PagedList<ApplicationModel>> handle(SearchAppsRequest request) {
        String endpoint = "/apps?page=" + request.getPage() + "&size=" + request.getPageSize();
        return communicator.GET(endpoint, new TypeReference<>() {});
    }
}
