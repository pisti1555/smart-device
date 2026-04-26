package com.smart_device.app_device._device.apps.local_app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class LocalAppImpl implements LocalApp {
    private String id;
    private String name;
    private String iconUrl;
    private boolean isStarted;

    @Override
    public String toString() {
        return "APP: \n" +
                "  ID: " + id + "  |  " +
                "Name: " + name + "  |  " +
                "Icon: " + iconUrl + '\n';
    }
}
