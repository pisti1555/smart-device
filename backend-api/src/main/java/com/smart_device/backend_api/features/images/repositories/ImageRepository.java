package com.smart_device.backend_api.features.images.repositories;

import com.smart_device.backend_api.common.bases.AppRepository;
import com.smart_device.backend_api.features.images.entities.AppImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends AppRepository<AppImage> {
    Page<AppImage> findAllByOwnerUserUsername(String username, Pageable pageable);
}
