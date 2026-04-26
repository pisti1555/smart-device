package com.smart_device.app_device.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.smart_device.app_device"})
@PropertySource("classpath:application.properties")
public class AppConfiguration {
}
