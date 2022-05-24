package tests.base;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import pages.driver.WebDriverManager;
import pages.home_page.MarketGoToSection;
import pages.home_page.YandexMarketHomePage;
import pages.steps.StepOpenCatalog;

public class BaseTest {

    protected WebDriver driver = WebDriverManager.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected YandexMarketHomePage yandexMarketHomePage = new YandexMarketHomePage(driver);
    protected StepOpenCatalog stepOpenCatalog = new StepOpenCatalog();
    protected MarketGoToSection marketGoToSection = new MarketGoToSection(driver);


    @AfterEach
    public void close() {
        driver.quit();
    }
}
