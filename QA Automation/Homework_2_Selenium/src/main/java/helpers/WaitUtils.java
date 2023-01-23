package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static driver.WebDriverManager.getDriver;
import static helpers.properties.Properties.driverProperties;

/**
 * Этот класс содержит ожидаемые условия
 *
 * @author Сергей Хорошков
 */
public class WaitUtils {
    /**
     * Ожидание проверки того, что все элементы, присутствующие на веб-странице, которые соответствуют локатору, видны
     *
     * @param webElements элементы, которые должны быть нам видны
     */
    public static void waitVisibilityOfAllElements(List<WebElement> webElements) {
        new WebDriverWait(getDriver(), driverProperties.defaultTimeout())
                .until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    /**
     * Ожидание проверки того, что элемент, о котором известно, что он присутствует в DOM страницы, виден.
     *
     * @param xpath локатор элемента
     */
    public static void waitVisibilityOf(String xpath) {
        new WebDriverWait(getDriver(), driverProperties.defaultTimeout())
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(xpath))));
    }

    /**
     * Ожидание для проверки, что элемент невидим на странице
     *
     * @param xpath Локатор элемента
     */
    public static void waitInvisibilityOf(String xpath) {
        new WebDriverWait(getDriver(), driverProperties.defaultTimeout())
                .until(ExpectedConditions.invisibilityOf(getDriver().findElement(By.xpath(xpath))));
    }

    /**
     * Ожидание проверки того, что элемент присутствует в DOM страницы и виден
     *
     * @param xpath Локатор элемента
     */
    public static void waitVisibilityOfElementLocated(String xpath) {
        new WebDriverWait(getDriver(), driverProperties.defaultTimeout())
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
}
