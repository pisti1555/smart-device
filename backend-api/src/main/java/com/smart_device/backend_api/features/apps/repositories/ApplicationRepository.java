package com.smart_device.backend_api.features.apps.repositories;

import com.smart_device.backend_api.common.bases.AppRepository;
import com.smart_device.backend_api.features.apps.entities.AppApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends AppRepository<AppApplication> {
    Page<AppApplication> findAllByUsersUsername(String username, Pageable pageable);
}
