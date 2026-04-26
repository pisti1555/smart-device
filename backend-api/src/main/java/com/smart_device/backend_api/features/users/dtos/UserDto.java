package com.smart_device.backend_api.features.users.dtos;

import com.smart_device.backend_api.common.bases.AppDto;
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
