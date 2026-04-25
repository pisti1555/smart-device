package com.smart_device.backend_api.common.bases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
public abstract class AppDto {
    private String id;
    private String createdAt;
    private String updatedAt;

    public AppDto(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id.toString();
        this.createdAt = createdAt.toString();
        this.updatedAt = updatedAt.toString();
    }
}
