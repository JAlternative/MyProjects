package pages.ru.yandex;

import com.codeborne.selenide.ElementsCollection;
import custom.assertions.CustomAssertions;
import io.qameta.allure.Step;
import pages.ru.yandex.market.YandexMarketHomePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

/**
 * Главная страница яндекса
 *
 * @author Сергей Хорошков
 */
public class YandexMainPage extends YandexBasePage {
    private String selectorLineSearch = "//input[@aria-label='Запрос']";
    private String selectorListOfServices = "//ul[contains(@class, 'services')]//li//span";

    /**
     * Шаг №2
     * 34. Перейти на фрейм ниже
     * 35. Клик по поисковой строке
     * 36. Получить все сервисы яндекса, я отфильтровать по названию {serviceName}
     * 37. Assert, который проверяет, что есть сервис с названием {serviceName} на странице яндекса
     * 39. Кликнуть по выбранному сервису
     * 40. Переключиться на следующее окно
     *
     * @param serviceName сервис яндекса, по которому будет осуществлён переход
     * @return домашняя страница яндекс маркета
     */
    @Step("Перейти на Яндекс {serviceName}")
    public YandexMarketHomePage goToYandexService(String serviceName) {
        switchTo().frame(0);
        $x(selectorLineSearch).click();
        ElementsCollection serviceElements = $$x(selectorListOfServices).filterBy(text(serviceName));
        CustomAssertions.assertTrue(!serviceElements.isEmpty(),
                "Сервис с названием «" + serviceName + "» отсутствует на главной странице яндекса");
        serviceElements.first().click();
        switchTo().window(1);
        return page(YandexMarketHomePage.class);
    }
}
