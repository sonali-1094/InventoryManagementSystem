package com.inventory.comparators;

import com.inventory.model.Product;
import java.util.Comparator;

public class NameComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}
