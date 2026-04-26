package com.smart_device.app_device.features.icons;

import com.smart_device.app_device._device.apps.local_app.LocalApp;
import com.smart_device.app_device._device.apps.storage.ApplicationStorage;
import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.LocalFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class IconsFeature implements LocalFeature {
    private final ApplicationStorage storage;

    @Autowired
    public IconsFeature(ApplicationStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        printSeparator();
        System.out.println("Icons:");
        for (LocalApp app : storage.getApplications()) {
            System.out.println(app.toString());
        }

        selectOption();
    }

    private void selectOption() {
        List<InputOption> options = List.of(
                InputOption.create("back", "Go back"),
                InputOption.create("start", "Start an application")
        );

        String option = ConsoleInput.optionsInput(options);

        switch (option) {
            case "back" -> ScreenNavigator.navigateBack();
            case "start" -> startApplication();
        }
    }

    private void startApplication() {
        System.out.print("Application ID: ");
        String id = new Scanner(System.in).nextLine();

        LocalApp foundApp = findApplicationInStorage(id);

        if (foundApp == null) {
            System.out.println("Application not found");
            return;
        }

        foundApp.start();
    }

    private LocalApp findApplicationInStorage(String id) {
        for (LocalApp app : storage.getApplications()) {
            if (app.getId().equals(id)) {
                return app;
            }
        }

        return null;
    }
}
