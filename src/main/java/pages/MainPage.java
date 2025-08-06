package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    public static final By TOP_ORDER_BUTTON = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать'][1]");
    public static final By BOTTOM_ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[text()='Заказать']");
    public static final By FAQ_QUESTION = By.xpath(".//div[@data-accordion-component='AccordionItemButton']");
    public static final By FAQ_ANSWER = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']");
    public static final By COOKIE_BUTTON = By.id("rcc-confirm-button");


    public MainPage(WebDriver driver) {
        this.driver = driver;

    }
    public void clickCookieButton() {
        driver.findElement(COOKIE_BUTTON).click();
    }

    public List<WebElement> getFaqQuestions() {
        return driver.findElements(FAQ_QUESTION);
    }

    public void clickFaqQuestion(int index) {
        driver.findElements(FAQ_QUESTION).get(index).click();
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
}
