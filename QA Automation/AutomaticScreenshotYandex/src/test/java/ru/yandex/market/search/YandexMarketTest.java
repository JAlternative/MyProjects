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
     * Конструктор по умолчанию
     */
    public YandexMarketTest() {

    }

    /**
     * Переход с главной странице яндекса на яндекс маркет. И ввод значений, которые указаны в параметрах
     *
     * @param serviceName     название сервиса, по которому будет осуществлён переход с главной страницы
     * @param productCategory категория товара из каталога
     * @param sectionName     название раздела, который требуется выбрать
     * @param startPrice      Цена от
     * @param finalPrice      Цена до
     * @param manufacturers   список производителей
     */
    @ParameterizedTest
    @MethodSource("helpers.DataProvider#provideYandexMarket")
    @DisplayName("Проверка Яндекс Маркета")
    @Tag("ЯндексМаркет-Ноутбуки")
    public void yandexMarketTest(String serviceName, String productCategory, String sectionName,
                                 String startPrice, String finalPrice, ArrayList<String> manufacturers) {
        Steps steps = new Steps();
        YandexMainPage yandexMainPage = new YandexMainPage();
        YandexMarketHomePage yandexMarketHomePage = new YandexMarketHomePage();
        YandexCatalogLaptop yandexCatalogLaptop = new YandexCatalogLaptop();

        steps.goPage(urlProperties.urlYandex());
        steps.goToYandexService(yandexMainPage, serviceName);
        steps.openProductSection(yandexMarketHomePage, productCategory);
        steps.selectSection(yandexMarketHomePage, sectionName);
        steps.setAPrice(yandexCatalogLaptop, startPrice, finalPrice);
        steps.selectManufacturer(yandexCatalogLaptop, manufacturers);
        steps.waitsResultSearch(yandexCatalogLaptop);
        steps.twelveElementsCheck(yandexCatalogLaptop);
        steps.checkCompliance(yandexCatalogLaptop, startPrice, finalPrice, manufacturers);
        String titleFirstElement = steps.backOnFirstPage(yandexCatalogLaptop);
        steps.enterValue(yandexCatalogLaptop, titleFirstElement);
        steps.clickOnButtonSearch(yandexCatalogLaptop);
        steps.checkResult(yandexCatalogLaptop, titleFirstElement);
    }
}
