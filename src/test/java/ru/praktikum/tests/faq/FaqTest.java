package ru.praktikum.tests.faq;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.driver.DriverFactory;
import org.junit.Rule;
import org.junit.Test;
import ru.praktikum.pages.MainPage;
import static org.junit.Assert.*;



@RunWith(Parameterized.class)
public class FaqTest extends DriverFactory {
    @Rule
    public DriverFactory factory = new DriverFactory();

    private final int questionIndex;

    public FaqTest(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Parameterized.Parameters(name = "Вопрос FAQ #{0}")
    public static Object[][] getFaqData() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7}
        };
    }

    @Test
    public void testFaqSection() {

        MainPage mainPage = new MainPage(factory.getDriver());
        mainPage.clickCookieButton();
        mainPage.scrollToFaqSection();
        mainPage.clickFaqQuestion(questionIndex);

        assertTrue("Ответ на вопрос " + (questionIndex+1) + " не отображается",mainPage.isFaqAnswerDisplayed(questionIndex));

    }
}
