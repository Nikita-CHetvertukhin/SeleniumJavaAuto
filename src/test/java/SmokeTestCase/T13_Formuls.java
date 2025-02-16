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
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.*;
import java.awt.event.InputEvent;

import org.testng.Assert;
import org.testng.annotations.*;

import methods.DriverConst;

public class T13_Formuls {
	
	private WebDriver driver;

    @BeforeSuite(groups = {"smoke", "speed4"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }
    
    @Test(groups = {"smoke", "speed4"})
	public void T13_1_Formuls_String() {
		
    	System.out.println("Запуск T13_1_Formuls_String");
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
    		String filePath = System.getProperty("user.dir") + "/src/test/resources/String.dotx";
    		fileInput.sendKeys(filePath);
    		WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(
    				By.xpath("//textarea[contains(@name,'input')]")
    				));
        	rename.sendKeys("StringFormuls от " + formattedDate);
        	pressEnterKey();
        } catch (AWTException e) {
            e.printStackTrace();
        }
		
		timing();
		pressEnterKey();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
		
		WebElement Questinnary = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='questionnaire tag btn default']")));
		Questinnary.click();
		WebElement id1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@title='ID1']//textarea[contains(@name,'input')]")
				));
		id1.sendKeys("Godzilla");
		WebElement id2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@title='ID2']//textarea[contains(@name,'input')]")
				));
		id2.sendKeys("Doc");
		
		timing1();
		//Далее идёт обработка результатов формул (пауза до 4.0)
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
		//Ищем и кликаем по "Лого"
  		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
      	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
      	    ));
  		logoButton.click();
	}
    
    @Test(groups = {"smoke", "speed4"})
	public void T13_2_Formuls_Replicator() {
		
    	System.out.println("Запуск T13_2_Formuls_Replicator");
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
    		String filePath = System.getProperty("user.dir") + "/src/test/resources/Replicator.dotx";
    		fileInput.sendKeys(filePath);
    		WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(
    				By.xpath("//textarea[contains(@name,'input')]")
    				));
        	rename.sendKeys("ReplicatorFormuls от " + formattedDate);
        	pressEnterKey();
        } catch (AWTException e) {
            e.printStackTrace();
        }
		
		timing();
		pressEnterKey();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
		
		WebElement Questinnary = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@class='questionnaire tag btn default']")
				));
		Questinnary.click();
		WebElement vanish = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@class='btn default no-text' and @title='Сбросить значения']")
				));
		vanish.click();
		WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='footer']/a[@class='btn primary push']")
				));
		ok.click();
		
		WebElement openReplicator = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class=' text' and @title='Открыть мультипликатор для внесения изменений']")
				));
		openReplicator.click();
		
		WebElement addReplica1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class=' text' and @title='Мультипликатор 1 1']//div[@class='box']//a[@class='replicate btn default no-text']")
				));
		addReplica1.click();
		
		WebElement product1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class=' text' and @title='Мультипликатор 1 1']//textarea[contains(@name,'input')]")
				));
		product1.sendKeys("Бананы");
		
		WebElement cash1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class=' text' and @title='Мультипликатор 1 1']//input[contains(@name,'input')]")
				));
		cash1.sendKeys("100");
		
		WebElement product2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class=' text' and @title='Мультипликатор 1 2']//textarea[contains(@name,'input')]")
				));
		product2.sendKeys("Яблоки");
		
		WebElement cash2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class=' text' and @title='Мультипликатор 1 2']//input[contains(@name,'input')]")
				));
		cash2.sendKeys("200");
		
		// Найти объект с классом "scroller items"
        try {
        	WebElement scroller = wait.until(ExpectedConditions.visibilityOfElementLocated(
        			By.xpath("//div[@class='body']//div[@class='body']//div[@class='right tabs ']//div[@class='questionnaire tab auto-fit list']/div[@class='scroller items']")
        			));
        	if (scroller != null) {
                //System.out.println("Скроллер найден"); //Логирование

                timing1();

                //Ожидание элемента внутри найденного scroller
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@title='Мультипликатор 2 1']//div[@class='box']//a[@class='replicate btn default no-text']")));
                WebElement addReplica2 = scroller.findElement(By.xpath(".//span[@title='Мультипликатор 2 1']//div[@class='box']//a[@class='replicate btn default no-text']"));
                if (addReplica2 != null) {
                    //System.out.println("Элемент targetFolder найден"); //Логирование

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView(true);", addReplica2);

                    // Ожидание, чтобы элемент был видимым и доступным для клика
                    wait.until(ExpectedConditions.visibilityOf(addReplica2));
                    wait.until(ExpectedConditions.elementToBeClickable(addReplica2));

                    // Открытие путь в окне перемещения
                    addReplica2.click();
                    timing();
                } else {
                    //System.out.println("Элемент targetFolder не найден"); //Логирование
                }
            }
        } catch (NoSuchElementException e) {
            //System.out.println("Скроллер не найден"); //Логирование
        }
        
        WebElement Condition2_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class=' text' and @title='Мультипликатор 2 1']//span[@class=' text' and @title='Условие']")
				));
        Condition2_1.click();
        
        WebElement Condition2_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class=' text' and @title='Мультипликатор 2 2']//span[@class=' text' and @title='Условие']")
				));
        Condition2_2.click();
        
        timing1();
        
        Condition2_1.click();
		
		timing1();
		//Далее идёт обработка результатов формул (пауза до 4.0)
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
		//Ищем и кликаем по "Лого"
  		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
      	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
      	    ));
  		logoButton.click();
	}
    
    @Test(groups = {"smoke", "speed4"})
	public void T13_3_Formuls_Date() {
		
    	System.out.println("Запуск T13_3_Formuls_Date");
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
    		String filePath = System.getProperty("user.dir") + "/src/test/resources/Date.dotx";
    		fileInput.sendKeys(filePath);
    		WebElement rename = wait.until(ExpectedConditions.visibilityOfElementLocated(
    				By.xpath("//textarea[contains(@name,'input')]")
    				));
        	rename.sendKeys("DateFormuls от " + formattedDate);
        	pressEnterKey();
        } catch (AWTException e) {
            e.printStackTrace();
        }
		
		timing();
		pressEnterKey();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
		
		WebElement Questinnary = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='questionnaire tag btn default']")));
		Questinnary.click();
		WebElement currentDateVar = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@title='Исходная дата']//input[contains(@name,'input')]")
				));
		currentDateVar.sendKeys("02.04.2024");
		WebElement anotherDateVar = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@title='Другая дата']//input[contains(@name,'input')]")
				));
		anotherDateVar.sendKeys("03.04.2024");
		
		timing1();
		//Далее идёт обработка результатов формул (пауза до 4.0)
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
		//Ищем и кликаем по "Лого"
  		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
      	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
      	    ));
  		logoButton.click();
  		
  	// Завершение работы с драйвером
  	    driver.quit();
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
