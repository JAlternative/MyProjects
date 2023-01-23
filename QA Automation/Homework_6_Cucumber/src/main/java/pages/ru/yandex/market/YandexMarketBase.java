package pages.ru.yandex.market;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import custom.assertions.CustomAssertions;
import io.qameta.allure.Step;
import pages.ru.yandex.YandexMainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

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
     * @param productCategory раздел в яндекс маркете
     */
    @Step("Перейти в Каталог -> Выбрать раздел {productCategory}")
    public void openProductSection(String productCategory) {
        $x(selectorButtonCatalog).shouldBe(visible).click();
        ElementsCollection products = $$x(selectorProductsCatalog).shouldHave(CollectionCondition.sizeNotEqual(0)).filter(text(productCategory));
        CustomAssertions.assertTrue(!products.isEmpty(),
                "Категории товара в каталоге с названием «" + productCategory + "» не найдено.");
        products.findBy(exactText(productCategory)).hover();
        $$x(selectorButtonsMore).forEach(SelenideElement::click);
    }

    /**
     * Шаг №4
     * @param sectionName название раздела, который требуется выбрать
     */
    @Step("Выбрать раздел {sectionName}")
    public void selectSection(String sectionName) {
        ElementsCollection sections = $$x(selectorProductsSubcategory).filterBy(text(sectionName));
        CustomAssertions.assertTrue(!sections.isEmpty(),
                "Подкатегории товара с названием «" + sectionName + "» не найдено.");
        sections.findBy(exactText(sectionName)).click();
    }
}
