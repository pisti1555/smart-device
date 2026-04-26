package com.smart_device.app_device._device;

import com.smart_device.app_device._device.apps.local_app.LocalAppImpl;
import com.smart_device.app_device._device.apps.storage.ApplicationStorage;
import com.smart_device.app_device._device.authentication.Authentication;
import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features.apps.AppsFeature;
import com.smart_device.app_device.features.apps.library.GetAppLibraryHandler;
import com.smart_device.app_device.features.apps.library.GetAppLibraryRequest;
import com.smart_device.app_device.features.auth.login.LoginFeature;
import com.smart_device.app_device.features.auth.logout.LogoutFeature;
import com.smart_device.app_device.features.auth.register.RegistrationFeature;
import com.smart_device.app_device.features.icons.IconsFeature;
import com.smart_device.app_device.features.images.ImageFeature;
import com.smart_device.app_device.features.users.get_profile.GetProfileFeature;
import com.smart_device.app_device.models.ApplicationModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.models.common.PagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class ConsoleDeviceLauncher implements DeviceLauncher {
    private final Authentication authentication;
    private final LoginFeature loginFeature;
    private final LogoutFeature logoutFeature;
    private final RegistrationFeature registrationFeature;
    private final GetProfileFeature getProfileFeature;
    private final ImageFeature imageFeature;
    private final AppsFeature appsFeature;
    private final IconsFeature iconsFeature;
    private final GetAppLibraryHandler getAppLibraryHandler;
    private final ApplicationStorage applicationStorage;

    @Autowired
    public ConsoleDeviceLauncher(Authentication authentication, LoginFeature loginFeature, LogoutFeature logoutFeature, RegistrationFeature registrationFeature, GetProfileFeature getProfileFeature, ImageFeature imageFeature, AppsFeature appsFeature, IconsFeature iconsFeature, GetAppLibraryHandler getAppLibraryHandler, ApplicationStorage applicationStorage) {
        this.authentication = authentication;
        this.loginFeature = loginFeature;
        this.logoutFeature = logoutFeature;
        this.registrationFeature = registrationFeature;
        this.getProfileFeature = getProfileFeature;
        this.imageFeature = imageFeature;
        this.appsFeature = appsFeature;
        this.iconsFeature = iconsFeature;
        this.getAppLibraryHandler = getAppLibraryHandler;
        this.applicationStorage = applicationStorage;
    }

    @Override
    public void launch() {
        while (true) {
            if (!authentication.isAuthenticated()) {
                login();
            }
            printWallpaper();
            fetchApps();
            mainMenu();
        }
    }

    private void login() {
        while(!authentication.isAuthenticated()) {
            loginFeature.run();
        }
    }

    private void printWallpaper() {
        System.out.println("\n---- WALLPAPER ----");
        if (authentication.getUser() != null && authentication.getUser().getActiveWallpaper() != null) {
            System.out.println(authentication.getUser().getActiveWallpaper().getUrl());
        } else {
            System.out.println("Default wallpaper");
        }
    }

    private void fetchApps() {
        boolean fetching = true;
        int page = 1;

        while (fetching) {
            AppResult<PagedList<ApplicationModel>> result =
                    getAppLibraryHandler.handle(new GetAppLibraryRequest(page, 100));

            if (result.isSuccess()) {
                if (page >= result.getData().getPage()) {
                    fetching = false;
                }

                Collection<ApplicationModel> items = result.getData().getItems();
                items.forEach(app ->
                        applicationStorage.addApplication(new LocalAppImpl(
                                app.getId(), app.getName(), app.getIconUrl(), false
                        ))
                );
            } else {
                System.out.println("Error fetching apps.");
                fetching = false;
                break;
            }
        }
    }

    private void mainMenu() {
        List<InputOption> options = new LinkedList<>(List.of(
                InputOption.create("exit", "Exit"),
                InputOption.create("logout", "Log out"),
                InputOption.create("icons", "Icons"),
                InputOption.create("profile", "Open profile"),
                InputOption.create("apps", "Open applications"),
                InputOption.create("images", "Open images")
        ));

        if (authentication.getUser().getRoles().contains("ROLE_ADMIN")) {
            options.add(InputOption.create("register", "Make an account for someone"));
        }

        System.out.println("\n\n--------------------- Main menu ---------------------");
        String option = ConsoleInput.optionsInput(options);

        switch (option) {
            case "exit" -> System.exit(0);
            case "profile" -> ScreenNavigator.navigateForward(getProfileFeature);
            case "icons" -> ScreenNavigator.navigateForward(iconsFeature);
            case "apps" -> ScreenNavigator.navigateForward(appsFeature);
            case "images" -> ScreenNavigator.navigateForward(imageFeature);
            case "register" -> ScreenNavigator.navigateForward(registrationFeature);
            case "logout" ->  ScreenNavigator.navigateForward(logoutFeature);
        }
    }
}
