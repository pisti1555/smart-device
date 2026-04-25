package com.smart_device.backend_api.common.bases;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppRepository<T extends AppEntity> extends JpaRepository<T, UUID> {
}
