package com.github.ovenal.demo.storage;

import com.github.ovenal.demo.model.ProductItem;

import java.util.List;

/**
 * Represents a storage where the product data may be written or read
 */
public interface Storage {
    /**
     * Writes the product items to a storage with provided label
     * @param label     description that can be used to read these data
     * @param items     data to save
     */
    public void write(String label, List<ProductItem> items);

    /**
     * Reads the product items by specified label
     * @param label text criteria to find the saved data
     * @return      the product items list
     */
    public List<ProductItem> read(String label);
}
