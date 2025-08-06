package faq;
import DriverFactory.DriverFactory;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;
import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FaqTest extends DriverFactory {
    @Rule
    public DriverFactory factory = new DriverFactory();
    @Test
    public void testFaqSection() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        mainPage.clickCookieButton();

        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight/2)");

        for (int i = 0; i < mainPage.getFaqQuestions().size(); i++) {
            WebElement question = mainPage.getFaqQuestions().get(i);

            wait.until(ExpectedConditions.elementToBeClickable(
                    mainPage.getFaqQuestions().get(i)
            )).click();
            wait.until(ExpectedConditions.visibilityOf(
                    driver.findElements(MainPage.FAQ_ANSWER).get(i)
            ));

            assertTrue("Ответ на вопрос " + (i+1) + " не отображается",
                    mainPage.isFaqAnswerDisplayed(i));
        }
    }
}
