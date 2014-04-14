package com.github.ovenal.demo.web.pages;

import com.github.ovenal.demo.model.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

import java.util.ArrayList;
import java.util.List;

public class ProductListPage {
    private final static Logger logger = LoggerFactory.getLogger(ProductListPage.class);
    private final WebDriver driver;
    private final PagerPage pager;

    private final By locatorName = By.tagName("em");
    private final By locatorBrand = By.tagName("b");
    private final By locatorPrice = By.cssSelector("span.priceBox");

    @FindBy(css = ".gItem")
    private List<WebElement> listItems;

    private final int MAX_PAGE_COUNT = 50;

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
        this.pager = PageFactory.initElements(driver, PagerPage.class);
    }

    /**
     * Returns the product lists. It iterates over all pages if there is a pagination.
     * @return
     */
    public List<ProductItem> getProducts() {
        int i = 1;
        logger.debug("Start reading product list");
        List<ProductItem> resultList = new ArrayList<>();
        while (true) {
            resultList.addAll(getVisibleProducts());

            if (i++ > MAX_PAGE_COUNT) {
                throw new RuntimeException("We have already processed " + MAX_PAGE_COUNT + " pages, it something might go wrong");
            }
            if (!pager.hasNextPage()) {
                break;
            }
            pager.openNextPage();
        }
        logger.debug("Reading has finished, found {} items", resultList.size());
        return resultList;
    }

    private List<ProductItem> getVisibleProducts() {
        List<ProductItem> resultList = new ArrayList<>();
        logger.debug("Found {} items on current page", listItems.size());
        for (WebElement item : listItems) {
            logger.debug("Reading item parameters");
            resultList.add(new ProductItem(
                    item.findElement(locatorName).getText().trim(),
                    item.findElement(locatorPrice).getText().trim(),
                    item.findElement(locatorBrand).getText().trim()));
        }
        return resultList;
    }
}
