package com.smart_device.app_device._device.screens;

import java.util.Stack;

public class ScreenNavigator {
    private static final Stack<Screen> SCREENS_OPEN = new Stack<>();

    public static void showCurrent() {
        if (!SCREENS_OPEN.isEmpty()) {
            SCREENS_OPEN.peek().run();
        }
    }

    public static void navigateForward(Screen screen) {
        SCREENS_OPEN.push(screen);
        showCurrent();
    }

    public static void navigateBack() {
        SCREENS_OPEN.pop();
        showCurrent();
    }
}
