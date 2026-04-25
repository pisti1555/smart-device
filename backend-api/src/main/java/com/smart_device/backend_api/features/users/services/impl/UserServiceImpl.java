package com.smart_device.backend_api.features.users.services.impl;

import com.smart_device.backend_api.features.users.dtos.SaveUserDto;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.repositories.UserRepository;
import com.smart_device.backend_api.features.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getByUsername(String username) {
        Optional<AppUser> result = userRepository.findByUsername(username);
        return new UserDto(result.get().getId().toString(), result.get().getUsername());
    }

    @Override
    public UserDto save(SaveUserDto dto) {
        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        AppUser savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId().toString(), savedUser.getUsername());
    }
}
