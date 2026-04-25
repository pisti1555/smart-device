package com.smart_device.backend_api.features.users.dtos;

import com.smart_device.backend_api.common.bases.AppDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserDto extends AppDto {
    private String username;
}
