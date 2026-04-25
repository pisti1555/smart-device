package com.smart_device.backend_api.features.auth.entities;

import com.smart_device.backend_api.common.bases.AppEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity @Table(name = "roles")
@NoArgsConstructor
@Getter @Setter
public class AppRole extends AppEntity implements GrantedAuthority {
    @Column(name = "role", unique = true, nullable = false)
    private String role;

    @Override
    public @Nullable String getAuthority() {
        return role;
    }
}
