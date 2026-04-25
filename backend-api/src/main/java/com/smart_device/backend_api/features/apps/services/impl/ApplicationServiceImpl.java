package com.smart_device.backend_api.features.apps.services.impl;

import com.smart_device.backend_api.common.app_models.PagedList;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.NotFoundException;
import com.smart_device.backend_api.common.exceptions.custom_exceptions.UnexpectedException;
import com.smart_device.backend_api.features.apps.dtos.ApplicationDto;
import com.smart_device.backend_api.features.apps.entities.AppApplication;
import com.smart_device.backend_api.features.apps.repositories.ApplicationRepository;
import com.smart_device.backend_api.features.apps.services.ApplicationService;
import com.smart_device.backend_api.features.users.entities.AppUser;
import com.smart_device.backend_api.features.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, UserRepository userRepository, ModelMapper mapper) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public ApplicationDto getById(UUID id) {
        AppApplication foundApp = getAppOrThrowNotFound(id);
        return mapper.map(foundApp, ApplicationDto.class);
    }

    @Override
    public PagedList<ApplicationDto> getAll(int page, int size) {
        var result = applicationRepository.findAll(PageRequest.of(page, size));
        return PagedList.of(result, app -> mapper.map(app, ApplicationDto.class));
    }

    @Override
    public PagedList<ApplicationDto> getAllByUsername(String username, int page, int size) {
        var result = applicationRepository.findAllByUsersUsername(username, PageRequest.of(page, size));
        return PagedList.of(result, app -> mapper.map(app, ApplicationDto.class));
    }

    @Override
    public ApplicationDto addToUser(UUID id, String username) {
        AppApplication foundApp = getAppOrThrowNotFound(id);
        AppUser user = getUserByAuthenticationOrThrow(username);

        foundApp.getUsers().add(user);

        AppApplication savedApp = applicationRepository.save(foundApp);

        return mapper.map(savedApp, ApplicationDto.class);
    }

    @Override
    public void deleteFromUser(UUID id, String username) {
        AppUser user = getUserByAuthenticationOrThrow(username);
        AppApplication foundApp = getAppOrThrowNotFound(id);

        foundApp.getUsers().remove(user);
        applicationRepository.save(foundApp);
    }

    private AppUser getUserByAuthenticationOrThrow(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UnexpectedException("Something went wrong on server side."));
    }

    private AppApplication getAppOrThrowNotFound(UUID id) {
        return applicationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Application was not found."));
    }
}
