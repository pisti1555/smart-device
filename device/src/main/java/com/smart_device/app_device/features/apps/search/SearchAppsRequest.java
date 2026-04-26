package com.smart_device.app_device.features.apps.search;

import com.smart_device.app_device.features._common.RemoteRequest;
import com.smart_device.app_device.models.ApplicationModel;
import com.smart_device.app_device.models.common.PagedList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchAppsRequest implements RemoteRequest<PagedList<ApplicationModel>> {
    private int page;
    private int pageSize;
}
