package com.smart_device.app_device.features.apps.library;

import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAppLibraryFeature implements RemoteFeature<GetAppLibraryHandler> {
    private final GetAppLibraryHandler getAppLibraryHandler;

    @Autowired
    public GetAppLibraryFeature(GetAppLibraryHandler getAppLibraryHandler) {
        this.getAppLibraryHandler = getAppLibraryHandler;
    }

    @Override
    public void run() {
        printPage(1);

        while (true) {
            System.out.println("0 -> Back or Any other number -> select a page.");

            int input = ConsoleInput.numberInput();
            if (input == 0) {
                break;
            }
            if (input < 0) {
                System.out.println("Invalid page.");
                continue;
            }

            printPage(input);
        }

        ScreenNavigator.navigateBack();
    }

    private void printPage(int page) {
        int PAGE_SIZE = 5;
        var result = getAppLibraryHandler.handle(new GetAppLibraryRequest(page, PAGE_SIZE));

        if (result.isSuccess()) {
            System.out.println(result.getData());
        } else {
            result.printErrorMessage();
        }
    }
}
