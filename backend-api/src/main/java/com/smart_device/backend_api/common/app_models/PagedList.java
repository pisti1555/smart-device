package com.smart_device.backend_api.common.app_models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PagedList<T> {
    private final int page;
    private final int size;

    private final int totalPages;
    private final long totalItems;

    private final Collection<T> items;

    public static <T> PagedList<T> of(Page<T> page) {
        int pageNumber = page.getNumber();
        int pageSize = page.getSize();

        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<T> items = page.toList();

        return new PagedList<>(pageNumber, pageSize, totalPages, totalItems, items);
    }

    public static <T, C> PagedList<C> of(Page<T> page, Function<T, C> convertFunction) {
        int pageNumber = page.getNumber() + 1;
        int pageSize = page.getSize();

        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<C> convertedItems = page.stream()
                .map(convertFunction)
                .toList();

        return new PagedList<>(pageNumber, pageSize, totalPages, totalItems, convertedItems);
    }
}
