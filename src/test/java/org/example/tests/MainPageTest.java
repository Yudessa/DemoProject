package org.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.pages.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPageTest {

    private static final Logger log = LoggerFactory.getLogger(MainPageTest.class);
    private static final String SELENIUM = "Selenium";
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://www.jetbrains.com/");
    }

    @Test
    public void search() {
        acceptCookiesIfExist();
        linses();

        //mainPage.searchButton.click();
        acceptCookiesIfExist();
        $("[data-test-id='search-input']").sendKeys(SELENIUM);
        $("button[data-test='full-search-button']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .click();
        $("input[data-test-id='search-input']").shouldHave(attribute("value", SELENIUM));
    }
void linses () {
    mainPage.searchButton.click();
    System.out.println("Вывод + linses");
}

    private void acceptCookiesIfExist() {
        try {
            $("button[class*='allow-all-btn']").click();
        } catch (Throwable throwable) {
            String errorMessage = throwable.getMessage();
            if (errorMessage.contains("Element should be clickable"))
                log.info("Accept Cookie button is not clickable");
            else if (errorMessage.contains("NoSuchElementException")) {
                log.info("Accept Cookie button is not exist");
            } else throw throwable;
        }
    }
}