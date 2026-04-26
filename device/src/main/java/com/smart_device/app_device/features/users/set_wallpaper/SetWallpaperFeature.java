package com.smart_device.app_device.features.users.set_wallpaper;

import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SetWallpaperFeature implements RemoteFeature<SetWallpaperHandler> {
    private final SetWallpaperHandler setWallpaperHandler;

    @Autowired
    public SetWallpaperFeature(SetWallpaperHandler setWallpaperHandler) {
        this.setWallpaperHandler = setWallpaperHandler;
    }

    @Override
    public void run() {
        printSeparator();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Image ID: ");
        String imageId = scanner.nextLine();

        AppResult<ImageModel> result = setWallpaperHandler.handle(new SetWallpaperRequest(imageId));

        if (result.isSuccess()) {
            System.out.println("Wallpaper has been updated successfully.");
        } else {
            result.printErrorMessage();
        }

        ScreenNavigator.navigateBack();
    }
}
