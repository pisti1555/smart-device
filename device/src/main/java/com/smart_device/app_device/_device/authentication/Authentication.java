package com.smart_device.app_device._device.authentication;

import com.smart_device.app_device.models.UserModel;

public interface Authentication {
    boolean isAuthenticated();
    String getName();

    Credentials getCredentials();
    void setCredentials(Credentials credentials);

    UserModel getUser();
    void setUser(UserModel user);
}
