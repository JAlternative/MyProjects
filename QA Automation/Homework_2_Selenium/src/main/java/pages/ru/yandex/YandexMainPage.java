package pages.ru.yandex;

import driver.WebDriverManager;
import helpers.CustomAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

/**
 * Главная страница яндекса
 *
 * @author Сергей Хорошков
 */
public class YandexMainPage {
    private String selectorLineSearch = "//input[@aria-label='Запрос']";
    private String selectorListOfServices = "//ul[contains(@class, 'services')]//li//span";

    /**
     * Метод возвращает веб элемент сервиса яндекса с названием {serviceName}
     *
     * @param serviceName - название сервиса, по которому будем осуществлять переход
     */
    public void goToYandexService(String serviceName) {
        WebDriverManager.getDriver().switchTo().frame(0);
        WebElement lineSearchElement = WebDriverManager.getDriver().findElement(By.xpath(selectorLineSearch));
        List<WebElement> listOfServices = WebDriverManager.getDriver().findElements(By.xpath(selectorListOfServices));
        lineSearchElement.click();
        WebElement serviceElement = listOfServices.stream().filter(s -> s.getText().contains(serviceName))
                .findFirst().orElse(null);
        CustomAssertions.assertTrue(Objects.nonNull(serviceElement),
                "Сервис с названием «" + serviceName + "» отсутствует на главной странице яндекса");
        serviceElement.click();
    }
}
