package methods; //Код ниже позволяет путём import methods.DriverConst и строке driver = DriverConst.getDriver() выполнять тесты последовательно в одном окне браузера

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverConst {
    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}