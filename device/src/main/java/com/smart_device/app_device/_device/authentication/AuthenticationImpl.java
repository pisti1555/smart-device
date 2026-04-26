package com.smart_device.app_device._device.authentication;

import com.smart_device.app_device.models.UserModel;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationImpl implements Authentication {
    private UserModel user;

    @Override
    public boolean isAuthenticated() {
        return user != null;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public UserModel getUser() {
        return user;
    }

    @Override
    public void setUser(UserModel user) {
        this.user = user;
    }
}
