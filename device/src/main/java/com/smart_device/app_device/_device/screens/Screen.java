package com.smart_device.app_device._device.screens;

public interface Screen {
    void run();
    default void printSeparator() {
        System.out.println("\n-----------------------------------------------------------------------------------\n");
    }
}
