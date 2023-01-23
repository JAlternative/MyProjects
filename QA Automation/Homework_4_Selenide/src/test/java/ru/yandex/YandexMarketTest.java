package ru.yandex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ru.yandex.YandexBasePage;
import pages.ru.yandex.YandexMainPage;

import static custom.properties.Properties.urlProperties;

/**
 * Задание 2_1. Тест яндекс маркета
 *
 * @author Сергей Хорошков
 */
@DisplayName("Задание 2_1")
public class YandexMarketTest extends BaseTest {
    /**
     * Переход с главной странице яндекса на яндекс маркет. И ввод значений, которые указаны в параметрах
     *
     * @param serviceName     название сервиса, по которому будет осуществлён переход с главной страницы
     * @param productCategory категория товара из каталога
     * @param sectionName     название раздела, который требуется выбрать
     * @param manufacturer    производитель
     * @param word            слово, которое должно быть в каждом результате
     */
    @ParameterizedTest
    @MethodSource("custom.helpers.DataProvider#provideYandexMarket")
    @DisplayName("Проверка Яндекс Маркета.")
    @Tag("ЯндексМаркет-Смартфоны")
    public void yandexMarketTest(String serviceName, String productCategory, String sectionName, String manufacturer, String word) {
        YandexBasePage.goPage(urlProperties.urlYandex(), YandexMainPage.class)
                .goToYandexService(serviceName)
                .openProductSection(productCategory)
                .selectSection(sectionName)
                .selectManufacturer(manufacturer)
                .waitResultSearch()
                .checkCompliance(word);
    }
}
