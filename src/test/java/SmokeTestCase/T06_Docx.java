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
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Dimension;

import org.testng.Assert;
import org.testng.annotations.*;

import methods.DriverConst;

public class T06_Docx {
	
	private WebDriver driver;

    @BeforeSuite(groups = {"smoke"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }
    
    @Test(groups = {"smoke"})
	public void t6_1_CreateDocx() {
		
		System.out.println("Запуск t6_1_CreateDocx");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Actions actions = new Actions(driver);
        
      //Ищем и кликаем по "Лого"
		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
    	    ));
		logoButton.click();
		
		WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='create btn primary push']")));
        createButton.click();
        
        WebElement createDocx = createButton.findElement(
    	        By.xpath(".//td[@class='column' and @title='Новый документ']")
    	    );
        createDocx.click();
        
        timing();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")));
      	logoButton.click();
        
      	WebElement currentDocx = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class, 'item') and (contains(@class, 'active file') or contains(@class, 'file active'))]//div[@class='cell']")));
    	// Выполнить клик правой кнопкой мыши
    	actions.contextClick(currentDocx).perform();
    	
    	WebElement renameButton = driver.findElement(By.xpath("//td[@title='Переименовать']"));
    	renameButton.click();
    	
    	WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@name,'input')]")));
    	rename.sendKeys("Docx Smoke тестирование от " + formattedDate);
    	pressEnterKey();
    }

    @Test(groups = {"smoke"})
	public void t6_2_DownloadingDocx() {
		
		System.out.println("Запуск t6_2_DownloadingDocx");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
		
        timing();
        
		try {
			WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='create btn primary push']")));
	        createButton.click();
	        
	        //Здесь хитро, блок нужен, чтобы в DOM появился класс input с type='file', в который и будем загружать документ
	        WebElement downloads = createButton.findElement(
	    	        By.xpath(".//td[@class='column' and @title='Загрузить файлы']")
	    	    );
	        downloads.click();

	        timing();
	        
            // Использовать Robot для нажатия клавиши ESC
            Robot robot = new Robot();
            
         // Получение размеров экрана
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            
         // Перемещение курсора в центр экрана и выполнение клика
            robot.mouseMove(screenWidth / 2, screenHeight / 2);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            
            // Найти элемент для выбора файла
    		WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
    		
    		// Указать путь к файлу
    		String filePath = System.getProperty("user.dir") + "/src/test/resources/doczillaStyles.docx";
    		fileInput.sendKeys(filePath);
    		WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@name,'input')]")));
        	rename.sendKeys("DoczillaStyles Smoke тестирование от " + formattedDate);
        	pressEnterKey();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    @Test(groups = {"smoke"})
   	public void t6_3_CheckDocx() {
       	
       	System.out.println("Запуск t6_3_CheckDocx");
       	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
       	// Перезагружаем страницу
           driver.navigate().refresh();
           
        WebElement checkDocx = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='Docx Smoke тестирование от " + formattedDate + "']")));
        WebElement checkDownloadDocx = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='DoczillaStyles Smoke тестирование от " + formattedDate + "']")));
        Assert.assertNotNull(checkDocx, "Созданный вручную docx не найден");
        Assert.assertNotNull(checkDownloadDocx, "Загруженный docx не найден");
       	
       }
    
 // Метод нажатия Enter
    public void pressEnterKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform(); // Отправляем клавишу Enter
    }
    
    private void timing() {
        try {
            Thread.sleep(400); // 400 милисекунд задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
    }

}
