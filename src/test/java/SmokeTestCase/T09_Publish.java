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

public class T09_Publish {
	
private WebDriver driver;
	
	@BeforeSuite(groups = {"smoke"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }
	
	@Test(groups = {"smoke"})
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
	
	@Test(groups = {"smoke"})
	public void t9_2_GroupPublish() {
		
		System.out.println("Запуск t9_2_GroupPublish");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Actions actions = new Actions(driver);
        
        WebElement groupPublish = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='createDotx Smoke тестирование от " + formattedDate + "']")
        		));
        actions.doubleClick(groupPublish).perform();
        
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
    	String testGroup = ConfigReader.getProperty("testGroup");
        
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//input[contains(@name, 'input') and @placeholder='Введите ФИО, e-mail или название группы']")
        		));
        inputName.sendKeys(testGroup);
      
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
	
	@Test(groups = {"smoke"})
	public void t9_2_folderPublish() {
		
		System.out.println("Запуск t9_2_folderPublish");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Actions actions = new Actions(driver);
        
        //Ищем и кликаем по "Шаблоны"
		WebElement buttonTemplates = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Шаблоны')]")
    	    ));
		buttonTemplates.click();
		
		timing();
		
		WebElement createFolderTemplate = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//a[@class='btn primary push']//span[@class='text']")
    	    ));
		createFolderTemplate.click();
		
		timing();
		
		WebElement renameFolderTemplate = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//textarea[contains(@name, 'input') and @placeholder='Название']")
    	    ));
		renameFolderTemplate.sendKeys("FolderTemplates Smoke тестирование от " + formattedDate);
		
		pressEnterKey();
		
		//Ищем и кликаем по "Лого"
  		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
      	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
      	    ));
  		logoButton.click();
        
        WebElement folderPublish = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='dotxDownload Smoke тестирование от " + formattedDate + "']")
        		));
        actions.doubleClick(folderPublish).perform();
        
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
    	
    	WebElement inputFolder = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//input[contains(@name, 'input') and @placeholder='Шаблоны']")
        		));
    	inputFolder.sendKeys("FolderTemplates Smoke тестирование от " + formattedDate);
    	timing();
    	inputFolder.sendKeys(Keys.BACK_SPACE);
    	
    	WebElement folderIn = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[contains(@class, 'folder-select')]//div[contains(@class, 'dropdown-list')]/div[@class='scroller items']//td[@class='column first']/div[@class='cell']")
        		));
    	folderIn.click();
        
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
      
      //Ожидаем и кликаем по "Лого"
  		wait.until(ExpectedConditions.elementToBeClickable(
      	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
      	    ));
  		logoButton.click();
	}
	
	// Метод нажатия Enter
    public void pressEnterKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform(); // Отправляем клавишу Enter
    }
    
  //Перманентная задержка (связано с особенностями перестраивания DOM)
    private void timing() {
        try {
            Thread.sleep(500); // 500 милисекунд задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
    }

}
