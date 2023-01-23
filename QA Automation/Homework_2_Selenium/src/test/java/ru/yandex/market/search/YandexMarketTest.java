package ru.yandex.market.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ru.yandex.YandexMainPage;
import pages.ru.yandex.market.YandexCatalogLaptop;
import pages.ru.yandex.market.YandexMarketHomePage;
import ru.yandex.market.base.BaseTest;
import steps.Steps;

import java.util.ArrayList;

import static helpers.properties.Properties.urlProperties;

/**
 * Задание 1_4. Тест яндекс маркета
 *
 * @author Сергей Хорошков
 */
@DisplayName("Задание 1_4")
public class YandexMarketTest extends BaseTest {
    /**
     * Переход с главной странице яндекса на яндекс маркет. И ввод значений, которые указаны в параметрах
     *
     * @param serviceName     название сервиса, по которому будет осуществлён переход с главной страницы
     * @param productCategory категория товара из каталога
     * @param sectionName     название раздела, который требуется выбрать
     * @param startPrice      цена от
     * @param finalPrice      цена до
     * @param manufacturers   список производителей
     * @param countElementsOnPage минимальное количество элементов, которое должно быть на странице
     * @param numberPage номер страницы, на которую нужно будет осуществить переход
     */
    @ParameterizedTest
    @MethodSource("helpers.DataProvider#provideYandexMarket")
    @DisplayName("Проверка Яндекс Маркета")
    @Tag("ЯндексМаркет-Ноутбуки")
    public void yandexMarketTest(String serviceName, String productCategory, String sectionName, String startPrice, String finalPrice,
                                 ArrayList<String> manufacturers, Integer countElementsOnPage, Integer numberPage) {
        Steps steps = new Steps();
        steps.goPage(urlProperties.urlYandex());

        YandexMainPage yandexMainPage = new YandexMainPage();
        steps.goToYandexService(yandexMainPage, serviceName);

        YandexMarketHomePage yandexMarketHomePage = new YandexMarketHomePage();
        steps.openProductSection(yandexMarketHomePage, productCategory);
        steps.selectSection(yandexMarketHomePage, sectionName);

        YandexCatalogLaptop yandexCatalogLaptop = new YandexCatalogLaptop();
        steps.setAPrice(yandexCatalogLaptop, startPrice, finalPrice);
        steps.selectManufacturer(yandexCatalogLaptop, manufacturers);
        steps.waitsResultSearch(yandexCatalogLaptop);
        steps.checkCountElements(yandexCatalogLaptop, countElementsOnPage);
        steps.checkCompliance(yandexCatalogLaptop, startPrice, finalPrice, manufacturers);
        String titleFirstElement = steps.backOnPage(yandexCatalogLaptop, numberPage);
        steps.enterValue(yandexCatalogLaptop, titleFirstElement);
        steps.clickOnButtonSearch(yandexCatalogLaptop);
        steps.checkResult(yandexCatalogLaptop, titleFirstElement);
    }
}
