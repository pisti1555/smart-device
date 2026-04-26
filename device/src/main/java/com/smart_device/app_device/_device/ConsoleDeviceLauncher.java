package com.smart_device.app_device._device;

import com.smart_device.app_device._device.authentication.Authentication;
import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features.auth.login.LoginFeature;
import com.smart_device.app_device.features.images.ImageFeature;
import com.smart_device.app_device.features.images.get_images.GetImagesFeature;
import com.smart_device.app_device.features.users.get_profile.GetProfileFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsoleDeviceLauncher implements DeviceLauncher {
    private final Authentication authentication;
    private final LoginFeature loginFeature;
    private final GetProfileFeature getProfileFeature;
    private final ImageFeature imageFeature;

    @Autowired
    public ConsoleDeviceLauncher(Authentication authentication, LoginFeature loginFeature, GetProfileFeature getProfileFeature, ImageFeature imageFeature) {
        this.authentication = authentication;
        this.loginFeature = loginFeature;
        this.getProfileFeature = getProfileFeature;
        this.imageFeature = imageFeature;
    }

    @Override
    public void launch() {
        initialize();
        while (true) {
            mainMenu();
        }
    }

    private void initialize() {
        while(!authentication.isAuthenticated()) {
            loginFeature.run();
        }
    }

    private void mainMenu() {
        List<InputOption> options = List.of(
                InputOption.create("exit", "Exit"),
                InputOption.create("profile", "Open profile"),
                InputOption.create("apps", "Open applications"),
                InputOption.create("images", "Open images")
        );

        System.out.println("\n\n--------------------- Main menu ---------------------");
        String option = ConsoleInput.optionsInput(options);

        switch (option) {
            case "exit" -> System.exit(0);
            case "profile" -> ScreenNavigator.navigateForward(getProfileFeature);
            //case "apps" -> ScreenNavigator.navigateForward(screenFactory.appsScreen());
            case "images" -> ScreenNavigator.navigateForward(imageFeature);
        }
    }
}
