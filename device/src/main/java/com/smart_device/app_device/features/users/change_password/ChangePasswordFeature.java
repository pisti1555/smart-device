package com.smart_device.app_device.features.users.change_password;

import com.smart_device.app_device._device.authentication.Authentication;
import com.smart_device.app_device._device.authentication.Credentials;
import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import com.smart_device.app_device.models.UserModel;
import com.smart_device.app_device.models.common.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ChangePasswordFeature implements RemoteFeature<ChangePasswordHandler> {
    private final Authentication  authentication;
    private final ChangePasswordHandler changePasswordHandler;

    @Autowired
    public ChangePasswordFeature(Authentication authentication, ChangePasswordHandler changePasswordHandler) {
        this.authentication = authentication;
        this.changePasswordHandler = changePasswordHandler;
    }

    @Override
    public void run() {
        printSeparator();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        AppResult<UserModel> result = changePasswordHandler.handle(new ChangePasswordRequest(newPassword));

        if (result.isSuccess()) {
            authentication.setCredentials(new Credentials(authentication.getName(),  newPassword));
            System.out.println("Password changed.");
        } else {
            result.printErrorMessage();
            ScreenNavigator.navigateBack();
        }

        selectOption();
    }

    private void selectOption() {
        List<InputOption> options = List.of(
                InputOption.create("back", "Go back")
        );

        String option = ConsoleInput.optionsInput(options);

        if (option.equals("back")) {
            ScreenNavigator.navigateBack();
        }
    }
}
