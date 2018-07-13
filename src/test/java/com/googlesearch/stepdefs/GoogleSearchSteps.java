package com.googlesearch.stepdefs;

import com.googlesearch.pages.GooglePage;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearchSteps implements En {

    private WebDriver driver;
    private GooglePage googlePage;

    // Warning: Make sure the timeouts for hooks using a web driver are zero

    public GoogleSearchSteps() {
        Before(new String[]{"@web"}, 0, 1, (Scenario scenario) -> {
            driver = new ChromeDriver();
        });

        Before(new String[]{"@google"}, 0, 10, (Scenario scenario) -> {
            googlePage = new GooglePage(driver);
        });

        After(new String[]{"@web"}, (Scenario scenario) -> {
            driver.quit();
        });
        Given("^user opens Google search page$", () -> {
            googlePage.navigateToHomePage();
        });
        When("^user types \"([^\"]*)\" in search input$", (String phrase) -> {
            googlePage.enterSearchPhrase(phrase);

        });
        When("^user clicks on Google search button$", () -> {
            googlePage.submitSearch();
        });
        Then("^results for \"([^\"]*)\" are shown$", (String phrase) -> {
            assertThat(googlePage.pageTitleContains(phrase)).isTrue();
        });
        When("^the search phrase \"([^\"]*)\" is entered$", (String phrase) -> {
            googlePage.enterSearchPhrase(phrase);
        });
        Then("^user stays on Google search page$", () -> {
            assertThat(googlePage.googleSearchButtonIsPresent()).isTrue();
        });
        Then("^user sees “Did you mean” label$", () -> {
            assertThat(googlePage.pageContains()).isTrue();

        });
        When("^user clicks on link near “Did you mean” label$", () -> {
            googlePage.clickOnLink();
        });
        Then("^user receives that no search results appears$", () -> {
            assertThat(googlePage.noSearchResultIsPresent()).isTrue();
        });
        When("^user changes language to english$", () -> {
            googlePage.clickOnEnglish();
        });


    }
}