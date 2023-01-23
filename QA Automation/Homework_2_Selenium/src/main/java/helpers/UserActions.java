package helpers;

import driver.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Класс, которые содержит пользовательские действия с веб-страницей
 *
 * @author Сергей Хорошков
 */
public class UserActions {
    /**
     * Наводит курсор мыши на элемент
     *
     * @param webElement Веб-элемент на который необходимо навести курсор
     */
    public static void mouseMovement(WebElement webElement) {
        new Actions(WebDriverManager.getDriver()).moveToElement(webElement).build().perform();
    }
}
