package com.smart_device.backend_api.features.auth.services.impl;

import com.smart_device.backend_api.common.exceptions.custom_exceptions.BadRequestException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnauthorizedException;
import com.smart_device.backend_api.features.auth.dtos.LoginDto;
import com.smart_device.backend_api.features.auth.dtos.RegistrationDto;
import com.smart_device.backend_api.features.auth.repositories.RoleRepository;
import com.smart_device.backend_api.features.auth.services.AuthService;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager, UserService userService,
            ModelMapper mapper
    ) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public UserDto login(LoginDto dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Invalid username or password.");
        }

        return userService.getByUsername(dto.username());
    }

    @Override
    public UserDto register(RegistrationDto dto) {
        if (userService.isUsernameExists(dto.username())) {
            throw new BadRequestException("Username is already taken.");
        }

        if (!dto.password().equals(dto.repeatPassword())) {
            throw new BadRequestException("Passwords don't match");
        }

        if(!isValidRoleName(dto.role())) {
            throw new BadRequestException("Invalid role. It can be either ROLE_USER or ROLE_ADMIN.");
        }

        List<String> roleNames = new LinkedList<>();
        roleNames.add(RoleRepository.ROLE_USER);

        if (dto.role().equalsIgnoreCase(RoleRepository.ROLE_ADMIN)) {
            roleNames.add(RoleRepository.ROLE_ADMIN);
        }

        AppUser savedUser = userService.createUserWithRoles(dto.username(), dto.password(), roleNames);

        return mapper.map(savedUser, UserDto.class);
    }

    private boolean isValidRoleName(String roleName) {
        return
                roleName.equalsIgnoreCase(RoleRepository.ROLE_ADMIN) ||
                roleName.equalsIgnoreCase(RoleRepository.ROLE_USER);
    }
}
