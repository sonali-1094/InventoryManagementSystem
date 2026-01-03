package com.inventory.utils;

import java.util.Collection;

public class InventoryUtils {

    // Generic method to display any collection
    public static <T> void displayCollection(Collection<T> items) {
        for (T item : items) {
            System.out.println(item);
        }
    }

    // Generic method to get size of any collection
    public static <T> int getSize(Collection<T> items) {
        return items.size();
    }
}

