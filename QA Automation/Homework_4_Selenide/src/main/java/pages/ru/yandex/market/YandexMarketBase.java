package pages.ru.yandex.market;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import custom.assertions.CustomAssertions;
import io.qameta.allure.Step;
import pages.ru.yandex.YandexMainPage;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Родительский класс, элементы которого
 * находятся на всех страницах яндекс маркета
 *
 * @author Сергей Хорошков
 */
public abstract class YandexMarketBase extends YandexMainPage {
    private String selectorButtonCatalog = "//button//span[text()='Каталог']";
    private String selectorProductsCatalog = "//li[@data-zone-name='category-link']";
    private String selectorButtonsMore = "//ul//li//span[contains(text(), 'Ещё')]";
    private String selectorProductsSubcategory = "//div[@data-zone-name='link']";

    /**
     * Шаг №3
     * 37. Кликнуть по кнопке 'Каталог'
     * 38. Отфильтровать элементы по названию {productCategory}
     * 41. Навести мышку на нужную категорию
     * 42. Прокликать кнопки 'Ещё'
     *
     * @param productCategory раздел в яндекс маркете
     * @return Яндекс Маркет домашняя страница
     */
    @Step("Перейти в Каталог -> Выбрать раздел {productCategory}")
    public YandexMarketHomePage openProductSection(String productCategory) {
        $x(selectorButtonCatalog).shouldBe(visible).click();
        ElementsCollection products = $$x(selectorProductsCatalog).should(sizeNotEqual(0)).filter(text(productCategory));
        CustomAssertions.assertTrue(!products.isEmpty(),
                "Категории товара в каталоге с названием «" + productCategory + "» не найдено.");
        products.findBy(exactText(productCategory)).hover();
        $$x(selectorButtonsMore).forEach(SelenideElement::click);
        return page(YandexMarketHomePage.class);
    }

    /**
     * Шаг №4
     * 56. Отфильтровать элементы по названию {sectionName}
     * 59. Кликнуть по секции с нужным нам названием
     *
     * @param sectionName название раздела, который требуется выбрать
     * @return следующая страница из каталога
     */
    @Step("Выбрать раздел {sectionName}")
    public CatalogSmartphones selectSection(String sectionName) {
        ElementsCollection sections = $$x(selectorProductsSubcategory).filterBy(text(sectionName));
        CustomAssertions.assertTrue(!sections.isEmpty(),
                "Подкатегории товара с названием «" + sectionName + "» не найдено.");
        sections.findBy(exactText(sectionName)).click();
        return page(CatalogSmartphones.class);
    }
}
