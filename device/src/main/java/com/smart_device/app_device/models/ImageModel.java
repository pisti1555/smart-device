package com.smart_device.app_device.models;

import com.smart_device.app_device.models.common.AppModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ImageModel extends AppModel {
    private String url;

    @Override
    public String toString() {
        return super.toString() +
                "URL: " + url + '\n';
    }
}
