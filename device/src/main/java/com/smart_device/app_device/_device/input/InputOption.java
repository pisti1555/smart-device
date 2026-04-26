package com.smart_device.app_device._device.input;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class InputOption {
    private String value;
    private String label;

    public static InputOption create(String value, String label) {
        InputOption option = new InputOption();
        option.value = value;
        option.label = label;
        return option;
    }
}
