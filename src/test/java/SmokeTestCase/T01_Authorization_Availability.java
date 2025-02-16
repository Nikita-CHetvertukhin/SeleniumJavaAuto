package SmokeTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.time.Duration;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import methods.ConfigReader;
import methods.DriverConst;

public class T01_Authorization_Availability {
	
	private WebDriver driver;

	 @BeforeSuite(groups = {"smoke", "speed4"})
	    public void setUp() {
	        // Инициализация браузера используя DriverConsy.java
	        driver = DriverConst.getDriver();
	    }

	@Test(groups = {"smoke", "speed4"})
	public void t1_1_Url_Availability() {
		
		//Получаем url сборки из config.properties
		String baseUrl = ConfigReader.getProperty("baseUrl");
		
		System.out.println("Запуск t1_1_Url_Availability");
		
		//Загрузка сайта
		driver.get(baseUrl);
		
		//Задаем лимит времени ожидания и ищем кнопку "Войти"
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
			    By.xpath("//a[contains(@class, 'btn primary push')]//span[contains(text(), 'Войти')]")
			));
		
		// Проверка, что кнопка отображается
		Assert.assertTrue(button.isDisplayed(), "Кнопка 'Войти' не отображается");
	}
	
	@Test(groups = {"smoke"})
	public void t1_2_Url_Refresh() {
	
		System.out.println("Запуск t1_2_Url_Refresh");
		
		//Перезагрузка страницы
		driver.navigate().refresh();
		
		//Задаем лимит времени ожидания
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(@class, 'btn primary push')]//span[contains(text(), 'Войти')]")
			));
				
		// Проверка, что кнопка отображается
		Assert.assertTrue(button.isDisplayed(), "Кнопка 'Войти' не отображается и не кликабельна");
	}
	
	@Test(groups = {"recover", "signup"})
	public void t1_3_Special_Button() {
	
		System.out.println("Запуск t1_3_Special_Button");
		
		//Задаем лимит времени ожидания
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		WebElement button1 = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(@class, 'btn default')]//span[contains(text(), 'Нет аккаунта? Зарегистрируйтесь')]")
			));
		WebElement button2 = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(@class, 'btn default')]//span[contains(text(), 'Забыл пароль')]")
			));
				
		// Проверка, что кнопки отображаются
		Assert.assertTrue(button1.isDisplayed(), "Кнопка 'Нет аккаунта? Зарегистрируйтесь' не отображается и не кликабельна");
		Assert.assertTrue(button2.isDisplayed(), "Кнопка 'Забыл пароль' не отображается и не кликабельна");
	}
	
	@AfterMethod
    public void checkTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            throw new SkipException("Тестирование остановлено из-за критической ошибки");
        }
    }
      
}
