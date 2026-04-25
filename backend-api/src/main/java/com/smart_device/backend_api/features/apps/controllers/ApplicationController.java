package com.smart_device.backend_api.features.apps.controllers;

import com.smart_device.backend_api.app_models.PagedList;
import com.smart_device.backend_api.features.apps.dtos.ApplicationAddToUserDto;
import com.smart_device.backend_api.features.apps.dtos.ApplicationDto;
import com.smart_device.backend_api.features.apps.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apps")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/{id}")
    public ApplicationDto getApplicationsOfUser(@PathVariable String id) {
        return applicationService.getById(id);
    }

    @GetMapping
    public PagedList<ApplicationDto> getApplicationsOfUser(Pageable pageable, Authentication authentication) {
        return applicationService.getAllByUsername(authentication.getName(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @PostMapping
    public ApplicationDto getApplicationsOfUser(@RequestBody ApplicationAddToUserDto dto, Authentication authentication) {
        return applicationService.addToUser(dto.getId(), authentication.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> getApplicationsOfUser(@PathVariable String id, Authentication authentication) {
        applicationService.deleteFromUser(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
