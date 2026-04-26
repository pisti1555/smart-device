package com.smart_device.app_device.features.apps.install;

import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import com.smart_device.app_device.models.ApplicationModel;
import com.smart_device.app_device.models.common.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InstallAppFeature implements RemoteFeature<InstallAppHandler> {
    private final InstallAppHandler installAppHandler;

    @Autowired
    public InstallAppFeature(InstallAppHandler installAppHandler) {
        this.installAppHandler = installAppHandler;
    }
    @Override
    public void run() {
        printSeparator();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Application ID you want to install: ");
        String appId = scanner.nextLine();

        AppResult<ApplicationModel> result = installAppHandler.handle(new InstallAppRequest(appId));

        if (result.isSuccess()) {
            System.out.println("Application successfully installed!");
        } else {
            result.printErrorMessage();
        }

        ScreenNavigator.navigateBack();
    }
}
