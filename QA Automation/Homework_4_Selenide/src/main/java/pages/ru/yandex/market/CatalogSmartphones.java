package pages.ru.yandex.market;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import custom.assertions.CustomAssertions;
import io.qameta.allure.Step;
import pages.ru.yandex.YandexBasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static custom.helpers.Constant.COUNT_REPETITIONS;

/**
 * PageObject для работы с яндекс маркетом (каталог смартфоны)
 *
 * @author Сергей Хорошков
 */
public class CatalogSmartphones extends YandexBasePage {

    private String selectorLoadingCenter = "//div[@data-zone-name='SearchSerp']//span[@aria-label]";
    private String selectorShowAll = "//fieldset//span[text()='Показать всё']";
    private String selectorSearchField = "//label[text()='Найти производителя']//..//input";
    private String selectorTitleProduct = "//article[@class]//h3//a";
    private String selectorShowMore = "//div[@class='cia-cs']//span[contains(text(),'Показать ещё')]";

    /**
     * Шаг №5
     * 40. Клик по кнопке "Показать всё"
     * 41. Ввести в поле поиска модель {manufacturer}
     * 42. xpath для клика по модели
     * 45. Клик по модели
     *
     * @param manufacturer список производителей
     * @return Яндекс маркет, каталог смартфоны
     */
    public CatalogSmartphones selectManufacturer(String manufacturer) {
        $x(selectorShowAll).shouldBe(visible).click();
        $x(selectorSearchField).sendKeys(manufacturer);
        String selectorModel = "//div[@data-grabber='SearchFilters']//span[text()='" + manufacturer + "']";
        CustomAssertions.assertTrue($x(selectorModel).is(visible),
                "Производитель с моделью " + manufacturer + " отсутствует в выборочном списке");
        $x(selectorModel).click();
        return this;
    }

    /**
     * Шаг №6
     * 57. Дождаться пока пропадёт значок загрузки
     *
     * @return Яндекс маркет, каталог смартфоны
     */
    @Step("Дождаться результатов поиска")
    public CatalogSmartphones waitResultSearch() {
        $x(selectorLoadingCenter).shouldBe(exist, Duration.ofMillis(10000));
        return this;
    }

    /**
     * Шаг №7
     * 73. Элемент кнопки 'Показать ещё'
     * 74. Счётчик
     * 79. Кликаем по ней
     * 80. Фильтр, в коллекцию попадут те элементы, которые не содержат слово {word}
     *
     * @param word слово, которое должно содержаться в каждом элементе после результатов поиска
     * @return Яндекс маркет, каталог смартфоны
     */
    @Step("Убедиться, что в выборку попали только {word}. Если страниц несколько – проверить все.")
    public CatalogSmartphones checkCompliance(String word) {
        SelenideElement buttonShowMore = $x(selectorShowMore);
        Integer count = 0;
        while (buttonShowMore.is(exist)) {
            count++;
            CustomAssertions.assertTrue(count <= COUNT_REPETITIONS,
                    "Произошло зацикливание! Количество попыток нажать кнопку «Показать ещё» больше " + COUNT_REPETITIONS + " раз");
            buttonShowMore.click();
            ElementsCollection products = $$x(selectorTitleProduct).filter(not(textCaseSensitive(word)));
            if (!products.isEmpty()) {
                CustomAssertions.assertFail("Найден продукт, который не содержит слово " + word + ". Его название: " + products.first().getText() +
                        ". Ссылка на продукт: " + products.first().getAttribute("href"));
            }
        }
        return this;
    }
}
