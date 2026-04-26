package com.smart_device.app_device.features.auth.register;

import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class RegistrationFeature implements RemoteFeature<RegistrationHandler> {
    private final RegistrationHandler registrationHandler;

    public RegistrationFeature(RegistrationHandler registrationHandler) {
        this.registrationHandler = registrationHandler;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        printSeparator();
        System.out.println("Registration form:");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Repeat password: ");
        String repeatPassword = scanner.nextLine();

        System.out.print("Role: ");
        String role = scanner.nextLine();

        RegistrationRequest request = new RegistrationRequest(username, password, repeatPassword, role);
        var result = registrationHandler.handle(request);

        if (result.isSuccess()) {
            System.out.println("User has been registered successfully.");
            System.out.println("It can use its account from now.");
        } else {
            result.printErrorMessage();
            result.printFieldErrors();
        }

        ScreenNavigator.navigateBack();
    }
}
