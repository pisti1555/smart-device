package com.smart_device.app_device.features.apps.delete;

import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import com.smart_device.app_device.models.ApplicationModel;
import com.smart_device.app_device.models.common.AppModel;
import com.smart_device.app_device.models.common.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class DeleteAppFeature implements RemoteFeature<DeleteAppHandler> {
    private final DeleteAppHandler deleteAppHandler;

    @Autowired
    public DeleteAppFeature(DeleteAppHandler deleteAppHandler) {
        this.deleteAppHandler = deleteAppHandler;
    }

    @Override
    public void run() {
        printSeparator();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Application ID: ");
        String appId = scanner.nextLine();

        AppResult<ApplicationModel> result = deleteAppHandler.handle(new DeleteAppRequest(appId));

        if (result.isSuccess()) {
            System.out.println("Application successfully deleted!");
        } else {
            result.printErrorMessage();
        }

        ScreenNavigator.navigateBack();
    }
}
