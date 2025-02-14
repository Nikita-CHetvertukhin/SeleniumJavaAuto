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
        
        // Вставляем задержку 2 секунды
        try {
            Thread.sleep(2000); // 2000 миллисекунд = 2 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
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
    }
	
	//@Test(groups = {"smoke", "speed2"})
	public void t11_1_Check_Publish() {
		
		System.out.println("Запуск t11_1_Check_Publish");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
      //Ищем и кликаем по "Шаблоны"
		WebElement buttonTemplates = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//a[contains(@class, 'tag btn default')]//span[contains(text(), 'Шаблоны')]")
    	    ));
		buttonTemplates.click();
		
		// Найти объект с классом "scroller items"
        try {
        	WebElement scrollerTemplates = wait.until(ExpectedConditions.visibilityOfElementLocated(
        			By.xpath("//div[@class='body']//div[@class='body']//div[contains(@class, 'tab columns') and not(contains(@class, 'inactive'))]//div[@class='scroller items']")
        			));
        	if (scrollerTemplates != null) {
                //System.out.println("Скроллер найден"); //Логирование

                try {
                    Thread.sleep(1000); // 1 секунда задержки
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
                    System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
                } catch (TimeoutException e) {
                    System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
                }

                // Ожидание элемента внутри найденного scroller
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@title='copyCreateDotx Smoke тестирование от " + formattedDate + "']")));
                WebElement targetFile1 = scrollerTemplates.findElement(By.xpath(".//span[@title='copyCreateDotx Smoke тестирование от " + formattedDate + "']"));
                if (targetFile1 != null) {
                    //System.out.println("Элемент targetFile1 найден"); //Логирование

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView(true);", targetFile1);

                    // Ожидание, чтобы элемент был видимым и доступным для клика
                    wait.until(ExpectedConditions.visibilityOf(targetFile1));
                    wait.until(ExpectedConditions.elementToBeClickable(targetFile1));
                    targetFile1.click();
                    timing();
                    pressEnterKey();
                    
                    //Далее ищем и прокручиваем до созданной в предыдущем шаге папке в выпадающем окне (ещё не готово)
                    
                    //Нажатие кнокпи "Переместить"
                    WebElement movePushButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='selector window']//div[@class='footer']//a[@class='btn primary push']")));
                    movePushButton.click();
                    //Открытие корневой папки в разделе "Мои файлы"
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='item folder active']//div[@class='cell']")));
                    pressEnterKey();
                    //Проверка попали ли мы в корневую папку и видно ли перемещенную
                    WebElement checkPath = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn default' and @title='0 Smoke тестирование от " + formattedDate + "']")));
                    WebElement checkFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='1 Smoke тестирование от " + formattedDate + "']")));
                    Assert.assertNotNull(checkPath, "Ожидаемый путь не найден");
                    Assert.assertNotNull(checkFolder, "Ожидаемая папка не найдена");
                } else {
                    //System.out.println("Элемент targetFolder не найден"); //Логирование
                }
            }
        } catch (NoSuchElementException e) {
            //System.out.println("Скроллер не найден"); //Логирование
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
