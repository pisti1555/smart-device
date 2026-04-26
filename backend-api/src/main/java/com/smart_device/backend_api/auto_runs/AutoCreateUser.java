package com.smart_device.backend_api.auto_runs;

import com.smart_device.backend_api.features.auth.dtos.RegistrationDto;
import com.smart_device.backend_api.features.auth.services.AuthService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoCreateUser {
    private final AuthService authService;

    @Autowired
    public AutoCreateUser(AuthService authService) {
        this.authService = authService;
    }

    @PostConstruct
    public void init(){
        var dto = new RegistrationDto("user", "password", "password");
        authService.register(dto);
    }
}
