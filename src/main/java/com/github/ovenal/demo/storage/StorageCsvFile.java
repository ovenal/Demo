package com.github.ovenal.demo.storage;

import com.github.ovenal.demo.Config;
import com.github.ovenal.demo.model.ProductItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a file storage. The data will be saved/read to/from text file in CSV-format
 */
public class StorageCsvFile implements Storage {
    private final Logger logger = LoggerFactory.getLogger(StorageCsvFile.class);

    public void write(String label, List<ProductItem> items) {
        logger.debug("Start data writing");
        try (Writer writer = new BufferedWriter(new FileWriter(Config.FOLDER + label))) {
            for (ProductItem item : items) {
                writer.write(convertToString(item) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("An exception occurred at data writing", e);
        }
        logger.debug("Data writing has finished.");
    }

    public List<ProductItem> read(String label) {
        logger.debug("Start data reading");
        List<ProductItem> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.FOLDER + label))) {
            String line = reader.readLine();
            while (line != null) {
                items.add(parseFromString(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("An exception occurred at data reading", e);
        }
        logger.debug("Data reading has finished.");
        return items;
    }

    private String convertToString(ProductItem item) {
        return String.format("%s;%s;%s", item.getName(), item.getBrand(), item.getPrice());
    }

    private ProductItem parseFromString(String csvLine) {
        String[] tokens = csvLine.split(";");
        if (tokens.length != 3) {
            throw new RuntimeException(
                    "Wrong format of input data: '" + csvLine + "', expected format is '<name>;<brand>;<price>'");
        }
        return new ProductItem(tokens[0], tokens[1], tokens[2]);
    }
}
