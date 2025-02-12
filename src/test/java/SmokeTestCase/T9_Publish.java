package SmokeTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.*;

import methods.ConfigReader;
import methods.DriverConst;

public class T9_Publish {
	
private WebDriver driver;
	
	@BeforeSuite(groups = {"smoke", "speed"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }
	
	@Test(groups = {"smoke", "speed"})
	public void t9_1_UserPublish() {
		
		System.out.println("Запуск t9_1_UserPublish");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Actions actions = new Actions(driver);
        
        WebElement userPublish = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='copyCreateDotx Smoke тестирование от " + formattedDate + "']")
        		));
        actions.doubleClick(userPublish).perform();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
        
        WebElement actionsButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='docflow-toolbar']/div[@class='docflow-actions']")
        		));
        actionsButton.click();
        
        WebElement publishButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='scroller items']//div[@class='cell']/div[@class='text' and text()='Опубликовать']")
        		));
        publishButton.click();
        
        //Подтягиваем  данные из ConfigReader.java
    	String testUserName = ConfigReader.getProperty("testUserName");
        
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//input[contains(@name, 'input') and @placeholder='Введите ФИО, e-mail или название группы']")
        		));
        inputName.sendKeys(testUserName);
      
        WebElement shareBy = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[contains(@class, 'share-select')]//div[contains(@class, 'dropdown-list')]/div[@class='scroller items']//td[@class='column last']/div[@class='cell']")
        		));
        shareBy.click();
        
        WebElement done = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class=\"footer\"]/a[@class='btn primary push']")
        		));
        done.click();
      
      //Ищем и кликаем по "Лого"
  		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
      	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
      	    ));
  		logoButton.click();
	}

}
