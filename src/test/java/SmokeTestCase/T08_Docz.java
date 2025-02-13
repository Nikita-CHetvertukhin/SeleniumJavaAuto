package SmokeTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.*;

import methods.DriverConst;

public class T08_Docz {
	
	private WebDriver driver;
	
	@BeforeSuite(groups = {"smoke", "speed"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }

	@Test(groups = {"smoke", "speed"})
	public void t8_1_CreateDocz() {
		
		System.out.println("Запуск t8_1_CreateDocz");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        
        WebElement actionsButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class='docflow-toolbar']/div[@class='docflow-actions']")
        		));
        actionsButton.click();
        
        WebElement createDoczButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//div[@class=\"scroller items\"]//div[@class='cell']/div[@class='text' and text()='Создать документ']")
        		));
        createDoczButton.click();
        
     // Найти объект с классом "scroller items"
        try {
        	WebElement scroller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector window']//div[@class='scroller items']//tbody")));
        	if (scroller != null) {
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
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@title='0 Smoke тестирование от " + formattedDate + "']")));
                WebElement targetFolder = scroller.findElement(By.xpath(".//span[@title='0 Smoke тестирование от " + formattedDate + "']"));
                if (targetFolder != null) {
                    //System.out.println("Элемент targetFolder найден"); //Логирование

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView(true);", targetFolder);

                    // Ожидание, чтобы элемент был видимым и доступным для клика
                    wait.until(ExpectedConditions.visibilityOf(targetFolder));
                    wait.until(ExpectedConditions.elementToBeClickable(targetFolder));

                    // Открытие путь в окне перемещения
                    targetFolder.click();
                    timing();
                    pressEnterKey();
                    
                    WebElement renameArea = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name, 'input') and @placeholder='Новое название']")));
                    renameArea.clear();
                    renameArea.sendKeys("createDocz Smoke тестирование от " + formattedDate);
                    
                    //Нажатие кнокпи "Сохранить здесь"
                    WebElement savePushButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='selector window']//div[@class='footer']//a[@class='btn primary push']")));
                    savePushButton.click();
                    
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                    		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
                    		));
                  //Ищем и кликаем по "Лого"
              		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
                  	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
                  	    ));
              		logoButton.click();
                    
                    
                    //Проверка попали ли мы в корневую папку и видно ли перемещенную
              		WebElement checkDocz = wait.until(ExpectedConditions.elementToBeClickable(
              				By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='createDocz Smoke тестирование от " + formattedDate + "']")
              				));
              		checkDocz.click();
                    Assert.assertNotNull(checkDocz, "Анкета не найдена");
                } else {
                    //System.out.println("Элемент targetFolder не найден"); //Логирование
                }
            }
        } catch (NoSuchElementException e) {
            //System.out.println("Скроллер не найден"); //Логирование
        }
	}
	
	@Test(groups = {"smoke", "speed"})
	public void t8_2_DownloadingDocz() {
		
		System.out.println("Запуск t8_2_DownloadingDocz");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        pressEnterKey();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
        
        WebElement inputVariable = wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//div[@class='scroller items']//span[@class=' text' and @title='SmokeText01']//div[@class='box']/textarea[contains(@name, 'input')]")
        		));
        inputVariable.sendKeys("Test");
        
        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//a[@title='Скачать']")
        		));
        downloadButton.click();
        WebElement downloadDotxButton = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//td[@class='column' and @title='Скачать docx']")
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
            if (fileName.endsWith(".docx")) {
                // Проверка размера файла
                if (downloadedFile.length() > 1024) {
                    //System.out.println("Файл " + fileName + " успешно скачан, формат и размер корректны."); //Логирование
                } else {
                    System.out.println("Размер файла меньше 1 Кб.");
                }
            } else {
                System.out.println("Неверный формат файла.");
            }
        } else {
            System.out.println("Файл не найден.");
        }
        
        WebElement downloadButton2 = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//a[@title='Скачать']")
        		));
        downloadButton2.click();
        WebElement downloadDotxButton2 = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//td[@class='column' and @title='Скачать pdf']")
        		));
        downloadDotxButton2.click();
        
     // Получение пути к директории с загрузками
        try {
            Thread.sleep(6000); // 6000 милисекунд задержки т.к. сейчас соранение каждую секунду, потом может измениться
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
        File downloadedFile2 = getLatestFileFromDir(downloadDir);

        if (downloadedFile2 != null) {
            // Проверка формата файла
            String fileName = downloadedFile2.getName();
            if (fileName.endsWith(".pdf")) {
                // Проверка размера файла
                if (downloadedFile2.length() > 1024) {
                    //System.out.println("Файл " + fileName + " успешно скачан, формат и размер корректны."); //Логирование
                } else {
                    System.out.println("Размер файла меньше 1 Кб.");
                }
            } else {
                System.out.println("Неверный формат файла.");
            }
        } else {
            System.out.println("Файл не найден.");
        }
        
      //Ищем и кликаем по "Лого"
  		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
      	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
      	    ));
  		logoButton.click();
        
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
