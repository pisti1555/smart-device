package com.smart_device.backend_api.features.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class LoginDto {
    private String username;
    private String password;
}
