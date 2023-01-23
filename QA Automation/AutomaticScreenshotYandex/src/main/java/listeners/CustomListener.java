package listeners;

import driver.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * На каждое взаимодействие с веб-страницей автоматически
 * снимается скриншот и вставляется в алюр отчёт
 *
 * @author Сергей Хорошков
 */
public class CustomListener implements WebDriverEventListener {

    /**
     * Конструктор по умолчанию
     */
    public CustomListener() {
    }

    /**
     * Метод для скриншота
     *
     * @return сохранить скриншот
     */
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Метод срабатывает после клика по элементу "click()"
     */
    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        Allure.getLifecycle().addAttachment(
                "screenshot", "image/png", "png",
                saveScreenshot()
        );
    }

    /**
     * Метод срабатывает после прокрутки страницы "script scroll()"
     */
    @Override
    public void afterScript(String s, WebDriver webDriver) {
        Allure.getLifecycle().addAttachment(
                "screenshot", "image/png", "png",
                saveScreenshot()
        );
    }

    /**
     * Метод срабатывает после ввода данных в поле "sendKeys()"
     */
    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        Allure.getLifecycle().addAttachment(
                "screenshot", "image/png", "png",
                saveScreenshot()
        );
    }


    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }


    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }


    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {
    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }
}
