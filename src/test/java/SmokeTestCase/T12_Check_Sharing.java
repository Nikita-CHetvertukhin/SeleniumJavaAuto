package SmokeTestCase;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import methods.DriverConst;

public class T12_Check_Sharing {
	
	private WebDriver driver;

	@BeforeSuite(groups = {"smoke"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getIncognitoDriver();
    }
	
	@Test(groups = {"smoke"})
	public void t12_1_Check_Reader_User() {
	    System.out.println("Запуск t12_1_Check_Reader_User");
	    String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // Клик по "Доступные мне"
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Доступные мне')]");
	    
	    // Поиск и открытие файла в Доступных мне
	    if (clickElement(wait, getXpathForFile(formattedDate, "copyCreateDotx Smoke тестирование от " + formattedDate))) {
	        timing();
	        pressEnterKey();
	        
	        timing2();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")));
            //Далее код с проверкой уровня доступа "Читатель" до 4.0 на паузе
	    }
	    clickElement(wait, "//div[@class = 'header']//a[@class = 'logo btn default']");
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Доступные мне')]");  
        timing1();
	}
	
	@Test(groups = {"smoke"})
	public void t12_2_Check_Reader_Group() {
	    System.out.println("Запуск t12_2_Check_Reader_Group");
	    String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // Поиск и открытие файла в Доступных мне
	    if (clickElement(wait, getXpathForFile(formattedDate, "dotxDownload Smoke тестирование от " + formattedDate))) {
	        timing();
	        pressEnterKey();
	        
	        timing2();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")));
            //Далее код с проверкой уровня доступа "Читатель" для группы до 4.0 на паузе
	    }
	    clickElement(wait, "//div[@class = 'header']//a[@class = 'logo btn default']");
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Доступные мне')]");
        timing1();
	}
	
	@Test(groups = {"smoke"})
	public void t12_3_Check_Comment_User() {
	    System.out.println("Запуск t12_3_Check_Comment_User");
	    String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // Поиск и открытие файла в Доступных мне
	    if (clickElement(wait, getXpathForFile(formattedDate, "DoczillaStyles Smoke тестирование от " + formattedDate))) {
	        timing();
	        pressEnterKey();
	        
	        timing2();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")));
            //Далее код с проверкой уровня доступа "Комментатор" до 4.0 на паузе
	    }
	    clickElement(wait, "//div[@class = 'header']//a[@class = 'logo btn default']");
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Доступные мне')]");
        timing1();
	}
	
	@Test(groups = {"smoke"})
	public void t12_4_Check_Editor_User() {
	    System.out.println("Запуск t12_4_Check_Editor_User");
	    String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // Поиск и открытие файла в Доступных мне
	    if (clickElement(wait, getXpathForFile(formattedDate, "createDotx Smoke тестирование от " + formattedDate))) {
	        timing();
	        pressEnterKey();
	        
	        timing2();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")));
            //Далее код с проверкой уровня доступа "Редактор" до 4.0 на паузе
	    }
	    clickElement(wait, "//div[@class = 'header']//a[@class = 'logo btn default']");
	    clickElement(wait, "//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Доступные мне')]");
        timing1();
	}
	
	@Test(groups = {"smoke"})
	public void t12_5_Check_Delete_Sharing() {
	    System.out.println("Запуск t12_5_Check_Delete_Sharing");
	    String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        
	 // Поиск элементов по заданному XPath
	    boolean isElementFound = !driver.findElements(By.xpath("//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='Docx Smoke тестирование от " + formattedDate + "']")).isEmpty();

	    assert !isElementFound : "Объект найден, хотя доступ к нему был закрыт";
	    
	 // Завершение работы с драйвером
	    driver.quit();
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
