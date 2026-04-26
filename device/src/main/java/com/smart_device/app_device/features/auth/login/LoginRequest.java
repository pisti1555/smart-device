package com.smart_device.app_device.features.auth.login;

import com.smart_device.app_device.features._common.Request;
import com.smart_device.app_device.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest implements Request<UserModel> {
    private String username;
    private String password;
}
