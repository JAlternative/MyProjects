package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static pages.constants.Constant.*;


public class Waits {

    public static void waitElementIsVisible(WebElement element) {
        new WebDriverWait(BasePage.getDriver(), EXPLICIT_WAIT).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitListElementsIsVisible(WebElement elements) {
        new WebDriverWait(BasePage.getDriver(), EXPLICIT_WAIT).until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }


    public static void invisibilityOfElement(String xpath) {
        new WebDriverWait(BasePage.getDriver(), 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));

        }


    public static WebElement waitElementIsClickable(WebElement element) {
        new WebDriverWait(BasePage.getDriver(), EXPLICIT_WAIT).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }


    public static void implicitWait() {
        BasePage.getDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public static void fullPageWait() {
        BasePage.getDriver().manage().timeouts().pageLoadTimeout(PAGE_WAIT, TimeUnit.SECONDS);
    }

}
