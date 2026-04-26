package com.smart_device.app_device.features.users.change_password;

import com.smart_device.app_device.features._common.Request;
import com.smart_device.app_device.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangePasswordRequest implements Request<UserModel> {
    String password;
}
