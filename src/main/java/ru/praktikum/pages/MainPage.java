package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public static final By TOP_ORDER_BUTTON = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать'][1]");
    public static final By BOTTOM_ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[text()='Заказать']");
    public static final By FAQ_QUESTION = By.xpath(".//div[@data-accordion-component='AccordionItemButton']");
    public static final By FAQ_ANSWER = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']");
    public static final By COOKIE_BUTTON = By.id("rcc-confirm-button");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void clickCookieButton() {
        driver.findElement(COOKIE_BUTTON).click();
    }

    public List<WebElement> getFaqQuestions() {
        return driver.findElements(FAQ_QUESTION);
    }

    public void clickFaqQuestion(int index) {
    //    driver.findElements(FAQ_QUESTION).get(index).click();
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElements(FAQ_QUESTION).get(index)
        ));
        question.click();

        // Ждем видимый ответ
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElements(FAQ_ANSWER).get(index)
        ));

    }

    public boolean isFaqAnswerDisplayed(int index) {
        return driver.findElements(FAQ_ANSWER).get(index).isDisplayed();
    }

    public void clickTopOrderButton() {
        driver.findElement(TOP_ORDER_BUTTON).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(BOTTOM_ORDER_BUTTON).click();
    }

    public void scrollToFaqSection() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
    }
}
