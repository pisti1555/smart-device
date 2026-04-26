package com.smart_device.app_device.features.images.delete_image;

import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class DeleteImageFeature implements RemoteFeature<DeleteImageHandler> {
    private final DeleteImageHandler deleteImageHandler;

    @Autowired
    public DeleteImageFeature(DeleteImageHandler deleteImageHandler) {
        this.deleteImageHandler = deleteImageHandler;
    }

    @Override
    public void run() {
        printSeparator();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Image ID: ");
        String imageId = scanner.nextLine();

        AppResult<ImageModel> result = deleteImageHandler.handle(new DeleteImageRequest(imageId));

        if (result.isSuccess()) {
            System.out.println("Image successfully deleted!");
        } else {
            result.printErrorMessage();
        }

        ScreenNavigator.navigateBack();
    }
}
