package com.smart_device.backend_api.configuration.modelmapper;

import com.smart_device.backend_api.features.apps.dtos.ApplicationDto;
import com.smart_device.backend_api.features.apps.entities.AppApplication;
import com.smart_device.backend_api.features.images.dtos.ImageDto;
import com.smart_device.backend_api.features.images.entities.AppImage;
import com.smart_device.backend_api.features.users.dtos.UserDto;
import com.smart_device.backend_api.features.users.entities.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(AppUser.class, UserDto.class);

        mapper.typeMap(AppImage.class, ImageDto.class);

        mapper.typeMap(AppApplication.class, ApplicationDto.class);

        return mapper;
    }
}
