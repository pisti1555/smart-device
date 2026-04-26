package com.smart_device.backend_api.features.users.services.impl;

import com.smart_device.backend_api.common.exceptions.custom_exceptions.BadRequestException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.NotFoundException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnexpectedException;
import com.smart_device.backend_api.features.auth.entities.AppRole;
import com.smart_device.backend_api.features.auth.repositories.RoleRepository;
import com.smart_device.backend_api.features.images.services.ImageService;
import com.smart_device.backend_api.features.users.dtos.EditUserDto;
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
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder, ImageService imageService, ModelMapper mapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
        this.mapper = mapper;
    }

    @Override
    public UserDto editUser(EditUserDto dto, String username) {
        AppUser user = getUserByUsernameOrThrow(username);

        if (dto.password() != null && !dto.password().isBlank()) {
            if (dto.password().length() < 6 || dto.password().length() > 100) {
                throw new BadRequestException("Password must be between 6 and 100 characters.");
            }
            user.setPassword(passwordEncoder.encode(dto.password()));
        }

        if (dto.profilePicture() != null && !dto.profilePicture().isBlank()) {
            UUID imageId = convertOrThrow(dto.profilePicture());
            imageService.setActiveProfileImage(imageId, user);
        }

        if (dto.wallpaper() != null && !dto.wallpaper().isBlank()) {
            UUID imageId = convertOrThrow(dto.wallpaper());
            imageService.setActiveWallpaper(imageId, user);
        }

        AppUser savedUser = userRepository.save(user);

        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getByUsername(String username) {
        AppUser result = getUserByUsernameOrThrow(username);
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

        if (roleNames.contains(RoleRepository.ROLE_CHILD)) {
            user.setChildAccount(true);
        }

        return userRepository.save(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    private AppUser getUserByUsernameOrThrow(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found."));
    }

    private UUID convertOrThrow(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid ID format.");
        }
    }
}
