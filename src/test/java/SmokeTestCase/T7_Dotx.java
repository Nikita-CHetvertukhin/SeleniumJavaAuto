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
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.*;

import methods.DriverConst;

public class T7_Dotx {

	private WebDriver driver;

    @BeforeSuite(groups = {"smoke", "speed"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }
	
    @Test(groups = {"smoke", "speed"})
	public void t7_1_CreateDotx() {
		
		System.out.println("Запуск t7_1_CreateDotx");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Actions actions = new Actions(driver);
        
        //Кнопка создать
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//a[@class='create btn primary push']")
        		));
        createButton.click();
        
        //Создать интерактивный шаблон
        WebElement createDotx = createButton.findElement(
    	        By.xpath(".//td[@class='column' and @title='Интерактивный шаблон']")
    	    );
        createDotx.click();
        
        //Ожидание открытия, Клик по переменной типа текст и присваивание имени
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
        WebElement string = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//button[@class='button' and @title='Текст']")
        		));
        string.click();
        WebElement stringName = wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//div[@class='scroller items']//div[contains(@class, 'required scrollable label-none control-group textarea')]//textarea[contains(@name, 'input') and text()='Переменная']")
        		));
        stringName.sendKeys("SmokeText01");
        
        //Поиск первого абзаца в редакторе, клик ПКМ-Привязать к схеме, и двойной клик по новосозданной переменной
        WebElement editor = wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//div[@class='page']/div[@class='line']")
        		));
        actions.contextClick(editor).perform();
        WebElement addToSchem = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='scroller items']//tr[contains(@class, 'item menu')]/td[@title='Привязать к схеме (ctrl+D)']")
        		));
        addToSchem.click();
        WebElement stringVariable = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='scroller items']//div[@class='cell']/span[@class=' text' and text()='SmokeText01']")
        		));
        actions.doubleClick(stringVariable).perform();
        
        WebElement renameDotx = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='divided main-action']/div[contains(@class, 'name')]/div[@class='box']//input")
        		));
        renameDotx.clear();
        renameDotx.sendKeys("createDotx Smoke тестирование от " + formattedDate);
        pressEnterKey();
        
        try {
            Thread.sleep(1000); // 1000 милисекунд задержки т.к. сейчас соранение каждую секунду, потом может измениться
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
        
      //Ищем и кликаем по "Лого"
      		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
          	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
          	    ));
      		logoButton.click();
	}
    
    @Test(groups = {"smoke", "speed"})
	public void t7_2_DownloadingDotx() {
		
		System.out.println("Запуск t7_2_DownloadingDotx");
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
    		String filePath = System.getProperty("user.dir") + "/src/test/resources/dotxDownload.dotx";
    		fileInput.sendKeys(filePath);
    		WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(
    				By.xpath("//textarea[contains(@name,'input')]")
    				));
        	rename.sendKeys("dotxDownload Smoke тестирование от " + formattedDate);
        	pressEnterKey();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    @Test(groups = {"smoke", "speed"})
	public void t7_3_CheckDotx() {
    	
    	System.out.println("Запуск t7_3_CheckDotx");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
    	
    	// Перезагружаем страницу
        driver.navigate().refresh();
        
        WebElement checkDotx = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='createDotx Smoke тестирование от " + formattedDate + "']")
        		));
        WebElement checkDownloadDotx = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='dotxDownload Smoke тестирование от " + formattedDate + "']")
        		));
        Assert.assertNotNull(checkDotx, "Созданный вручную dotx не найден");
        Assert.assertNotNull(checkDownloadDotx, "Загруженный dotx не найден");
    }
    
    @Test(groups = {"smoke", "speed"})
	public void t7_4_DownloadDotx() {
    	System.out.println("Запуск t7_4_DownloadDotx");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        
        WebElement checkDownloadDotx = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='dotxDownload Smoke тестирование от " + formattedDate + "']")
        		));
        checkDownloadDotx.click();
        
        pressEnterKey();
        
        //Ожидание открытия, клик по кнопке "скачать" и выбор "скачать dotx"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//a[@title='Скачать']")
        		));
        downloadButton.click();
        WebElement downloadDotxButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//td[@class='column' and @title='Скачать dotx']")
        		));
        downloadDotxButton.click();

        // Получение пути к директории с загрузками
        String downloadDir = System.getProperty("user.home") + "/Downloads";
        try {
            Thread.sleep(3000); // 3000 милисекунд задержки т.к. сейчас соранение каждую секунду, потом может измениться
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
        File downloadedFile = getLatestFileFromDir(downloadDir);

        if (downloadedFile != null) {
            // Проверка формата файла
            String fileName = downloadedFile.getName();
            if (fileName.endsWith(".dotx")) {
                // Проверка размера файла
                if (downloadedFile.length() > 1024) {
                    System.out.println("Файл " + fileName + " успешно скачан, формат и размер корректны.");
                } else {
                    System.out.println("Размер файла меньше 1 Кб.");
                }
            } else {
                System.out.println("Неверный формат файла.");
            }
        } else {
            System.out.println("Файл не найден.");
        }  
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
    
    private static File getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            File latestFile = files[0];
            for (File file : files) {
                if (file.lastModified() > latestFile.lastModified()) {
                    latestFile = file;
                }
            }
            return latestFile;
        }
        return null;
    }
}
