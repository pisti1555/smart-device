package com.smart_device.app_device._device.authentication;

import com.smart_device.app_device.models.UserModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class AuthenticationImpl implements Authentication {
    private UserModel user;
    private Credentials credentials;

    @Override
    public boolean isAuthenticated() {
        return user != null;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
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
