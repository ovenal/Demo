package com.github.ovenal.demo.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    private final WebDriver driver;

    @FindBy(id = "searchContent")
    private WebElement searchField;

    @FindBy(id = "searchButtonTopSubmit")
    private WebElement searchButton;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductListPage search(String criteria) {
        searchField.clear();
        searchField.sendKeys(criteria);
        searchButton.click();
        return PageFactory.initElements(driver, ProductListPage.class);
    }
}
