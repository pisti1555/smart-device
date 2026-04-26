package com.smart_device.app_device.features.users.set_profile_picture;

import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SetProfilePictureFeature implements RemoteFeature<SetProfilePictureHandler> {
    private final SetProfilePictureHandler setProfilePictureHandler;

    @Autowired
    public SetProfilePictureFeature(SetProfilePictureHandler setProfilePictureHandler) {
        this.setProfilePictureHandler = setProfilePictureHandler;
    }

    @Override
    public void run() {
        printSeparator();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Image ID: ");
        String imageId = scanner.nextLine();

        AppResult<ImageModel> result = setProfilePictureHandler.handle(new SetProfilePictureRequest(imageId));

        if (result.isSuccess()) {
            System.out.println("Profile picture has been updated successfully.");
        } else {
            result.printErrorMessage();
        }

        ScreenNavigator.navigateBack();
    }
}
