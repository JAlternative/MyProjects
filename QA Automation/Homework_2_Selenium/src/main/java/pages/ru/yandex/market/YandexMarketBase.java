package pages.ru.yandex.market;

import driver.WebDriverManager;
import helpers.CustomAssertions;
import helpers.UserActions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

import static driver.WebDriverManager.getDriver;

/**
 * Родительский класс, элементы которого
 * находятся на всех страницах яндекс маркета
 *
 * @author Сергей Хорошков
 */
public abstract class YandexMarketBase {
    private String selectorButtonCatalog = "//button//span[text()='Каталог']";
    private String selectorProductsCatalog = "//li[@data-zone-name='category-link']";
    private String selectorButtonsMore = "//ul//li//span[contains(text(), 'Ещё')]";
    private String selectorProductsSubcategory = "//div[@data-zone-name='link']";

    /**
     * Метод заходит в каталог товаров, наводит курсор на нужную нам категорию
     * и кликает по кнопкам «Еще»
     *
     * @param productCategory - категория товара, на которую будем наводить курсор
     * @see WaitUtils#waitVisibilityOfAllElements(List)
     * @see UserActions#mouseMovement(WebElement)
     */
    public void openProductSection(String productCategory) {
        WebDriverManager.nextWindow();
        getDriver().findElement(By.xpath(selectorButtonCatalog)).click();
        List<WebElement> productsCatalog = getDriver().findElements(By.xpath(selectorProductsCatalog));
        WaitUtils.waitVisibilityOfAllElements(productsCatalog);
        WebElement product = productsCatalog
                .stream().filter(p -> p.getText().toLowerCase().contains(productCategory.toLowerCase()))
                .findFirst().orElse(null);
        CustomAssertions.assertTrue(Objects.nonNull(product),
                "Категории товара в каталоге с названием «" + productCategory + "» не найдено.");
        UserActions.mouseMovement(product);
        getDriver().findElements(By.xpath(selectorButtonsMore)).stream().forEach(WebElement::click);
    }

    /**
     * Метод для выбора элемента из выпадающего списка
     *
     * @param sectionName - подкатегорию товара в выбранном разделе, на который будем кликать и переходить на другую страницу
     */
    public void selectSection(String sectionName) {
        WebElement elementSection = getDriver().findElements(By.xpath(selectorProductsSubcategory)).stream()
                .filter(c -> c.getText().toLowerCase().equals(sectionName.toLowerCase()))
                .findFirst().orElse(null);
        CustomAssertions.assertTrue(Objects.nonNull(elementSection),
                "Подкатегории товара с названием «" + sectionName + "» не найдено.");
        elementSection.click();
    }
}
