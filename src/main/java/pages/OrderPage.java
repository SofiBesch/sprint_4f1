package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By numberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-placeholder");
    private final By blackCheckbox = By.id("black");
    private final By grayCheckbox = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By successModal = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
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

        // Формируем локатор для конкретного периода
        By periodLocator = By.xpath(String.format(
                ".//div[contains(@class, 'Dropdown-option') and text()='%s']",
                period
        ));

        // Ожидаем появления нужного варианта и скроллим
        WebElement periodOption = wait.until(ExpectedConditions.presenceOfElementLocated(periodLocator));
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                periodOption
        );

        wait.until(ExpectedConditions.elementToBeClickable(periodOption)).click();

        wait.until(ExpectedConditions.textToBePresentInElement(
                periodDropdown,
                period
        ));
    }
    private void setScooterColor(String color) {
        if (color.equals("black")) {
            driver.findElement(blackCheckbox).click();
        }else if (color.equals("grey")){
            driver.findElement(grayCheckbox).click();
        }

    }
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }
    public void clickConfirmButton(){
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }
    public boolean isSuccessModalDisplayed() {
        return driver.findElement(successModal).isDisplayed();
    }
}