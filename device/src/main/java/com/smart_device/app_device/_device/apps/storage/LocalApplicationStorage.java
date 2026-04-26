package com.smart_device.app_device._device.apps.storage;

import com.smart_device.app_device._device.apps.local_app.LocalApp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Scope("singleton")
public class LocalApplicationStorage implements ApplicationStorage {
    private final List<LocalApp> applications = new LinkedList<>();

    @Override
    public List<LocalApp> getApplications() {
        return applications;
    }

    @Override
    public void addApplication(LocalApp app) {
        applications.add(app);
    }
}
