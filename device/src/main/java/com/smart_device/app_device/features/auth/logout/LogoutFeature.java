package com.smart_device.app_device.features.auth.logout;

import com.smart_device.app_device._device.authentication.Authentication;
import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.LocalFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogoutFeature implements LocalFeature {
    private final Authentication authentication;

    @Autowired
    public LogoutFeature(Authentication authentication) {
        this.authentication = authentication;
    }
    @Override
    public void run() {
        printSeparator();

        System.out.println("Are you sure you want to logout?");

        String option = ConsoleInput.optionsInput(List.of(
                InputOption.create("yes", "Yes, logout"),
                InputOption.create("no", "No, stay signed in")
        ));

        switch (option) {
            case "yes" -> {
                authentication.setUser(null);
                authentication.setCredentials(null);
            }
            case "no" -> {
                ScreenNavigator.navigateBack();
            }
        }
    }
}
