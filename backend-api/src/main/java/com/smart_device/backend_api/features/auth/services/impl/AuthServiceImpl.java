package com.smart_device.backend_api.features.auth.services.impl;

import com.smart_device.backend_api.common.exceptions.custom_exceptions.BadRequestException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnauthorizedException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnexpectedException;
import com.smart_device.backend_api.features.auth.dtos.LoginDto;
import com.smart_device.backend_api.features.auth.dtos.RegistrationDto;
import com.smart_device.backend_api.features.auth.entities.AppRole;
import com.smart_device.backend_api.features.auth.repositories.RoleRepository;
import com.smart_device.backend_api.features.auth.services.AuthService;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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

        AppUser user = userRepository
                .findByUsername(dto.username())
                .orElseThrow(() -> new UnexpectedException("Something went wrong on server side."));

        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto register(RegistrationDto dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new BadRequestException("Username is already taken.");
        }

        if (!dto.password().equals(dto.repeatPassword())) {
            throw new BadRequestException("Passwords don't match");
        }

        AppRole userRole = roleRepository
                .findByRole(RoleRepository.ROLE_USER)
                .orElseThrow(UnexpectedException::new);

        String encodedPassword = passwordEncoder.encode(dto.password());

        AppUser user = new AppUser();
        user.setUsername(dto.username());
        user.setPassword(encodedPassword);
        user.setRoles(List.of(userRole));

        AppUser savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }
}
