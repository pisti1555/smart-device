package com.smart_device.app_device._device.authentication;

import com.smart_device.app_device.models.UserModel;
import org.springframework.stereotype.Component;

@Component
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
    public void setCredentials(String username, String password) {
        this.credentials = new Credentials(username, password);
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
