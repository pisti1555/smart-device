package com.smart_device.app_device.features.images.upload_image;

import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UploadImageFeature implements RemoteFeature<UploadImageHandler> {
    private final UploadImageHandler uploadImageHandler;

    @Autowired
    public UploadImageFeature(UploadImageHandler uploadImageHandler) {
        this.uploadImageHandler = uploadImageHandler;
    }

    @Override
    public void run() {
        printSeparator();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Image URL: ");
        String imageUrl = scanner.nextLine();

        AppResult<ImageModel> result = uploadImageHandler.handle(new UploadImageRequest(imageUrl));

        if (result.isSuccess()) {
            System.out.println("Image successfully uploaded!");
        } else {
            result.printErrorMessage();
        }

        ScreenNavigator.navigateBack();
    }
}
