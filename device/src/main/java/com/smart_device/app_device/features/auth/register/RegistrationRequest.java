package com.smart_device.app_device.features.auth.register;

import com.smart_device.app_device.features._common.RemoteRequest;
import com.smart_device.app_device.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationRequest implements RemoteRequest<UserModel> {
    private String username;
    private String password;
    private String repeatPassword;
    private String role;
}
