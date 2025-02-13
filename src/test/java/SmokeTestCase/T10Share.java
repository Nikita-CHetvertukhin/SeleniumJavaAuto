package SmokeTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.*;

import methods.ConfigReader;
import methods.DriverConst;

public class T10Share {

private WebDriver driver;
	
	@BeforeSuite(groups = {"smoke", "speed"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }
	
	@Test(groups = {"smoke", "speed"})
	public void t10_1_ReaderUserShare() {
		
		System.out.println("Запуск t9_1_UserPublish");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Actions actions = new Actions(driver);
        
        WebElement findFirstFile = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='copyCreateDotx Smoke тестирование от " + formattedDate + "']")
        		));
		
        
	}

}
