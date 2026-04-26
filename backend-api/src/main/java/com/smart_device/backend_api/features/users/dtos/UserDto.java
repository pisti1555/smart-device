package com.smart_device.backend_api.features.users.dtos;

import com.smart_device.backend_api.common.bases.AppDto;
import com.smart_device.backend_api.features.images.dtos.ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class UserDto extends AppDto {
    @Setter
    private String username;

    @Setter
    private ImageDto activeProfilePicture;

    @Setter
    private ImageDto activeWallpaper;

    @Setter
    private boolean childAccount;

    private List<String> roles;

    public UserDto(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String username, List<GrantedAuthority> roles) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.roles = roles.stream().map(GrantedAuthority::getAuthority).toList();
    }

    public void setRoles(List<GrantedAuthority> roles) {
        this.roles = roles.stream().map(GrantedAuthority::getAuthority).toList();
    }
}
