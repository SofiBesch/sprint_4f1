package ru.praktikum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    // локаторы для первой страницы заказа
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By numberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    // локаторы для второй страницы заказа
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-placeholder");
    //вынесла локатор
    private final By periodOptionTemplate = By.xpath(".//div[contains(@class, 'Dropdown-option') and text()='%s']");

    private final By blackCheckbox = By.id("black");
    private final By grayCheckbox = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By orderStatusButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM')]");
    private final By successModal = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void fillFirstPage(String name, String surname, String address, String metro, String number){
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        setMetroStation(metro);
        driver.findElement(numberField).sendKeys(number);
    }
    private void setMetroStation(String station) {
        driver.findElement(metroField).click();
        driver.findElement(By.xpath(String.format(".//div[@class='Order_Text__2broi' and text()='%s']", station))).click();


    }
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillSecondPage(String date, String period, String color, String comment) {
        setDeliveryDate(date);
        setRentalPeriod(period);
        setScooterColor(color);
        driver.findElement(commentField).sendKeys(comment);
    }

    private void setDeliveryDate(String date) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        dateInput.click();
        dateInput.clear();

        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.attributeToBe(dateField, "value", date));

    }
    private void setRentalPeriod(String period) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement periodDropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodField));
        periodDropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'Dropdown-menu')]")
        ));

        // Сделала шаблон локатора из поля класса
        By periodLocator = By.xpath(String.format(periodOptionTemplate.toString().replace("By.xpath: ", ""), period));

        WebElement periodOption = wait.until(ExpectedConditions.presenceOfElementLocated(periodLocator));
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                periodOption
        );

        wait.until(ExpectedConditions.elementToBeClickable(periodOption)).click();
        wait.until(ExpectedConditions.textToBePresentInElement(periodDropdown,period));
    }
    private void setScooterColor(String color) {
        if (color.equals("black")) {
            driver.findElement(blackCheckbox).click();
        }else if (color.equals("grey")){
            driver.findElement(grayCheckbox).click();
        }

    }
    public void clickOrderButton(){
//        driver.findElement(orderButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }
    public void clickConfirmButton(){
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isOrderStatusButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderStatusButton));
        return driver.findElement(orderStatusButton).isDisplayed();
    }

    public void waitForOrderSuccess() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));
    }

    // Добавлен метод для скролла к элементу
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

}