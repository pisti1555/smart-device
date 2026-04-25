package com.smart_device.backend_api.auto_runs;

import com.smart_device.backend_api.features.users.dtos.SaveUserDto;
import com.smart_device.backend_api.features.users.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AutoCreateUser {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AutoCreateUser(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        userService.save(new SaveUserDto("user", passwordEncoder.encode("password")));
    }
}
