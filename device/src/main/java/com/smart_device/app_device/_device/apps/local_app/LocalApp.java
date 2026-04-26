package com.smart_device.app_device._device.apps.local_app;

import java.util.Scanner;

public interface LocalApp {
    String getId();
    String getName();
    String getIconUrl();

    boolean isStarted();
    void setStarted(boolean started);

    default void start() {
        setStarted(true);
        System.out.println(getName() + " is running...");
        System.out.println("Press any key to stop it.");
        new Scanner(System.in).nextLine();
        stop();
    }

    default void stop() {
        setStarted(false);
        System.out.println(getName() + " is stopped.");
    }
}
