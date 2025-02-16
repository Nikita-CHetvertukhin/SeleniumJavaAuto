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

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.ITestResult;
import org.testng.SkipException;

import methods.StopTestExecutionListener;
import methods.DriverConst;

@Listeners(StopTestExecutionListener.class)
public class T03_Generator {

	private WebDriver driver;

    @BeforeSuite(groups = {"smoke"})
    public void setUp() {
        // Получаем общий драйвер из DriverFactory
        driver = DriverConst.getDriver();
    }
	
    @Test(groups = {"smoke"})
	public void t3_1_Generation_Schem() {
		
		System.out.println("Запуск t3_1_Generation_Schem");
		
		//Инициируем ожидание с лимитом в 1 секунду
		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
		//Инициируем ожидание с лимитом в 10 секунд
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	
    	//Поиск кнопки генерации схемы данных
    	try {
    	    WebElement buttonAdministrationSimple = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//a[contains(@class, 'btn default')]//span[contains(text(), 'Администрирование')]")
    	    ));
    	    buttonAdministrationSimple.click();
    	    
    	    WebElement buttonGeneration = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//div[contains(@class, ' text') and @title='Генерация схемы данных']")
    	    ));
    	    buttonGeneration.click();
    	} catch (TimeoutException e) {
    	    WebElement buttonMore = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//a[contains(@class, 'btn default')]//span[contains(text(), 'Ещё')]")
    	    ));
    	    buttonMore.click();

    	    // Создаем объект Actions для выполнения действий с элементом
    	    Actions actions = new Actions(driver);

    	    WebElement buttonAdministration = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//div[contains(@class, 'text tree right') and @title='Администрирование']")
    	    ));
    	    // Наводим курсор на элемент
    	    actions.moveToElement(buttonAdministration).perform();

    	    WebElement buttonGeneration = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//div[contains(@class, ' text') and @title='Генерация схемы данных']")
    	    ));
    	    buttonGeneration.click();
    	}
    	
    	//Получаем результат генерации
    	// Найти первый элемент
    	WebElement firstElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//td[contains(@class, 'column last')]//div[contains(@class, 'cell')]//span[contains(@class, 'text') and @title='100%']")
            ));
    	// Ожидать пока значение первого элемента не станет равным "100%"
        wait.until(ExpectedConditions.textToBe(
            By.xpath("//td[contains(@class, 'column last')]//div[contains(@class, 'cell')]//span[contains(@class, 'text') and @title='100%']"), "100%"));
        
        // Найти второй элемент
        WebElement secondElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class, 'html control')]//p[contains(@class, 'text') and contains(text(), 'JUST-IN-TIME COMPILATION SUCCESSFUL')]")
        ));
        WebElement secondElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class, 'html control')]//p[contains(@class, 'text') and contains(text(), 'Планировщик заданий запущен')]")
        ));
        
        // Получить текст обоих элементов
        String secondElementText1 = secondElement1.getText();
        String secondElementText2 = secondElement2.getText();

        // Условия прохождения теста
        Assert.assertEquals(firstElement.getText(), "100%", "Первый элемент не равен '100%'");
        Assert.assertEquals(secondElementText1, "JUST-IN-TIME COMPILATION SUCCESSFUL", "Текст во втором 'p' не равен 'JUST-IN-TIME COMPILATION SUCCESSFUL'");
        Assert.assertEquals(secondElementText2, "Планировщик заданий запущен", "Текст во втором 'p' не равен 'Планировщик заданий запущен'");
        
        try {
            Thread.sleep(250); // 250 милисекунд задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
        
        // Закрыть все кнопки с классом "close-button btn default no-text" и атрибутом title="Закрыть"
        List<WebElement> closeButtons = driver.findElements(
        	By.xpath("//a[contains(@class, 'close-button btn default no-text') and @title='Закрыть'] | //div[contains(@class, 'close fa fa-fw fa-times')]"));
        for (WebElement closeButton : closeButtons) {
        	closeButton.click();
        	}
        
        try {
            Thread.sleep(250); // 250 милисекунд задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванное состояние потока
            System.err.println("Поток был прерван, операция не завершена"); // Выводим сообщение об ошибке
        } catch (TimeoutException e) {
            System.err.println("Произошло истечение времени ожидания, операция не завершена"); // Выводим сообщение об ошибке
        }
        
        // Найти и кликнуть по кнопке "Готово"
        WebElement buttonReady = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(@class, 'btn primary push')]//span[contains(text(), 'Готово')]")
        ));
        
        buttonReady.click();
        
	}
    
    @AfterMethod
    public void checkTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            throw new SkipException("Тестирование остановлено из-за критической ошибки");
        }
    }

}
