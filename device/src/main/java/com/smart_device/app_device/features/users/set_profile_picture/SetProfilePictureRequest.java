package com.smart_device.app_device.features.users.set_profile_picture;

import com.smart_device.app_device.features._common.RemoteRequest;
import com.smart_device.app_device.models.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SetProfilePictureRequest implements RemoteRequest<ImageModel> {
    private String profilePicture;
}
