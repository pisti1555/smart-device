package com.smart_device.app_device.features.images;

import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.LocalFeature;
import com.smart_device.app_device.features.images.delete_image.DeleteImageFeature;
import com.smart_device.app_device.features.images.get_images.GetImagesFeature;
import com.smart_device.app_device.features.images.upload_image.UploadImageFeature;
import com.smart_device.app_device.features.users.set_profile_picture.SetProfilePictureFeature;
import com.smart_device.app_device.features.users.set_wallpaper.SetWallpaperFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageFeature implements LocalFeature {
    private final GetImagesFeature getImagesFeature;
    private final UploadImageFeature uploadImageFeature;
    private final DeleteImageFeature deleteImageFeature;
    private final SetProfilePictureFeature setProfilePictureFeature;
    private final SetWallpaperFeature setWallpaperFeature;

    @Autowired
    public ImageFeature(GetImagesFeature getImagesFeature, UploadImageFeature uploadImageFeature, DeleteImageFeature deleteImageFeature, SetProfilePictureFeature setProfilePictureFeature, SetWallpaperFeature setWallpaperFeature) {
        this.getImagesFeature = getImagesFeature;
        this.uploadImageFeature = uploadImageFeature;
        this.deleteImageFeature = deleteImageFeature;
        this.setProfilePictureFeature = setProfilePictureFeature;
        this.setWallpaperFeature = setWallpaperFeature;
    }

    @Override
    public void run() {
        printSeparator();
        selectOption();
    }

    private void selectOption() {
        List<InputOption> options = List.of(
                InputOption.create("back", "Go back"),
                InputOption.create("list", "List all images"),
                InputOption.create("chpp", "Change profile picture"),
                InputOption.create("chw", "Change wallpaper"),
                InputOption.create("upload", "Upload a new image"),
                InputOption.create("delete", "Delete and existing image by its ID")
        );

        String option = ConsoleInput.optionsInput(options);

        switch (option) {
            case "back" -> ScreenNavigator.navigateBack();
            case "list" -> ScreenNavigator.navigateForward(getImagesFeature);
            case "chpp" -> ScreenNavigator.navigateForward(setProfilePictureFeature);
            case "chw" -> ScreenNavigator.navigateForward(setWallpaperFeature);
            case "upload" -> ScreenNavigator.navigateForward(uploadImageFeature);
            case "delete" -> ScreenNavigator.navigateForward(deleteImageFeature);
        }
    }
}
