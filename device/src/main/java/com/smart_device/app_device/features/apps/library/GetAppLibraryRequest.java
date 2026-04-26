package com.smart_device.app_device.features.apps.library;

import com.smart_device.app_device.features._common.RemoteRequest;
import com.smart_device.app_device.models.ApplicationModel;
import com.smart_device.app_device.models.common.PagedList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAppLibraryRequest implements RemoteRequest<PagedList<ApplicationModel>> {
    private int page;
    private int pageSize;
}
