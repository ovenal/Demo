package com.github.ovenal.demo;

import com.github.ovenal.demo.model.ProductItem;
import com.github.ovenal.demo.storage.Storage;
import com.github.ovenal.demo.storage.StorageCsvFile;
import com.github.ovenal.demo.web.pages.ProductListPage;
import com.github.ovenal.demo.web.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Main.class);
        final String MODE_WRITE = "WRITE";
        final String MODE_READ = "READ";
        String action = System.getProperty("mode", MODE_WRITE);
        Storage storage = new StorageCsvFile();
        if (MODE_WRITE.equalsIgnoreCase(action)) {
            logger.debug("Start 'write' mode");
            WebDriver driver = new FirefoxDriver();
            List<ProductItem> listItems;
            try {
                SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
                driver.get(Config.WEB_HOST);

                ProductListPage productListPage = searchPage.search(Config.SECTION);
                listItems = productListPage.getProducts();
            } finally {
                driver.close();
            }
            storage.write("output.csv", listItems);
        } else if (MODE_READ.equalsIgnoreCase(action)) {
            logger.debug("Start 'read' mode");
            List<ProductItem> list = storage.read(Config.LABEL);
            logger.info("Read {} items from a storage with {} label", list.size(), Config.LABEL);
        } else {
            throw new RuntimeException(
                    String.format("Not supported option: '%s'. Only the following are possible: %s",
                            action, MODE_WRITE + " | " + MODE_READ));
        }
        logger.debug("Done.");
    }
}
