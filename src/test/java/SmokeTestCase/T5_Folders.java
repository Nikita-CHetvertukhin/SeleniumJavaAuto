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
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import methods.DriverConst;

public class T5_Folders {
	
	private WebDriver driver;

    @BeforeSuite(groups = {"smoke", "speed"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }

    @Test(groups = {"smoke", "speed"})
	public void t5_1_CreateRenameFolders() {
		
		System.out.println("Запуск t5_1_CreateFolders");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        
        for (int i = 0; i < 2; i++) {
        	WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='create btn primary push']")));
            createButton.click();
            
            WebElement createFolder = createButton.findElement(
        	        By.xpath(".//td[@class='column' and @title='Новую папку']")
        	    );
            createFolder.click();
        }
        
        timing();
        pressEnterKey();
	}
    
    @Test(groups = {"smoke", "speed"})
    public void t5_2_RenameFolders() {
    	
    	Actions actions = new Actions(driver);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	// Получить текущую дату
        LocalDate currentDate = LocalDate.now();
    	
    	for (int i = 0; i < 2; i++) {
        	WebElement currentFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='item folder active']//div[@class='cell']")));
        	// Выполнить клик правой кнопкой мыши
        	actions.contextClick(currentFolder).perform();
        	
        	WebElement renameButton = driver.findElement(By.xpath("//td[@title='Переименовать']"));
        	renameButton.click();
        	
        	WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@name,'input')]")));
        	rename.sendKeys(i + " Smoke тестирование от " + currentDate.toString());
        	pressEnterKey();
        	
        	if (i == 0) {
        		actions.sendKeys(Keys.ARROW_DOWN).perform(); // Отправляет нажатие клавиши вниз
        	}
        }
    }
    
  //Перманентная задержка (связано с особенностями перестраивания DOM)
    private void timing() {
        try {
            Thread.sleep(200); // 200 милисекунд задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
    }
    
    // Метод нажатия Enter
    public void pressEnterKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform(); // Отправляем клавишу Enter
    }


}
