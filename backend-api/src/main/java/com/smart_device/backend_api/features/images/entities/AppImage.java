package com.smart_device.backend_api.features.images.entities;

import com.smart_device.backend_api.common.bases.AppEntity;
import com.smart_device.backend_api.features.images.enums.ImageType;
import com.smart_device.backend_api.features.users.entities.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "images")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AppImage extends AppEntity {
    private String url;

    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    private AppUser ownerUser;

    @Enumerated(value = EnumType.STRING)
    private ImageType type;
}
