package com.smart_device.app_device._device;

import com.smart_device.app_device._device.authentication.Authentication;
import com.smart_device.app_device.features.auth.login.LoginFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsoleDeviceLauncher implements DeviceLauncher {
    private final Authentication authentication;
    private final LoginFeature loginFeature;

    @Autowired
    public ConsoleDeviceLauncher(Authentication authentication, LoginFeature loginFeature) {
        this.authentication = authentication;
        this.loginFeature = loginFeature;
    }

    @Override
    public void launch() {
        initialize();
    }

    private void initialize() {
        while(!authentication.isAuthenticated()) {
            loginFeature.run();
        }
    }
}
