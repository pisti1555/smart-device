package com.smart_device.app_device;

import com.smart_device.app_device._device.DeviceLauncher;
import com.smart_device.app_device.configuration.AppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        DeviceLauncher device = context.getBean(DeviceLauncher.class);

        device.launch();
    }
}