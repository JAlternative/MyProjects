package driver;

import listeners.CustomListener;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static helpers.properties.Properties.mainProperties;


/**
 * Класс для взаимодействия с WebDriver
 *
 * @author Сергей Хорошков
 */
public class WebDriverManager {

    /**
     * Конструктор по умолчанию
     */
    public WebDriverManager() {

    }

    private static WebDriver driver;

    private static EventFiringWebDriver eventDriver;

    /**
     * Метод для взаимодействия с драйверов
     *
     * @return текущее состояние драйвера
     */
    public static EventFiringWebDriver getDriver() {
        return eventDriver;
    }

    /**
     * Метод, для инициализации WebDriver, открытия развертывания браузера на весь экран.
     * Также метод проверяет совместимость версии драйвера с браузером
     */
    public static void createDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
        } catch (SessionNotCreatedException e) {
            e.printStackTrace();
            Assertions.fail("Данный драйвер несовместим с текущим браузером, используйте другой драйвер");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(mainProperties.defaultTimeout(), TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        eventDriver = new EventFiringWebDriver(driver);
        CustomListener customListener = new CustomListener();
        eventDriver.register(customListener);
    }


    /**
     * Метод закрывает все окна браузера и завершает сеанс WebDriver
     */
    public static void quitDriver() {
        driver.quit();
    }

    /**
     * Метод переключает на следующее окно
     */
    public static void nextWindow() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }
}
