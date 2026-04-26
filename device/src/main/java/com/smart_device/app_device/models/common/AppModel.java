package com.smart_device.app_device.models.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public abstract class AppModel {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "ID: " + id + '\n' +
                "Created at: " + createdAt + '\n' +
                "Updated at: " + updatedAt + '\n';
    }
}
