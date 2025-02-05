package SmokeTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

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
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));

	        // Основной цикл для прохождения по всем кнопкам
	        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='header-menu']//a[@class='btn default']"));
	        for (int i = 0; i < buttons.size(); i++) {
	            WebElement button = buttons.get(i);
	            wait.until(ExpectedConditions.elementToBeClickable(button)).click();

	            List<WebElement> scrollerItems = driver.findElements(By.xpath("//div[@class='scroller items']"));
	            if (!scrollerItems.isEmpty()) {
	                List<WebElement> tableRows = driver.findElements(By.xpath("//tr[@class='item menu' or contains(@class, 'item menu active')]"));
	                for (WebElement row : tableRows) {
	                	hoverOverElement(row);
	                    if (additionalDropdownAppears()) {
	                    	System.out.println("additionalDropdownAppears: true");
	                    	// Добавьте здесь логику для обработки выпадающего списка
	                    	
	                    } else {
	                    	System.out.println("additionalDropdownAppears: false");
	                        wait.until(ExpectedConditions.elementToBeClickable(row)).click();
	                        handleRowActions(shortWait, row);
	                    }

	                    // Перезагрузить список кнопок перед повторным открытием выпадающего списка
	                    buttons = driver.findElements(By.xpath("//div[@class='header-menu']//a[@class='btn default']"));
	                    button = buttons.get(i);
	                    wait.until(ExpectedConditions.elementToBeClickable(button)).click();
	                }
	            } else {
	                System.out.println("Element <div class='scroller items'> not found.");
	            }

	            // Перезагрузить страницу после обработки каждой кнопки (из-за перестраивания DOM)
	            driver.navigate().refresh();
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header-menu']//a[@class='btn default']")));

	            // Обновить список кнопок после перезагрузки страницы
	            buttons = driver.findElements(By.xpath("//div[@class='header-menu']//a[@class='btn default']"));
	        }
	        System.out.println("Вывод в консоль вообще-то работает");
	    }
	    
	    //Перманентная задержка между кликами в 1 секунду (из-за перестраивания DOM)
	    private void handleRowActions(WebDriverWait shortWait, WebElement row) {
	        try {
	            Thread.sleep(1000); // Задержка в 1 секунду
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        handleCloseButton(shortWait);
	        handleTableRow(row);
	    }
	    
	    //метод наведения на строку выпадающего списка
	    private void hoverOverElement(WebElement element) {
	        Actions actions = new Actions(driver);
	        actions.moveToElement(element).perform();
	    }
	    
	    //Характеристики классов выпадающего списка второго уровня...
	    private boolean additionalDropdownAppears() {
	        try {
	            // Добавим небольшую задержку перед проверкой наличия выпадающего списка
	            Thread.sleep(1000); // Секунда задержки

	            WebElement dropdown = driver.findElement(By.xpath("//div[contains(@class, 'submenu')]"));
	            boolean isDisplayed = dropdown.isDisplayed();
	            System.out.println("submenu found: " + isDisplayed);
	            return isDisplayed;
	        } catch (NoSuchElementException e) {
	            System.out.println("submenu not found");
	            return false;
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }


	    
	    //Метод закрытия мешающих экранов в процессе прокликивания кнопок
	    private void handleCloseButton(WebDriverWait shortWait) {
	        try {
	            WebElement closeButton = shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@class='btn-tool btn default no-text' and descendant::i[@class='fa-close fa icon']]")));
	            if (closeButton != null) {
	                closeButton.click();
	                System.out.println("Кнопка 'Закрыть' найдена и кликнута.");
	            }
	        } catch (TimeoutException e) {
	            System.out.println("Кнопка 'Закрыть' не найдена.");
	        }
	    }
	    
	    //метод для обработки строк выпадающего списка первого уровня
	    private void handleTableRow(WebElement row) {
	        List<WebElement> cellDivs = row.findElements(By.xpath(".//div[contains(@class, 'cell')]"));
	        if (!cellDivs.isEmpty()) {
	            WebElement cellDiv = cellDivs.get(0);
	            List<WebElement> uniqueTextDivs = cellDiv.findElements(By.xpath(".//div[contains(@class, ' text') and @title]"));
	            if (!uniqueTextDivs.isEmpty()) {
	                WebElement uniqueTextDiv = uniqueTextDivs.get(0);
	                String titleAttribute = uniqueTextDiv.getDomAttribute("title");
	                System.out.println("Title attribute: " + titleAttribute);

	                String rowClass = row.getDomAttribute("class");
	                if (rowClass.contains("active")) {
	                    System.out.println("Row is active: " + titleAttribute);
	                } else {
	                    System.out.println("Row is not active: " + titleAttribute);
	                }
	            } else {
	                System.out.println("Element <div class='text' and @title> not found inside <div class='cell'>.");
	            }
	        } else {
	            System.out.println("Element <div class='cell'> not found inside row.");
	        }
	    }

}