package com.smart_device.app_device.models.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public final class PagedList<T> {
    private int page;
    private int size;

    private int totalPages;
    private long totalItems;

    private Collection<T> items;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nPaged list:\n")
                .append("Page: ").append(page)
                .append(", Size: ").append(size).append("\n")
                .append("Total pages: ").append(totalPages)
                .append(", Total items: ").append(totalItems).append("\n")
                .append("Items:\n");

        if (items != null && !items.isEmpty()) {
            int index = 1;
            for (var item : items) {
                sb.append(index++).append(" -> ").append(item.toString()).append("\n");
            }
        } else {
            sb.append("No items.");
        }

        return sb.toString();
    }
}
