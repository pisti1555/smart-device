package com.smart_device.backend_api.features.auth.services.impl;

import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnauthorizedException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnexpectedException;
import com.smart_device.backend_api.features.auth.services.AuthService;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto login(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Invalid username or password.");
        }

        AppUser user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UnexpectedException("Something went wrong on server side."));
        return new UserDto(user.getId().toString(), user.getUsername());
    }
}
