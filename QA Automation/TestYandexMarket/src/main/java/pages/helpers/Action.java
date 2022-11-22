package pages.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

public class Action {
    /**
     * Наведение мышки на веб элемент слева
     * */
    private static Actions actions = new Actions(BasePage.getDriver());

    public static void mouseMovement(WebElement el) {
        actions.moveToElement(el).build().perform();
    }

}
