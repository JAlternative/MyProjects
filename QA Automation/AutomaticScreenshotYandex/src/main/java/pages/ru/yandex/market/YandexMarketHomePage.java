package pages.ru.yandex.market;

import driver.WebDriverManager;
import helpers.CustomAssertions;
import helpers.UserActions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static driver.WebDriverManager.getDriver;

/**
 * Page Object главной страницы яндекс маркета
 *
 * @author Сергей Хорошков
 */
public class YandexMarketHomePage {

	/**
	 * Конструктор по умолчанию
	 */
	public YandexMarketHomePage() {

	}

	private String selectorButtonCatalog = "//button//span[text()='Каталог']";
	private String selectorProductsCatalog = "//li[@data-zone-name='category-link']";
	private String selectorButtonsMore = "//ul//li//span[contains(text(), 'Ещё')]";
	private String selectorProductsSubcategory = "//div[@data-zone-name='link']";

	/**
	 * Метод заходит в каталог товаров, наводит курсор на нужную нам категорию
	 * и кликает по кнопкам «Еще»
	 *
	 * @param productCategory - категория товара, на которую будем наводить курсор
	 */
	public void openProductSection(String productCategory) {
		WebDriverManager.nextWindow();
		getDriver().findElement(By.xpath(selectorButtonCatalog)).click();

		List<WebElement> productsCatalog = getDriver().findElements(By.xpath(selectorProductsCatalog));
		WaitUtils.waitVisibilityOfAllElements(productsCatalog);
		WebElement product = productsCatalog
			.stream().filter(p -> p.getText().toLowerCase().contains(productCategory.toLowerCase()))
			.findFirst().orElse(null);
		CustomAssertions.assertProductCategory(product, productCategory);
		UserActions.mouseMovement(product);
		getDriver().findElements(By.xpath(selectorButtonsMore)).stream().forEach(WebElement::click);
	}

	/**
	 * Метод выбирает нужную
	 *
	 * @param sectionName - подкатегорию товара в выбранном разделе, на который будем кликать и переходить на другую страницу
	 */
	public void selectSection(String sectionName) {
		WebElement elementSection = getDriver().findElements(By.xpath(selectorProductsSubcategory)).stream()
			.filter(c -> c.getText().toLowerCase().equals(sectionName.toLowerCase()))
			.findFirst().orElse(null);
		CustomAssertions.assertSection(elementSection, sectionName);
		elementSection.click();
	}
}
