package SmokeTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.*;

import methods.DriverConst;

public class T4_menu_Work_Area {
		
	private WebDriver driver;

	    @BeforeSuite(groups = {"smoke", "speed"})
	    public void setUp() {
	        // Инициализируем драйвер, ожидающие объекты и другие переменные
	        this.driver = DriverConst.getDriver();
	    }
		
	    @Test(groups = {"smoke", "speed"})
		public void t4_1_Sidebar_Tabs() {
	    	
	    	System.out.println("Запуск t4_1_Sidebar_Tabs");
			
			//Инициируем ожидание с лимитом в 1 секунду
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
			
			//Ищем и кликаем по "Доступные мне"
			WebElement buttonShareToMe = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Доступные мне')]")
	    	    ));
			buttonShareToMe.click();
			// Проверяем наличие заголовка раздела "Доступные мне" с помощью assert
	        WebElement shareToMeCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[@class='folder btn default disabled']//span[text()='Доступные мне']")
	        ));
	        // Проверка результата теста
	        Assert.assertTrue(shareToMeCheck.isDisplayed(), "Элемент \"Доступные мне\"не отображен на странице.");
	        
	        //Ищем и кликаем по "Недавние"
			WebElement buttonRecent = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Недавние')]")
	    	    ));
			buttonRecent.click();
			// Проверяем наличие заголовка раздела "Недавние" с помощью assert
	        WebElement RecentCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[@class='folder btn default disabled']//span[text()='Недавние']")
	        ));
	        // Проверка результата теста
	        Assert.assertTrue(RecentCheck.isDisplayed(), "Элемент \"Недавние\"не отображен на странице.");
	        
	        //Ищем и кликаем по "Шаблоны"
			WebElement buttonTemplates = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Шаблоны')]")
	    	    ));
			buttonTemplates.click();
			// Проверяем наличие заголовка раздела "Шаблоны" с помощью assert
	        WebElement TemplatesCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[@class='folder btn default disabled']//span[text()='Шаблоны']")
	        ));
	        // Проверка результата теста
	        Assert.assertTrue(TemplatesCheck.isDisplayed(), "Элемент \"Шаблоны\"не отображен на странице.");
	        
	        //Ищем и кликаем по "Корзина"
			WebElement buttonRecycleBin = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'recycle-bin tag btn default')]//span[contains(text(), 'Корзина')]")
	    	    ));
			buttonRecycleBin.click();
			// Проверяем наличие заголовка раздела "Корзина" с помощью assert
	        WebElement RecycleBinCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[@class='folder btn default disabled']//span[text()='Корзина']")
	        ));
	        // Проверка результата теста
	        Assert.assertTrue(RecycleBinCheck.isDisplayed(), "Элемент \"Корзина\"не отображен на странице.");
	        
	        //Ищем и кликаем по "Мои файлы"
			WebElement buttonMyFiles = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Мои файлы')]")
	    	    ));
			buttonMyFiles.click();
			// Проверяем наличие заголовка раздела "Мои файлы" с помощью assert
	        WebElement MyFilesCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[@class='folder btn default disabled']//span[text()='Мои файлы']")
	        ));
	        // Проверка результата теста
	        Assert.assertTrue(MyFilesCheck.isDisplayed(), "Элемент \"Мои файлы\"не отображен на странице.");
	    }
	    
	    @Test(groups = {"sharing-drives"})
		public void t4_2_Sidebar_Tabs_Sharing_Drives() {
	    	
	    	System.out.println("Запуск t4_2_Sidebar_Tabs_Sharing_Drives");
			
			//Инициируем ожидание с лимитом в 1 секунду
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
			
			//Ищем и кликаем по "Общие диски"
			WebElement buttonSharingDrives = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'shared-drives-tag tag btn default')]//span[contains(text(), 'Общие диски')]")
	    	    ));
			buttonSharingDrives.click();
			// Проверяем наличие заголовка раздела "Общие диски" с помощью assert
	        WebElement SharingDrivesCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[@class='folder btn default disabled']//span[text()='Общие диски']")
	        ));
	        // Проверка результата теста
	        Assert.assertTrue(SharingDrivesCheck.isDisplayed(), "Элемент \"Общие диски\"не отображен на странице.");
	    }
	    
	    @Test(groups = {"AI"})
		public void t4_3_Sidebar_Tabs_AI() {
	    	
	    	System.out.println("Запуск t4_3_Sidebar_Tabs_AI");
			
			//Инициируем ожидание с лимитом в 1 секунду
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
			
			//Ищем и кликаем по "Ассистенты"
			WebElement buttonAssistants = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'assistant-set tag btn default')]//span[contains(text(), 'Ассистенты')]")
	    	    ));
			buttonAssistants.click();
			// Проверяем наличие заголовка раздела "Ассистенты" с помощью assert
	        WebElement AssistantsCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
	        		By.xpath("//div[@class='list-name' and text()='Ассистенты по отраслям права']")
	        ));
	        // Проверка результата теста
	        Assert.assertTrue(AssistantsCheck.isDisplayed(), "Элемент \"Ассистенты по отраслям права\"не отображен на странице.");
	        
	      //Ищем и кликаем по "Чат AI"
			WebElement buttonChatAi = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'chat-set tag btn default')]//span[contains(text(), 'Чат AI')]")
	    	    ));
			buttonChatAi.click();
			// Проверяем наличие инпута чата AI с помощью assert
			WebElement ChatAiCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
				    By.xpath("//textarea[@class='textarea control' and @placeholder='Задайте любой вопрос или опишите задачу. Для вставки абзаца нажмите Shift+Enter']")
				));
	        // Проверка результата теста
	        Assert.assertTrue(ChatAiCheck.isDisplayed(), "строка ввода промта чата AI не найдена на странице.");
	        
	        //Ищем и кликаем по "Мои файлы"
			WebElement buttonMyFiles = shortWait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Мои файлы')]")
	    	    ));
			buttonMyFiles.click();
			//Ищем и кликаем по кнопке "Начать" (для saas) или по кнопке "Сценарии" 
			// Найти все элементы, которые могут быть кнопками
			List<WebElement> allbuttons = shortWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
			    By.xpath("//a[contains(@class, 'scenarios-set tag btn default')]//span[contains(text(), 'Сценарии')] | //a[contains(@class, 'start btn default')]//span[contains(text(), 'Начать')]")
			));
			for (WebElement button : allbuttons) {
			    if (button.getText().contains("Сценарии")) {
			        button.click();
			        WebElement scenarioTitleCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
			            By.xpath("//div[@class='title' and text()='СЦЕНАРИИ']")
			        ));
			        Assert.assertTrue(scenarioTitleCheck.isDisplayed(), "Элемент \"Сценарии\"не отображен на странице.");
			    } else if (button.getText().contains("Начать")) {
			        button.click();
			        WebElement docCreationCheck = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
			            By.xpath("//div[contains(@class, 'cell')]//span[contains(text(), 'Создание документов')]")
			        ));
			        Assert.assertTrue(docCreationCheck.isDisplayed(), "Выпадающий список \"Создание документа\"не отображен на странице.");
			    }
			}
	    }
	    
	    @Test(groups = {"smoke", "speed"})
	    public void t4_4_Header_buttons() {
	    	
	    	System.out.println("Запуск t4_4_Header_buttons");
	    	
	    	// Найдите все элементы <a class="btn default"> внутри <div class="header-menu">
	        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='header-menu']//a[@class='btn default']"));

	        // Инициализация WebDriverWait
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Проход по всем найденным элементам
	        for (WebElement button : buttons) {
	            // Кликнуть по кнопке
	            wait.until(ExpectedConditions.elementToBeClickable(button)).click();

	            // Проверить наличие элемента <div class="scroller items">
	            List<WebElement> scrollerItems = driver.findElements(By.xpath("//div[@class='scroller items']"));
	            if (!scrollerItems.isEmpty()) {
	                // Найти все строки таблицы <tr id="id34" class="item menu" tabindex="-1">
	                List<WebElement> tableRows = driver.findElements(By.xpath("//tr[@class='item menu' and @tabindex='-1' or contains(@class, 'item menu active')]"));

	                // Проход по всем найденным строкам таблицы
	                for (WebElement row : tableRows) {
	                    // Ожидать, пока строка не станет интерактивной, и кликнуть по строке таблицы
	                    wait.until(ExpectedConditions.elementToBeClickable(row)).click();

	                    // Добавить небольшую задержку, чтобы дать классу время измениться
	                    try {
	                        Thread.sleep(500); // Задержка в 0.5 секунды
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }

	                    // Найти элемент <div class=" text"> на 3 уровне вложенности внутри строки таблицы
	                    WebElement uniqueTextDiv = row.findElement(By.xpath(".//div[contains(@class, ' text') and @title]"));
	                    // Извлечь и вывести уникальный текст
	                    String uniqueText = uniqueTextDiv.getText();
	                    System.out.println("Unique text: " + uniqueText);

	                    // Проверить, что класс изменился на "item menu active"
	                    String rowClass = row.getDomAttribute("class");
	                    if (rowClass.contains("active")) {
	                        System.out.println("Row is active: " + uniqueText);
	                    } else {
	                        System.out.println("Row is not active: " + uniqueText);
	                    }

	                    // Открыть выпадающий список заново, кликнув на кнопку
	                    wait.until(ExpectedConditions.elementToBeClickable(button)).click();
	                }
	            }
	        }
	        
	        System.out.println("Вывод в консоль вообще-то работает");
	        
	    }
}