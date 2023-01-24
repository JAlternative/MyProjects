package pages.ru.yandex;

import driver.WebDriverManager;
import helpers.CustomAssertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

import static helpers.properties.Properties.urlProperties;

/**
 * Page Object главной страницы яндекса
 *
 * @author Сергей Хорошков
 */
public class YandexMainPage {
    /**
     * selectorLineSearch - поисковая строка в яндексе
     * selectorListOfServices - все видимые сервисы яндекса
     */
    private String selectorLineSearch = "//input[@aria-label='Запрос']";
    private String selectorListOfServices = "//ul[contains(@class, 'services')]//li//span";

    /**
     * Метод возвращает веб элемент сервиса яндекса с названием {serviceName}
     *
     * @param serviceName - название сервиса, по которому будем осуществлять переход
     */
    @Step("Перейти с главной страницы на сервис яндекс {serviceName}")
    public void goToYandexService(String serviceName) {
        WebDriverManager.getDriver().get(urlProperties.urlYandex());
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
