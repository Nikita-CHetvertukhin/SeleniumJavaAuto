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

public class T10_Share {
private WebDriver driver;
	
	@BeforeSuite(groups = {"smoke"})
    public void setUp() {
        // Инициализируем драйвер, ожидающие объекты и другие переменные
        this.driver = DriverConst.getDriver();
    }
	
	@Test(groups = {"smoke"})
	public void t10_1_ReaderUserShare() {
		
		System.out.println("Запуск t10_1_ReaderUserShare");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        //Подтягиваем данные из ConfigReader.java
        String testUserName = ConfigReader.getProperty("testUserName");
        
        WebElement findFile = waitAndFindElement(
                By.xpath("//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='copyCreateDotx Smoke тестирование от " + formattedDate + "']")
            );
            contextClickElement(findFile);

            clickElement(By.xpath("//td[@title='Настроить доступ']"));
            
            WebElement inputLogin = waitAndFindElement(
                By.xpath("//input[@placeholder='Введите ФИО, e-mail или название группы']")
            );
            inputLogin.sendKeys(testUserName);

            clickElement(By.xpath("//div[contains(@class, 'share-select')]//td[@class='column first']//div[@class='cell']"));
            clickElement(By.xpath("//td[@class='column int']"));
            clickElement(By.xpath("//div[contains(@class, 'acces')]//a[contains(@class, 'btn-trigger') and contains(@class, 'btn default no-text')]"));
            clickElement(By.xpath("//td[@class='column' and @title='Читатель']"));
            clickElement(By.xpath("//div[@class='footer']//a[@class='btn primary push']"));
            timing();
        }
	
	@Test(groups = {"smoke"})
	public void t10_2_ReaderGroupShare() {
		
		System.out.println("Запуск t10_2_ReaderGroupShare");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        //Подтягиваем данные из ConfigReader.java
        String testGroup = ConfigReader.getProperty("testGroup");
        
        WebElement findFile = waitAndFindElement(
                By.xpath("//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='dotxDownload Smoke тестирование от " + formattedDate + "']")
            );
            contextClickElement(findFile);

            clickElement(By.xpath("//td[@title='Настроить доступ']"));
            
            WebElement inputGroup = waitAndFindElement(
                By.xpath("//input[@placeholder='Введите ФИО, e-mail или название группы']")
            );
            inputGroup.sendKeys(testGroup);

            clickElement(By.xpath("//div[contains(@class, 'share-select')]//td[@class='column first']//div[@class='cell']"));
            clickElement(By.xpath("//td[@class='column int']"));
            clickElement(By.xpath("//div[contains(@class, 'acces')]//a[contains(@class, 'btn-trigger') and contains(@class, 'btn default no-text')]"));
            clickElement(By.xpath("//td[@class='column' and @title='Читатель']"));
            clickElement(By.xpath("//div[@class='footer']//a[@class='btn primary push']"));
            timing();
        }
	
	@Test(groups = {"smoke"})
	public void t10_3_CommentUserShare() {
		
		System.out.println("Запуск t10_3_CommentUserShare");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        //Подтягиваем данные из ConfigReader.java
        String testUserName = ConfigReader.getProperty("testUserName");
        
        WebElement findFile = waitAndFindElement(
                By.xpath("//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='DoczillaStyles Smoke тестирование от " + formattedDate + "']")
            );
            contextClickElement(findFile);

            clickElement(By.xpath("//td[@title='Настроить доступ']"));
            
            WebElement inputLogin = waitAndFindElement(
                By.xpath("//input[@placeholder='Введите ФИО, e-mail или название группы']")
            );
            inputLogin.sendKeys(testUserName);

            clickElement(By.xpath("//div[contains(@class, 'share-select')]//td[@class='column first']//div[@class='cell']"));
            clickElement(By.xpath("//td[@class='column int']"));
            clickElement(By.xpath("//div[contains(@class, 'acces')]//a[contains(@class, 'btn-trigger') and contains(@class, 'btn default no-text')]"));
            clickElement(By.xpath("//td[@class='column' and @title='Комментатор']"));
            clickElement(By.xpath("//div[@class='footer']//a[@class='btn primary push']"));
            timing();
        }
	
	@Test(groups = {"smoke"})
	public void t10_4_EditorUserShare() {
		
		System.out.println("Запуск t10_4_EditorUserShare");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        //Подтягиваем данные из ConfigReader.java
        String testUserName = ConfigReader.getProperty("testUserName");
        
        WebElement findFile = waitAndFindElement(
                By.xpath("//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='createDotx Smoke тестирование от " + formattedDate + "']")
            );
            contextClickElement(findFile);

            clickElement(By.xpath("//td[@title='Настроить доступ']"));
            
            WebElement inputLogin = waitAndFindElement(
                By.xpath("//input[@placeholder='Введите ФИО, e-mail или название группы']")
            );
            inputLogin.sendKeys(testUserName);

            clickElement(By.xpath("//div[contains(@class, 'share-select')]//td[@class='column first']//div[@class='cell']"));
            clickElement(By.xpath("//td[@class='column int']"));
            clickElement(By.xpath("//div[contains(@class, 'acces')]//a[contains(@class, 'btn-trigger') and contains(@class, 'btn default no-text')]"));
            clickElement(By.xpath("//td[@class='column' and @title='Редактор']"));
            clickElement(By.xpath("//div[@class='footer']//a[@class='btn primary push']"));
            timing();
        }
	
	@Test(groups = {"smoke"})
	public void t10_5_addDeleteUserShare() {
		
		System.out.println("Запуск t10_5_addDeleteUserShare");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        //Подтягиваем данные из ConfigReader.java
        String testUserName = ConfigReader.getProperty("testUserName");
        
        //Добавляем уровень доступа
        WebElement findFile = waitAndFindElement(
                By.xpath("//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='Docx Smoke тестирование от " + formattedDate + "']")
            );
            contextClickElement(findFile);

            clickElement(By.xpath("//td[@title='Настроить доступ']"));
            
            WebElement inputLogin = waitAndFindElement(
                By.xpath("//input[@placeholder='Введите ФИО, e-mail или название группы']")
            );
            inputLogin.sendKeys(testUserName);

            clickElement(By.xpath("//div[contains(@class, 'share-select')]//td[@class='column first']//div[@class='cell']"));
            clickElement(By.xpath("//td[@class='column int']"));
            clickElement(By.xpath("//div[contains(@class, 'acces')]//a[contains(@class, 'btn-trigger') and contains(@class, 'btn default no-text')]"));
            clickElement(By.xpath("//td[@class='column' and @title='Редактор']"));
            clickElement(By.xpath("//div[@class='footer']//a[@class='btn primary push']"));
            timing();
            
          //Удаляем уровень доступа
          WebElement findFile2 = waitAndFindElement(
                  By.xpath("//div[@class='body']//div[@class='scroller items']//span[@class='text' and @title='Docx Smoke тестирование от " + formattedDate + "']")
              );
              contextClickElement(findFile2);

              clickElement(By.xpath("//td[@title='Настроить доступ']"));

              clickElement(By.xpath("//td[@class='column int']"));
              timing();
              clickElement(By.xpath("//td[@class='column int']"));
              clickElement(By.xpath("//div[contains(@class, 'acces')]//a[contains(@class, 'btn-trigger') and contains(@class, 'btn default no-text')]"));
              clickElement(By.xpath("//td[@class='column' and @title='Нет доступа']"));
              clickElement(By.xpath("//div[@class='footer']//a[@class='btn primary push']"));
              timing();
        }

        private WebElement waitAndFindElement(By locator) {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }

        private void contextClickElement(WebElement element) {
        	Actions actions = new Actions(driver);
            actions.contextClick(element).perform();
        }

        private void clickElement(By locator) {
            WebElement element = waitAndFindElement(locator);
            element.click();
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
