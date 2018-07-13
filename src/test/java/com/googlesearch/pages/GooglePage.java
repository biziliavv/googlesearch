package com.googlesearch.pages;

import com.googlesearch.framework.AbstractBasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GooglePage extends AbstractBasePage {

    private static final String GOOGLE_HOME_URL = "https://www.google.com/";
    private static final By BY_SEARCH_FIELD = By.name("q");
    private static final String linkXpath = "//p/a[@class='spell']";
    private static final String englishLinkXpath = "//a[text()='English']";
    private static final String noSearchContainerXpath = "//div[@class='med card-section']";

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        getDriver().navigate().to(GOOGLE_HOME_URL);
    }

    public void enterSearchPhrase(String phrase) {
        WebElement searchField = driverWait(10).until(ExpectedConditions.elementToBeClickable(BY_SEARCH_FIELD));
        searchField.sendKeys(phrase);
    }

    public void submitSearch(){
        WebElement searchField = driverWait(10).until(ExpectedConditions.elementToBeClickable(BY_SEARCH_FIELD));
        searchField.submit();
    }

    public boolean pageTitleContains(String phrase) {
        try {
            return driverWait(5).until(ExpectedConditions.titleContains(phrase));
        } catch (TimeoutException ex) {
            return false;
        }
    }
    public boolean pageContains() {
        try {
            WebElement labelForDidYouMean = getDriver().findElement(By.xpath("//span[text()='Did you mean:']"));
            labelForDidYouMean.isDisplayed();
            return labelForDidYouMean.isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }
    }
    public boolean googleSearchButtonIsPresent() {
        try {
            WebElement searchField = driverWait(10).until(ExpectedConditions.elementToBeClickable(BY_SEARCH_FIELD));
            return searchField.isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }
    }
    public void clickOnLink(){
        try {
            WebElement linkText = driverWait(10).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(linkXpath))));
            linkText.click();
        } catch (NoSuchElementException e){
            System.out.println("No element");
        }
    }
    public void clickOnEnglish(){
        WebElement linkText = driverWait(10).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(englishLinkXpath))));
        linkText.click();
    }
    public boolean noSearchResultIsPresent() {
        try {
            WebElement noResultsContainer = getDriver().findElement(By.xpath(noSearchContainerXpath));
            return noResultsContainer.isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }
    }
}
