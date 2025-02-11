package methods; //Код ниже позволяет путём import methods.DriverConst и строке driver = DriverConst.getDriver() выполнять тесты последовательно в одном окне браузера

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverConst {
    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
    
    public static WebDriver getIncognitoDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver incognitoDriver = new ChromeDriver(options);
        incognitoDriver.manage().window().maximize();
        return incognitoDriver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}