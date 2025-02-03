package methods; //Вызывается импортом import methods.StopTestExecutionListener и позволяет остановить тестирование, если тест-кейс провален

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class StopTestExecutionListener implements IInvokedMethodListener {
    private boolean stopTests = false;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (stopTests) {
            throw new SkipException("Тест пропущен из-за критической ошибки");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            stopTests = true;
        }
    }
}
