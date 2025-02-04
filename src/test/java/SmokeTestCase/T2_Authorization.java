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

public class T2_Authorization {
	
	 private WebDriver driver;

	    @BeforeSuite(groups = {"smoke", "speed"})
	    public void setUp() {
	        // Получаем общий драйвер из DriverFactory
	        driver = DriverConst.getDriver();
	    }
	    
	    @Test(groups = {"smoke"})
	    public void t2_1_Incorrect_Credentials() {
	    	
	    	System.out.println("Запуск t2_1_Incorrect_Credentials");
	    	
	    	//Подтягиваем  данные из ConfigReader.java
	    	String username = ConfigReader.getProperty("username");
	    	String password = ConfigReader.getProperty("password");
	    	
	    	//Инициируем ожидание с лимитом в 10 секунд
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	
	    	//Поиск кнопки логина, ввод некорректного логина 
	        driver.findElement(By.cssSelector("input[placeholder='Логин или email']")).sendKeys(username + "123");
			
	        //Поиск кнопки пароля, ввод некорреткного пароля
	        driver.findElement(By.cssSelector("input[placeholder='Пароль']")).sendKeys(password + "123");
	        
	        //Нажатие на кнопку входа
	        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[contains(@class, 'btn primary push')]//span[contains(text(), 'Войти')]")
				));
	        button.click();
	        
	        // Вставляем задержку 1 секунда
	        try {
	            Thread.sleep(1000); // 1000 миллисекунд = 1 секунда
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        // Проверка отсутствия элемента с помощью findElements
	        boolean isUserBtnPresent = driver.findElements(By.cssSelector(".divided.user.btn.default, .user.btn.default")).size() > 0;
	        
	        // Проверка, что элемент не найден на странице
	        Assert.assertFalse(isUserBtnPresent, "Элемент с классами 'divided user btn default' или 'user btn default' найден на странице");
	    }
	    
	    @Test(groups = {"smoke"})
	    public void t2_2_Incorrect_Login() {
	    	
	    	System.out.println("Запуск t2_2_Incorrect_Login");
	    	
	    	//Перезагрузка страницы
			driver.navigate().refresh();
	    	
	    	//Подтягиваем  данные из ConfigReader.java
	    	String username = ConfigReader.getProperty("username");
	    	String password = ConfigReader.getProperty("password");
	    	
	    	//Инициируем ожидание с лимитом в 10 секунд
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	
	    	WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[contains(@class, 'btn primary push')]//span[contains(text(), 'Войти')]")
				));
	    	
	    	//Поиск кнопки логина, ввод некорректного логина 
	        driver.findElement(By.cssSelector("input[placeholder='Логин или email']")).sendKeys(username + "123");
			
	        //Поиск кнопки пароля, ввод корректного пароля
	        driver.findElement(By.cssSelector("input[placeholder='Пароль']")).sendKeys(password);
	        
	        //Нажатие на кнопку входа
	        button.click();
	        
	        // Вставляем задержку 1 секунда
	        try {
	            Thread.sleep(1000); // 1000 миллисекунд = 1 секунда
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        // Проверка отсутствия элемента с помощью findElements
	        boolean isUserBtnPresent = driver.findElements(By.cssSelector(".divided.user.btn.default, .user.btn.default")).size() > 0;
	        
	        // Проверка, что элемент не найден на странице
	        Assert.assertFalse(isUserBtnPresent, "Элемент с классами 'divided user btn default' или 'user btn default' найден на странице");
	    }
	    
	    @Test(groups = {"smoke"})
	    public void t2_3_Incorrect_Password() {
	    	
	    	System.out.println("Запуск t2_3_Incorrect_Password");
	    	
	    	//Перезагрузка страницы
			driver.navigate().refresh();
	    	
	    	//Подтягиваем  данные из ConfigReader.java
	    	String username = ConfigReader.getProperty("username");
	    	String password = ConfigReader.getProperty("password");
	    	
	    	//Инициируем ожидание с лимитом в 10 секунд
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	
	    	WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[contains(@class, 'btn primary push')]//span[contains(text(), 'Войти')]")
				));
	    	
	    	//Поиск кнопки логина, ввод некорректного логина 
	        driver.findElement(By.cssSelector("input[placeholder='Логин или email']")).sendKeys(username);
			
	        //Поиск кнопки пароля, ввод корректного пароля
	        driver.findElement(By.cssSelector("input[placeholder='Пароль']")).sendKeys(password + "123");
	        
	        //Нажатие на кнопку входа
	        button.click();
	        
	        // Вставляем задержку 1 секунда
	        try {
	            Thread.sleep(1000); // 1000 миллисекунд = 1 секунда
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        // Проверка отсутствия элемента с помощью findElements
	        boolean isUserBtnPresent = driver.findElements(
	        		By.cssSelector(".divided.user.btn.default, .user.btn.default")).size() > 0;
	        
	        // Проверка, что элемент не найден на странице
	        Assert.assertFalse(isUserBtnPresent, "Элемент с классами 'divided user btn default' или 'user btn default' найден на странице");
	    }
	    
	    @Test(groups = {"smoke", "speed"})
	    public void t2_4_Correct_Credentials() {
	    	
	    	System.out.println("Запуск t2_4_Correct_Credentials");
	    	
	    	//Перезагрузка страницы
			driver.navigate().refresh();
	    	
	    	//Подтягиваем  данные из ConfigReader.java
	    	String username = ConfigReader.getProperty("username");
	    	String password = ConfigReader.getProperty("password");
	    	
	    	//Инициируем ожидание с лимитом в 10 секунд
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	
	    	WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[contains(@class, 'btn primary push')]//span[contains(text(), 'Войти')]")
				));
	    	
	    	//Поиск кнопки логина, ввод некорректного логина 
	        driver.findElement(By.cssSelector("input[placeholder='Логин или email']")).sendKeys(username);
			
	        //Поиск кнопки пароля, ввод корректного пароля
	        driver.findElement(By.cssSelector("input[placeholder='Пароль']")).sendKeys(password);
	        
	        //Нажатие на кнопку входа
	        button.click();
	        
	        // Вставляем задержку 2 секунды
	        try {
	            Thread.sleep(2000); // 2000 миллисекунд = 2 секунды
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        // Проверка отсутствия элемента с помощью findElements
	        boolean isUserBtnPresent = driver.findElements(By.cssSelector("user.divided.btn.default, .divided.user.btn.default, .user.btn.default")).size() > 0;
	        
	        // Проверка, что элемент найден на странице
	        Assert.assertTrue(isUserBtnPresent, "Элемент с классами 'divided user btn default' или 'user btn default' не найден на странице");
	    }
	    
	    @AfterMethod
	    public void checkTestResult(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            throw new SkipException("Тестирование остановлено из-за критической ошибки");
	        }
	    }


}
