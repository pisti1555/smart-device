package com.smart_device.app_device.features.auth.login;

import com.smart_device.app_device._device.authentication.Authentication;
import com.smart_device.app_device._device.authentication.Credentials;
import com.smart_device.app_device.features._common.RemoteFeature;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class LoginFeature implements RemoteFeature<LoginHandler> {
    private final LoginHandler loginHandler;
    private final Authentication authentication;

    public LoginFeature(LoginHandler loginHandler, Authentication authentication) {
        this.loginHandler = loginHandler;
        this.authentication = authentication;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nYou are unauthenticated. Please login!");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        LoginRequest loginRequest = new LoginRequest(username, password);
        var result = loginHandler.handle(loginRequest);

        if (result.isSuccess()) {
            authentication.setUser(result.getData());

            authentication.setCredentials(new Credentials(username, password));

            System.out.println("Login successful!");
        } else {
            result.printErrorMessage();
        }
    }
}
