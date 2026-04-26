package com.smart_device.app_device.models;

import com.smart_device.app_device.models.common.AppModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserModel extends AppModel {
    private String username;
    private ImageModel activeProfilePicture;
    private ImageModel activeWallpaper;
    private boolean childAccount;
    private List<String> roles;

    @Override
    public String toString() {
        String str = super.toString() +
                "Username: " + username + '\n' +
                "Active profile picture: " + activeProfilePicture + '\n' +
                "Active wallpaper: " + activeWallpaper + '\n' +
                "Child account: " + childAccount + '\n' +
                "Roles: [";

        StringBuilder sb = new StringBuilder(str);
        roles.forEach(r -> sb.append(r + ' '));
        sb.append("]\n");

        return sb.toString();
    }
}
