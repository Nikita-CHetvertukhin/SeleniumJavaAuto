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
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

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
        
        timing();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("//a[@class='status tool btn default disabled']//span[contains(@title, 'Cохранен') or contains(@title, 'Последнее')]")
        		));
        
      //Ищем и кликаем по "Лого"
      		WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(
          	        By.xpath("//div[@class = 'header']//a[@class = 'logo btn default']")
          	    ));
      		logoButton.click();
	}
    
    // Метод нажатия Enter
    public void pressEnterKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform(); // Отправляем клавишу Enter
    }
    private void timing() {
        try {
            Thread.sleep(1000); // 1000 милисекунд задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
    }
}
