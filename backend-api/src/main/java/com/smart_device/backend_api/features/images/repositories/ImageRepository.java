package com.smart_device.backend_api.features.images.repositories;

import com.smart_device.backend_api.features.images.entities.AppImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<AppImage, UUID> {
    Page<AppImage> findAllByOwnerUserUsername(String username, Pageable pageable);
}
