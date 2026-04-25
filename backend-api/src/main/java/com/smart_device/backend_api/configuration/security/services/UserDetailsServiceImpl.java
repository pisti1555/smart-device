package com.smart_device.backend_api.configuration.security.services;

import com.smart_device.backend_api.configuration.security.entities.UserDetailsImpl;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserDetailsImpl(user.getUsername(), user.getPassword());
    }
}
