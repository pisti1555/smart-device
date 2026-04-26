package com.smart_device.app_device.models;

import com.smart_device.app_device.models.common.AppModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ApplicationModel extends AppModel {
    private String name;
    private String iconUrl;
    private String category;
    private boolean adultOnly;

    @Override
    public String toString() {
        return super.toString() +
                "Name: " + name + '\n' +
                "Icon URL: " + iconUrl + '\n' +
                "Category: " + category + '\n' +
                "Adult only: " + adultOnly + '\n';
    }
}
