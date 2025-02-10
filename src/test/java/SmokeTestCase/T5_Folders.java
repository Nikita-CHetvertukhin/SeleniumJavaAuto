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

import java.time.Duration;
import java.time.LocalDate;
import org.openqa.selenium.JavascriptExecutor;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.*;

import methods.DriverConst;

public class T5_Folders {
	
	private WebDriver driver;

    @BeforeSuite(groups = {"smoke", "speed"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }

    @Test(groups = {"smoke", "speed"})
	public void t5_1_CreateRenameFolders() {
    	
		System.out.println("Запуск t5_1_CreateFolders");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        //Проверяем что рабочая область "Мои файлы" отображается и доступна
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='body']//div[@class='body']//div[@class='scroller items']//tr[@class='item folder' or @class='item file']")));
        
        for (int i = 0; i < 2; i++) {
        	WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='create btn primary push']")));
            createButton.click();
            
            WebElement createFolder = createButton.findElement(
        	        By.xpath(".//td[@class='column' and @title='Новую папку']")
        	    );
            createFolder.click();
        }
        
        timing();
        pressEnterKey();
	}
    
    @Test(groups = {"smoke", "speed"})
    public void t5_2_RenameFolders() {
    	
    	System.out.println("Запуск t5_2_RenameFolders");
    	Actions actions = new Actions(driver);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	// Получить текущую дату
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
    	
    	for (int i = 0; i < 2; i++) {
        	WebElement currentFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='item folder active']//div[@class='cell']")));
        	// Выполнить клик правой кнопкой мыши
        	actions.contextClick(currentFolder).perform();
        	
        	WebElement renameButton = driver.findElement(By.xpath("//td[@title='Переименовать']"));
        	renameButton.click();
        	
        	WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@name,'input')]")));
        	rename.sendKeys(i + " Smoke тестирование от " + formattedDate);
        	pressEnterKey();
        	
        	if (i == 0) {
        		actions.sendKeys(Keys.ARROW_DOWN).perform(); // Отправляет нажатие клавиши вниз
        	}
        }
    }
    
    @Test(groups = {"smoke", "speed"})
    public void t5_3_MoveFolder() {
    	
    	System.out.println("Запуск t5_3_MoveFolder");
    	Actions actions = new Actions(driver);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
   
        WebElement currentFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='item folder active']//div[@class='cell']")));
        // Выполнить клик правой кнопкой мыши
        actions.contextClick(currentFolder).perform();
        	
        WebElement moveButton = driver.findElement(By.xpath("//td[@title='Переместить']"));
        moveButton.click();
        
        // Найти объект с классом "scroller items"
        try {
        	WebElement scroller = driver.findElement(By.xpath("//div[@class='selector window']//div[@class='scroller items']//tbody"));
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
    
    @Test(groups = {"smoke", "speed"})
    public void t5_4_RenameNull() {
    	
    	System.out.println("Запуск t5_4_RenameNull");
    	Actions actions = new Actions(driver);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
   
        WebElement currentFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='item folder active']//div[@class='cell']")));
        // Выполнить клик правой кнопкой мыши
        actions.contextClick(currentFolder).perform();
        	
        WebElement renameButton = driver.findElement(By.xpath("//td[@title='Переименовать']"));
        renameButton.click();
        
        WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@name,'input')]")));
        rename.sendKeys(Keys.BACK_SPACE);
    	pressEnterKey();
    	WebElement checkError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='error dzmessage show']")));
    	WebElement folderName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='item folder active']//div[@class='cell']//span[@class='text' and @title='1 Smoke тестирование от " + formattedDate + "']")));
    	Assert.assertNotNull(checkError, "Ожидаемая ошибка не найдена");
        Assert.assertNotNull(folderName, "Имя папки не возвращено");
        WebElement closeButton = driver.findElement(By.xpath("//a[@class='close-button btn default no-text']"));
        closeButton.click();
    }
    
    @Test(groups = {"smoke", "speed"})
    public void t5_5_RemoveFolder() {
    	
    	System.out.println("Запуск t5_5_RemoveFolder");
    	Actions actions = new Actions(driver);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
    	LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
   
        WebElement currentFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='item folder active']//div[@class='cell']")));
        // Выполнить клик правой кнопкой мыши
        actions.contextClick(currentFolder).perform();
        	
        WebElement removeButton = driver.findElement(By.xpath("//td[@title='Переместить в Корзину']"));
        removeButton.click();
        
      //Ищем и кликаем по "Корзина"
		WebElement buttonRecycleBin = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//a[contains(@class, 'recycle-bin tag btn default')]//span[contains(text(), 'Корзина')]")
    	    ));
		buttonRecycleBin.click();
		
    	WebElement folderRecycle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='item folder active']//div[@class='cell']//span[@class='text' and @title='1 Smoke тестирование от " + formattedDate + "']")));
    	Assert.assertNotNull(folderRecycle, "Папка не найдена в корзине");
    	// Выполнить клик правой кнопкой мыши
        actions.contextClick(folderRecycle).perform();
        WebElement deleteButton = driver.findElement(By.xpath("//td[@title='Удалить навсегда']"));
        deleteButton.click();
        WebElement approvedButton = driver.findElement(By.xpath("//a[@class='btn primary push']//span[@class='text' and text()='Удалить документы']"));
        approvedButton.click();
        
        try {
            Thread.sleep(1000); // 1 секунда задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
        
        Boolean isFolderInvisible = shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tr[@class='item folder active']//div[@class='cell']//span[@class='text' and @title='1 Smoke тестирование от " + formattedDate + "']")));
        Assert.assertTrue(isFolderInvisible, "Папка найдена после удаления");

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
