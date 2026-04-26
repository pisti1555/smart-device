package com.smart_device.app_device._device.authentication;

import lombok.AllArgsConstructor;

import java.util.Base64;

@AllArgsConstructor
public class Credentials {
    private String username;
    private String password;

    public String getBasicAuthToken() {
        return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
