package com.smart_device.app_device._device.screens;

import com.smart_device.app_device.features._common.Feature;

import java.util.Stack;

public class ScreenNavigator {
    private static final Stack<Feature<?>> SCREENS_OPEN = new Stack<>();

    public static void showCurrent() {
        if (!SCREENS_OPEN.isEmpty()) {
            SCREENS_OPEN.peek().run();
        }
    }

    public static void navigateForward(Feature<?> screen) {
        SCREENS_OPEN.push(screen);
        showCurrent();
    }

    public static void navigateBack() {
        SCREENS_OPEN.pop();
        showCurrent();
    }
}
