package com.github.ovenal.demo.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PagerPage {
    private final Logger logger = LoggerFactory.getLogger(PagerPage.class);
    private final WebDriver driver;
    private final By locatorItems = By.tagName("li");

    @FindBy(css = "div.pages")
    private WebElement pager;

    public PagerPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasNextPage() {
        WebElement item = getLastItem();
        return item != null && item.getText().trim().equalsIgnoreCase(">");
    }

    public void openNextPage() {
        WebElement item = getLastItem();
        if (item == null) {
            throw new RuntimeException("There is no items in the pager component");
        }
        item.click();
        logger.debug("Next page was opened");
    }

    private WebElement getLastItem() {
        List<WebElement> items = getItems();
        return items.isEmpty()
            ? null
            : items.get(items.size() - 1);
    }

    private List<WebElement> getItems() {
        return pager.findElements(locatorItems);
    }
}
