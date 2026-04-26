package com.smart_device.app_device.features.images.get_images;

import com.smart_device.app_device._device.input.ConsoleInput;
import com.smart_device.app_device._device.input.InputOption;
import com.smart_device.app_device._device.screens.ScreenNavigator;
import com.smart_device.app_device.features._common.Feature;
import com.smart_device.app_device.models.ImageModel;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.models.common.PagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GetImagesFeature implements Feature<GetImagesHandler> {
    private final int PAGE_SIZE = 5;

    private final GetImagesHandler getImagesHandler;

    @Autowired
    public GetImagesFeature(GetImagesHandler getImagesHandler) {
        this.getImagesHandler = getImagesHandler;
    }

    @Override
    public void run() {
        printSeparator();
        AppResult<PagedList<ImageModel>> result = getImagesHandler.handle(new GetImagesRequest(1, PAGE_SIZE));

        if  (result.isSuccess()) {
            paginateList(result.getData());
        } else {
            result.printErrorMessage();
            ScreenNavigator.navigateBack();
        }
    }

    private void paginateList(PagedList<ImageModel> pagedList) {
        int currentPage = 1;

        if (pagedList.getItems().isEmpty()) {
            System.out.println("No images found.");
        }

        String option = ConsoleInput.optionsInput(makeOptions(pagedList));

        switch (option) {
            case "back" -> ScreenNavigator.navigateBack();
            case "next" -> getImagesHandler.handle(new GetImagesRequest(++currentPage, PAGE_SIZE));
            case "prev" -> getImagesHandler.handle(new GetImagesRequest(--currentPage, PAGE_SIZE));
        }
    }

    private List<InputOption> makeOptions(PagedList<?> pagedList) {
        List<InputOption> options = new LinkedList<>();

        if (pagedList.getPage() < pagedList.getTotalPages()) {
            options.add(InputOption.create("next", "Next page"));
        }

        if (pagedList.getPage() > 1) {
            options.add(InputOption.create("prev", "Previous page"));
        }

        options.add(InputOption.create("back", "Back"));

        return options;
    }
}
