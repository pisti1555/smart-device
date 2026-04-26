package com.smart_device.backend_api.auto_runs;

import com.smart_device.backend_api.features.auth.repositories.RoleRepository;
import com.smart_device.backend_api.features.users.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoCreateUser {
    private final UserService userService;

    @Autowired
    public AutoCreateUser(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        userService.createUserWithRoles(
                "admin", "admin",
                List.of(RoleRepository.ROLE_ADMIN, RoleRepository.ROLE_USER)
        );
    }
}
