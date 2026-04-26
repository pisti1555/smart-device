package com.smart_device.backend_api.features.users.entities;

import com.smart_device.backend_api.common.bases.AppEntity;
import com.smart_device.backend_api.features.apps.entities.AppApplication;
import com.smart_device.backend_api.features.auth.entities.AppRole;
import com.smart_device.backend_api.features.images.entities.AppImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity @Table(name = "users")
@NoArgsConstructor
@Getter @Setter
public class AppUser extends AppEntity implements UserDetails {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "child_account", nullable = false)
    private boolean childAccount;

    @OneToOne
    @JoinColumn(name = "active_profile_picture_id")
    private AppImage activeProfilePicture;

    @OneToOne
    @JoinColumn(name = "active_wallpaper_image_id")
    private AppImage activeWallpaper;

    @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppImage> images;

    @ManyToMany(mappedBy = "users")
    private List<AppApplication> apps;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false)
    )
    private List<AppRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
