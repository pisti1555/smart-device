package com.smart_device.app_device._device.apps.storage;

import com.smart_device.app_device._device.apps.local_app.LocalApp;

import java.util.List;

public interface ApplicationStorage {
    List<LocalApp> getApplications();
    void addApplication(LocalApp app);
}
