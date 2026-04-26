package com.smart_device.backend_api.features.users.repositories;

import com.smart_device.backend_api.common.bases.AppRepository;
import com.smart_device.backend_api.features.users.entities.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AppRepository<AppUser> {
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
