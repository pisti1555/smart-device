package com.smart_device.backend_api.features.users.entities;

import com.smart_device.backend_api.common.bases.AppEntity;
import com.smart_device.backend_api.features.apps.entities.AppApplication;
import com.smart_device.backend_api.features.images.entities.AppImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppImage> images;

    @ManyToMany(mappedBy = "users")
    private List<AppApplication> apps;
}
