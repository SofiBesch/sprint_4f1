package ru.praktikum.tests.order;

import ru.praktikum.driver.DriverFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum.pages.MainPage;
import ru.praktikum.pages.OrderPage;



@RunWith(Parameterized.class)
public class OrderTest extends DriverFactory {
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String number;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;

    @Rule
    public DriverFactory factory = new DriverFactory();
    public OrderTest(String name, String surname, String address, String metro, String number, String date, String period, String color, String comment){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.number = number;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;

    }
    @Parameterized.Parameters(name = "Тестовый набор: {0} {1}")
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Аэлита", "Гарина", "Подмосковная, 4", "Тушинская", "+79998887766", "06.08.2025",
                        "сутки", "black", "За час до приезда курьера позвонить"},
                {"Владимир", "Ленин", "Красная площадь, 9", "Театральная", "+79101917711", "26.10.2025",
                        "четверо суток", "grey", "не беспокоить по понедельникам и пятницам"},
        };
    }

    @Test
    public void testOrderFromTopButton() {
        makeOrder(true);
    }
    @Test
    public void testOrderFromBottomButton() {
        makeOrder(false);
    }

    private void makeOrder(boolean fromTopButton){
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.clickCookieButton();
        if (fromTopButton){
            mainPage.clickTopOrderButton();
        }else {
            orderPage.scrollToElement(driver.findElement(MainPage.BOTTOM_ORDER_BUTTON));
            mainPage.clickBottomOrderButton();
        }


        orderPage.fillFirstPage(name, surname, address, metro, number);
        orderPage.clickNextButton();


        orderPage.fillSecondPage(date, period, color, comment);

        orderPage.clickOrderButton();
        orderPage.clickConfirmButton();


        orderPage.waitForOrderSuccess();
        Assert.assertTrue(orderPage.isOrderStatusButtonDisplayed());

    }

}
