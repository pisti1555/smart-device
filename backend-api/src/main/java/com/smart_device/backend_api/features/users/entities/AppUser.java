package com.smart_device.backend_api.features.users.entities;

import com.smart_device.backend_api.common.bases.AppEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "users")
@NoArgsConstructor
@Getter @Setter
public class AppUser extends AppEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String activeProfilePicture;
    private String activeWallpaper;
}
