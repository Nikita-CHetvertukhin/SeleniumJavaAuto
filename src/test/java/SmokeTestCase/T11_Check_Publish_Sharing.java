package SmokeTestCase;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import methods.ConfigReader;
import methods.DriverConst;

public class T11_Check_Publish_Sharing {
private WebDriver driver;
	
	@BeforeSuite(groups = {"smoke", "speed2"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getIncognitoDriver();
    }
	
	@Test(groups = {"smoke", "speed2"})
	public void t11_0_UserAuthorization() {
		
		System.out.println("Запуск t11_0_UserAuthorization");
		Actions actions = new Actions(driver);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
		
		//Подтягиваем  данные из ConfigReader.java
    	String testUserName = ConfigReader.getProperty("testUserName");
    	String testUserPassword = ConfigReader.getProperty("testUserPassword");
    	String baseUrl = ConfigReader.getProperty("baseUrl");
    	
    	driver.get(baseUrl);
    	
    	WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(@class, 'btn primary push')]//span[contains(text(), 'Войти')]")
			));
    	
    	//Поиск кнопки логина, ввод некорректного логина 
        driver.findElement(By.cssSelector("input[placeholder='Логин или email']")).sendKeys(testUserName);
		
        //Поиск кнопки пароля, ввод корректного пароля
        driver.findElement(By.cssSelector("input[placeholder='Пароль']")).sendKeys(testUserPassword);
        
        //Нажатие на кнопку входа
        button.click();
        
        timing2();
        
        // Проверка отсутствия элемента с помощью findElements
        boolean isUserBtnPresent = driver.findElements(By.cssSelector(".divided.user.btn.default, .user.btn.default")).size() > 0;
        // Проверка, что элемент найден на странице
        Assert.assertTrue(isUserBtnPresent, "Элемент с классами 'divided user btn default' или 'user btn default' не найден на странице");
        
        //Создание папки для анкет из опубликованных шаблонов
        //Проверяем что рабочая область "Мои файлы" отображается и доступна
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//tr[@class='item folder' or @class='item file']")
        		));
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='create btn primary push']")));
        createButton.click();
        
        WebElement createFolder = createButton.findElement(
    	        By.xpath(".//td[@class='column' and @title='Новую папку']")
    	    );
        createFolder.click();
        timing();
        pressEnterKey();
        WebElement currentFolder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='item folder active']//div[@class='cell']")));
    	// Выполнить клик правой кнопкой мыши
    	actions.contextClick(currentFolder).perform();
    	
    	WebElement renameButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='Переименовать']")));
    	renameButton.click();
    	
    	WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@name,'input')]")));
    	rename.sendKeys("Smoke тестирование от " + formattedDate);
    	pressEnterKey();
    	timing();
    	pressEnterKey();
    }
	
	@Test(groups = {"smoke", "speed2"})
	public void t11_1_Check_Publish_User() {
	    System.out.println("Запуск t11_1_Check_Publish_User");
	    String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    Actions actions = new Actions(driver);
	    
	    // Клик по "Шаблоны"
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Шаблоны')]");
	    
	    // Поиск и клик по файлу в шаблонах
	    if (clickElement(wait, getXpathForFile(formattedDate, "copyCreateDotx Smoke тестирование от " + formattedDate))) {
	        timing();
	        pressEnterKey();
	        
	        // Поиск и клик по папке в выпадающем окне
	        if (clickElement(wait, getXpathForFolder(formattedDate, "Smoke тестирование от " + formattedDate))) {
	        	timing();
	        	pressEnterKey();
	        	timing1();
	            clickElement(wait, "//div[@class='selector window']//div[@class='footer']//a[@class='btn primary push']");
	            timing2();
	            
	            // Ожидание сохранения и проверка
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")));
	            clickElement(wait, "//div[@class = 'header']//a[@class = 'logo btn default']");
	            Assert.assertNotNull(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn default' and @title='Smoke тестирование от " + formattedDate + "']"))));
	            Assert.assertNotNull(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='copyCreateDotx Smoke тестирование от " + formattedDate + "']"))));
	        }
	    }
	    // Клик по "Шаблоны"
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Шаблоны')]");
	    //Снятие с публикации
        WebElement Templates = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath(getXpathForFile(formattedDate, "copyCreateDotx Smoke тестирование от " + formattedDate))
        		));
        actions.contextClick(Templates).perform();
        WebElement unPublish = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='text' and text()='Снять с публикации']")
        		));
        unPublish.click(); 
	}
	
	@Test(groups = {"smoke", "speed2"})
	public void t11_2_Check_Publish_Group() {
	    System.out.println("Запуск t11_2_Check_Publish_Group");
	    String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    Actions actions = new Actions(driver);
	    
	    // Клик по "Шаблоны"
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Шаблоны')]");
	    
	    // Поиск и клик по файлу в шаблонах
	    if (clickElement(wait, getXpathForFile(formattedDate, "copyCreateDotx Smoke тестирование от " + formattedDate))) {
	        timing();
	        pressEnterKey();
	        
	        // Поиск и клик по папке в выпадающем окне
	        if (clickElement(wait, getXpathForFolder(formattedDate, "Smoke тестирование от " + formattedDate))) {
	        	timing();
	        	pressEnterKey();
	        	timing1();
	            clickElement(wait, "//div[@class='selector window']//div[@class='footer']//a[@class='btn primary push']");
	            timing2();
	            
	            // Ожидание сохранения и проверка
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")));
	            clickElement(wait, "//div[@class = 'header']//a[@class = 'logo btn default']");
	            Assert.assertNotNull(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn default' and @title='Smoke тестирование от " + formattedDate + "']"))));
	            Assert.assertNotNull(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='copyCreateDotx Smoke тестирование от " + formattedDate + "']"))));
	        }
	    }
	    // Клик по "Шаблоны"
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Шаблоны')]");
	    //Снятие с публикации
        WebElement Templates = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath(getXpathForFile(formattedDate, "copyCreateDotx Smoke тестирование от " + formattedDate))
        		));
        actions.contextClick(Templates).perform();
        WebElement unPublish = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='text' and text()='Снять с публикации']")
        		));
        unPublish.click(); 
	}

	private boolean clickElement(WebDriverWait wait, String xpath) {
	    try {
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	        element.click();
	        return true;
	    } catch (Exception e) {
	        System.err.println("Не удалось кликнуть по элементу: " + xpath);
	        return false;
	    }
	}

	private String getXpathForFile(String date, String title) {
	    return "//div[@class='body']//div[@class='body']//div[contains(@class, 'tab columns') and not(contains(@class, 'inactive'))]//div[@class='scroller items']//span[@title='" + title + "']";
	}

	private String getXpathForFolder(String date, String title) {
	    return "//div[@class='selector window']//div[@class='scroller items']//span[@title='" + title + "']";
	}

	public void pressEnterKey() {
	    new Actions(driver).sendKeys(Keys.ENTER).perform();
	}
	
	private void timing() {
	    try {
	        Thread.sleep(200); // 200 миллисекунд задержки
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        System.err.println("Поток был прерван, операция не завершена");
	    } catch (TimeoutException e) {
	        System.err.println("Произошло истечение времени ожидания, операция не завершена");
	    }
	}
	
	private void timing1() {
		try {
            Thread.sleep(1000); // 1 секунда задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Поток был прерван, операция не завершена");
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена");
        }
	}
	
	private void timing2() {
		try {
            Thread.sleep(2000); // 2 секунды задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Поток был прерван, операция не завершена");
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена");
        }
	}
}
