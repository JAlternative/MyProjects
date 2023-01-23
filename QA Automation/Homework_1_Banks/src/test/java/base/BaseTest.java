package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static utils.Constants.DEFAULT_TIMEOUT;
import static utils.Constants.PAGE_LOAD_TIMEOUT;

/**
 * Базовый тест
 * @author Сергей Хорошков
 * */
public class BaseTest {

    /**
     * Конструктор по умолчанию
     * */
    public BaseTest(){

    }
    protected WebDriver driver;

    /**
     * Перед каждым тестом запускаем инициализацию драйвера
     * */
    @BeforeEach
    public void createDriver(){
        try {
            System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
            driver = new ChromeDriver();
        } catch (SessionNotCreatedException e) {
            e.printStackTrace();
            Assertions.fail("Данный драйвер несовместим с текущим браузером, используйте другой драйвер");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }

    /**
     * После каждого теста завершаем работу драйвера
     * */
    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

}
