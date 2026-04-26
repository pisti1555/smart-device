package com.smart_device.app_device.features.users.get_profile;

import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.RemoteFeature;
import com.smart_device.app_device.features.users.change_password.ChangePasswordFeature;
import com.smart_device.app_device.models.UserModel;
import com.smart_device.app_device.models.common.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProfileFeature implements RemoteFeature<GetProfileHandler> {
    private final GetProfileHandler getProfileHandler;
    private final ChangePasswordFeature  changePasswordFeature;

    @Autowired
    public GetProfileFeature(GetProfileHandler getProfileHandler, ChangePasswordFeature changePasswordFeature) {
        this.getProfileHandler = getProfileHandler;
        this.changePasswordFeature = changePasswordFeature;
    }

    @Override
    public void run() {
        printSeparator();
        AppResult<UserModel> result = getProfileHandler.handle(new GetProfileRequest());

        if (result.isSuccess()) {
            System.out.println(result.getData());
        } else {
            result.printErrorMessage();
        }

        selectOption();
    }

    private void selectOption() {
        List<InputOption> options = List.of(
                InputOption.create("back", "Go back"),
                InputOption.create("change-pw", "Change password")
        );
        String option = ConsoleInput.optionsInput(options);

        switch (option) {
            case "back" -> ScreenNavigator.navigateBack();
            case "change-pw" -> ScreenNavigator.navigateForward(changePasswordFeature);
        }
    }
}
