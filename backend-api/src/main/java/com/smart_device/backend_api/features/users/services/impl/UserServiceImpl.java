package com.smart_device.backend_api.features.users.services.impl;

import com.smart_device.backend_api.common.exceptions.custom_exceptions.NotFoundException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnexpectedException;
import com.smart_device.backend_api.features.auth.entities.AppRole;
import com.smart_device.backend_api.features.auth.repositories.RoleRepository;
import com.smart_device.backend_api.features.users.dtos.SaveUserDto;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.repositories.UserRepository;
import com.smart_device.backend_api.features.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    public UserDto save(SaveUserDto dto) {
        AppRole userRole = roleRepository
                .findByRole(RoleRepository.ROLE_USER)
                .orElseThrow(() -> new UnexpectedException("Something went wrong."));

        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoles(Set.of(userRole));

        AppUser savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }
}
