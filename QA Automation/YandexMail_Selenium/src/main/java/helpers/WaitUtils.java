package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static driver.WebDriverManager.getDriver;
import static helpers.properties.Properties.driverProperties;

/**
 * Этот класс содержит ожидаемые условия
 *
 * @author Сергей Хорошков
 */
public class WaitUtils {
    /**
     * Ожидание для проверки, что элемент невидим на странице
     *
     * @param xpath Локатор элемента
     */
    public static void waitInvisibilityOf(String xpath) {
        new WebDriverWait(getDriver(), driverProperties.defaultTimeout())
                .until(ExpectedConditions.invisibilityOf(getDriver().findElement(By.xpath(xpath))));
    }

}
