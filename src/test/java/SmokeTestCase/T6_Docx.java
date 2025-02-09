package SmokeTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.*;

import methods.DriverConst;

public class T6_Docx {
	
	private WebDriver driver;

    @BeforeSuite(groups = {"smoke", "speed"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }

    @Test(groups = {"smoke", "speed"})
	public void t6_3_DownloadDocx() {
		
		System.out.println("Запуск t5_1_CreateFolders");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
      //Ищем и кликаем по "Лого"
		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
    	    ));
		logoButton.click();
		
		try {
            Thread.sleep(1000); // 1000 милисекунд задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
        
		try {
			WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='create btn primary push']")));
	        createButton.click();
	        
	        WebElement downloads = createButton.findElement(
	    	        By.xpath(".//td[@class='column' and @title='Загрузить файлы']")
	    	    );
	        downloads.click();

            // Использовать Robot для нажатия клавиши ESC
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            
            // Найти элемент для выбора файла
    		WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
    		
    		// Указать путь к файлу в папке resources вашего проекта
    		String filePath = System.getProperty("user.dir") + "/src/test/resources/test.docx";
    		fileInput.sendKeys(filePath);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
