package BaseTest;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class DriverFactory extends ExternalResource {


    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;
    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            startFireFox();
        } else {
            startChrome();
        }

    }

    private void startChrome() {
        //System.setProperty("webdriver.chrome.driver", "D:\\draivervonychka\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    private void startFireFox() {
        //System.setProperty("webdriver.gecko.driver", "D:\\draivervonychka\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Override
    protected void before() {
        initDriver();
    }
    @Override
    protected void after(){
        driver.quit();
    }
}
