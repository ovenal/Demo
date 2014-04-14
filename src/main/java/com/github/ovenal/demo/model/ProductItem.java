package com.github.ovenal.demo.model;

import java.util.List;

/**
 * Represents a single product item.
 *
 * Notice: currency, brand, sizes and other parameters later will be converted to corresponding enum types
 */
public class ProductItem {
    private String name;
    private String price;
    private String brand;
    private List<String> availableSizes;

    public ProductItem(String name, String price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }
}
