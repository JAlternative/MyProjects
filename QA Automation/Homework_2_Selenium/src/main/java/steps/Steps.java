package steps;

import io.qameta.allure.Step;
import pages.ru.yandex.YandexMainPage;
import pages.ru.yandex.market.YandexCatalogLaptop;
import pages.ru.yandex.market.YandexMarketHomePage;

import java.util.ArrayList;

import static driver.WebDriverManager.getDriver;

/**
 * В этом классе расписаны поэтапные шаги,
 * которые мы будем осуществлять, тестируя яндекс маркет
 *
 * @author - Сергей Хорошков
 */
public class Steps {

    /**
     * Шаг №1
     *
     * @param url - ссылка, по который будет осуществлён переход
     */
    @Step("Зайти на {url}")
    public void goPage(String url) {
        getDriver().get(url);
    }

    /**
     * Шаг №2
     *
     * @param yandexMainPage главная страница яндекса
     * @param serviceName    сервис яндекса, по которому будет осуществлён переход
     * @see YandexMainPage#goToYandexService(String)
     */
    @Step("Перейти на Яндекс {serviceName}")
    public void goToYandexService(YandexMainPage yandexMainPage, String serviceName) {
        yandexMainPage.goToYandexService(serviceName);
    }

    /**
     * Шаг №3
     *
     * @param yandexMarketHomePage яндекс маркет (домашняя страница)
     * @param productCategory      раздел в яндекс маркете
     * @see YandexMarketHomePage#openProductSection(String)
     */
    @Step("Перейти в Каталог -> Навести курсор на раздел {productCategory}")
    public void openProductSection(YandexMarketHomePage yandexMarketHomePage, String productCategory) {
        yandexMarketHomePage.openProductSection(productCategory);
    }


    /**
     * Шаг №4
     *
     * @param yandexMarketHomePage яндекс маркет (домашняя страница)
     * @param sectionName          название раздела, который требуется выбрать
     * @see YandexMarketHomePage#selectSection(String)
     */
    @Step("Выбрать раздел {sectionName}")
    public void selectSection(YandexMarketHomePage yandexMarketHomePage, String sectionName) {
        yandexMarketHomePage.selectSection(sectionName);
    }

    /**
     * Шаг №5
     *
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @param startPrice          Цена от
     * @param finalPrice          Цена до
     * @see YandexCatalogLaptop#setAPrice(String, String)
     */
    @Step("Задать параметр «Цена, Р» от {startPrice} до {finalPrice} рублей")
    public void setAPrice(YandexCatalogLaptop yandexCatalogLaptop, String startPrice, String finalPrice) {
        yandexCatalogLaptop.setAPrice(startPrice, finalPrice);
    }

    /**
     * Шаг №6
     *
     * @param manufacturers       список производителей
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @see YandexCatalogLaptop#selectManufacturer(ArrayList, String, Integer)
     */
    @Step("Выбрать производителя {manufacturers}")
    public void selectManufacturer(YandexCatalogLaptop yandexCatalogLaptop, ArrayList<String> manufacturers) {
        yandexCatalogLaptop.selectManufacturer(new ArrayList<>(manufacturers), "", 0);
    }

    /**
     * Шаг №7
     * Метод дожидается результатов поиска, после введённых фильтров
     *
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @see YandexCatalogLaptop#waitsResultSearch()
     */
    @Step("Дождаться результатов поиска")
    public void waitsResultSearch(YandexCatalogLaptop yandexCatalogLaptop) {
        yandexCatalogLaptop.waitsResultSearch();
    }

    /**
     * Шаг №8
     *
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @param countElementsOnPage минимальное количество элементов на странице
     * @see YandexCatalogLaptop#checkCountElements(Integer)
     */
    @Step("Проверить, что на странице отображалось более {countElementsOnPage} элементов")
    public void checkCountElements(YandexCatalogLaptop yandexCatalogLaptop, Integer countElementsOnPage) {
        yandexCatalogLaptop.checkCountElements(countElementsOnPage);
    }

    /**
     * Шаг №9
     *
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @param startPrice          начальная цена
     * @param finalPrice          конечная цена
     * @param manufacturers       список производителей
     * @see YandexCatalogLaptop#checkCompliance(String, String, ArrayList)
     */
    @Step("Проверить что на всех страницах предложения соответствуют фильтру")
    public void checkCompliance(YandexCatalogLaptop yandexCatalogLaptop, String startPrice, String finalPrice, ArrayList<String> manufacturers) {
        yandexCatalogLaptop.checkCompliance(startPrice, finalPrice, manufacturers);
    }

    /**
     * Шаг №10
     *
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @param numberPage номер страницы, на которую требуется вернуться
     * @return возвращает первое значение с первой страницы
     * @see YandexCatalogLaptop#backOnPage(Integer)
     */
    @Step("Вернуться на страницу №{numberPage} с результатами поиска и запомнить первое значение")
    public String backOnPage(YandexCatalogLaptop yandexCatalogLaptop, Integer numberPage) {
        return yandexCatalogLaptop.backOnPage(numberPage);
    }

    /**
     * Шаг №11
     *
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @param titleFirstElement   сохранённое значение названия продукта
     * @see YandexCatalogLaptop#enterValue(String)
     */
    @Step("В поисковую строку ввести сохраненное значение")
    public void enterValue(YandexCatalogLaptop yandexCatalogLaptop, String titleFirstElement) {
        yandexCatalogLaptop.enterValue(titleFirstElement);
    }

    /**
     * Шаг №12
     *
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @see YandexCatalogLaptop#clickOnButtonSearch()
     */
    @Step("Нажать кнопку «Найти»")
    public void clickOnButtonSearch(YandexCatalogLaptop yandexCatalogLaptop) {
        yandexCatalogLaptop.clickOnButtonSearch();
    }

    /**
     * Шаг №13
     *
     * @param yandexCatalogLaptop яндекс маркет (каталог ноутбуки)
     * @param titleFirstElement   сохранённое значение названия продукта
     * @see YandexCatalogLaptop#checkResult(String)
     */
    @Step("Сохранить результаты поиска страницы и отправить на проверку")
    public void checkResult(YandexCatalogLaptop yandexCatalogLaptop, String titleFirstElement) {
        yandexCatalogLaptop.checkResult(titleFirstElement);
    }
}
