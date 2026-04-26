package com.smart_device.app_device.features.apps;

import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.LocalFeature;
import com.smart_device.app_device.features.apps.delete.DeleteAppFeature;
import com.smart_device.app_device.features.apps.install.InstallAppFeature;
import com.smart_device.app_device.features.apps.library.GetAppLibraryFeature;
import com.smart_device.app_device.features.apps.search.SearchAppsFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppsFeature implements LocalFeature {
    private final SearchAppsFeature searchAppsFeature;
    private final GetAppLibraryFeature getAppLibraryFeature;
    private final InstallAppFeature installAppFeature;
    private final DeleteAppFeature deleteAppFeature;

    @Autowired
    public AppsFeature(SearchAppsFeature searchAppsFeature, GetAppLibraryFeature getAppLibraryFeature, InstallAppFeature installAppFeature, DeleteAppFeature deleteAppFeature) {
        this.searchAppsFeature = searchAppsFeature;
        this.getAppLibraryFeature = getAppLibraryFeature;
        this.installAppFeature = installAppFeature;
        this.deleteAppFeature = deleteAppFeature;
    }

    @Override
    public void run() {
        printSeparator();
        selectOption();
    }

    private void selectOption() {
        List<InputOption> options = List.of(
                InputOption.create("back", "Go back"),
                InputOption.create("library", "See installed applications"),
                InputOption.create("search", "Search in the app store"),
                InputOption.create("install", "Install an application"),
                InputOption.create("delete", "Delete an application from this device by its ID")
        );

        String option = ConsoleInput.optionsInput(options);

        switch (option) {
            case "back" -> ScreenNavigator.navigateBack();
            case "library" -> ScreenNavigator.navigateForward(getAppLibraryFeature);
            case "search" -> ScreenNavigator.navigateForward(searchAppsFeature);
            case "install" -> ScreenNavigator.navigateForward(installAppFeature);
            case "delete" -> ScreenNavigator.navigateForward(deleteAppFeature);
        }
    }
}
