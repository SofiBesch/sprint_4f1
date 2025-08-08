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
    //Массив с ожидаемыми ответами
    private static final String[] EXPECTED_ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

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

        //Проверка текста ответа
        String actualAnswer = mainPage.getFaqAnswerText(questionIndex);
        assertEquals("Неверный ответ на вопрос " + (questionIndex + 1),
                EXPECTED_ANSWERS[questionIndex], actualAnswer);
    }
}
