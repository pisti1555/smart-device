package com.smart_device.backend_api.features.users.services.impl;

import com.smart_device.backend_api.common.exceptions.custom_exceptions.NotFoundException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnexpectedException;
import com.smart_device.backend_api.features.auth.entities.AppRole;
import com.smart_device.backend_api.features.auth.repositories.RoleRepository;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.repositories.UserRepository;
import com.smart_device.backend_api.features.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder, ModelMapper mapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDto getByUsername(String username) {
        AppUser result = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found."));

        return mapper.map(result, UserDto.class);
    }

    @Override
    public AppUser createUserWithRoles(String username, String password, List<String> roleNames) {
        List<AppRole> roles = new LinkedList<>();
        for (String roleName : roleNames) {
            AppRole role = roleRepository.
                    findByRole(roleName)
                    .orElseThrow(UnexpectedException::new);
            roles.add(role);
        }

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
