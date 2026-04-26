package com.smart_device.backend_api.features.auth.repositories;

import com.smart_device.backend_api.common.bases.AppRepository;
import com.smart_device.backend_api.features.auth.entities.AppRole;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends AppRepository<AppRole> {
    String ROLE_USER = "ROLE_USER";
    String ROLE_ADMIN = "ROLE_ADMIN";
    String ROLE_CHILD = "ROLE_CHILD";

    Optional<AppRole> findByRole(String role);
}
