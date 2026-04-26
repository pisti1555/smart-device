package com.smart_device.backend_api.features.apps.entities;

import com.smart_device.backend_api.common.bases.AppEntity;
import com.smart_device.backend_api.features.users.entities.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Table(name = "apps")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AppApplication extends AppEntity {
    private String name;

    @Column(name = "icon_url")
    private String iconUrl;

    @ManyToMany
    @JoinTable(
            name = "app_user",
            joinColumns = @JoinColumn(name = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<AppUser> users;
}