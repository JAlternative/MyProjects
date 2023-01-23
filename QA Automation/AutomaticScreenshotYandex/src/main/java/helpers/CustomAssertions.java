package helpers;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

import static helpers.properties.Properties.mainProperties;

/**
 * Класс проверок
 *
 * @author Сергей Хорошков
 **/
public class CustomAssertions {

	/**
	 * Конструктор по умолчанию
	 */
	public CustomAssertions() {

	}

	/**
	 * Шаг 2.1
	 *
	 * @param serviceElement веб-элемент с главной странице яндекса
	 * @param serviceName    название сервиса, который мы ищем на главной странице яндекса
	 */
	@Step("Проверить, что сервис с названием {serviceName} существует на главной странице яндекса")
	public static void assertServiceName(WebElement serviceElement, String serviceName) {
		Assertions.assertTrue(Objects.nonNull(serviceElement),
			"Сервис с названием «" + serviceName + "» отсутствует на главной странице яндекса");
	}

	/**
	 * Шаг 3.1
	 *
	 * @param product         веб элемент категории с яндекс маркета
	 * @param productCategory категория товара из списка
	 */
	@Step("Проверить, что категория товара, выпадающего списка слева, с названием {productCategory} находится в каталоге яндекс маркета")
	public static void assertProductCategory(WebElement product, String productCategory) {
		Assertions.assertTrue(Objects.nonNull(product),
			"Категории товара в каталоге с названием «" + productCategory + "» не найдено.");
	}

	/**
	 * Шаг 4.1
	 *
	 * @param elementSection веб элемент секции с яндекс маркета
	 * @param sectionName    подкатегория товара из списка
	 */
	@Step("Проверить, что подкатегория товара, выпадающего списка справа, с названием {sectionName} находится в каталоге яндекс маркета")
	public static void assertSection(WebElement elementSection, String sectionName) {
		Assertions.assertTrue(Objects.nonNull(elementSection),
			"Подкатегории товара с названием «" + sectionName + "» не найдено.");
	}

	/**
	 * Шаг 6.1
	 *
	 * @param copyManufacturers список моделей, которые не найдены на странице яндекс маркета
	 */
	@Step("Проверить, что производители заданные в параметрах присутствуют в выборочном списке на странице")
	public static void assertManufacturer(ArrayList<String> copyManufacturers) {
		Assertions.assertTrue(copyManufacturers.isEmpty(),
			"Производитель с моделью " + copyManufacturers + " отсутствует в выборочном списке");
	}

	/**
	 * Шаг 8.1
	 *
	 * @param allProductsOnPage Ключ - title продукта, значение - //article на странице
	 */
	@Step("Проверить, что на странице отображалось более 12 элементов")
	public static void assertTwelveElementsOnPage(LinkedHashMap<String, WebElement> allProductsOnPage) {
		Assertions.assertTrue(allProductsOnPage.size() >= mainProperties.countElementsOnPage(),
			"Количество элементов на странице меньше " + mainProperties.countElementsOnPage());
	}

	/**
	 * Шаг 9.1
	 *
	 * @param result        переменная, проверяющая соответствие моделей
	 * @param numberPage    номер страницы, на которой осуществляется проверка
	 * @param manufacturers список производителей
	 * @see pages.ru.yandex.market.YandexCatalogLaptop#checkCompliance(String, String, ArrayList)
	 */
	@Step("Проверить, что товар на странице содержит в оглавлении название производителя {manufacturers}")
	public static void assertNameProductOnPage(Boolean result, Integer numberPage, ArrayList<String> manufacturers) {
		Assertions.assertTrue(result,
			"Товар на странице: " + numberPage + " не содержит строки " + manufacturers);
	}

	/**
	 * Шаг 9.2
	 *
	 * @param price      цена товара на странице
	 * @param startPrice начальная цена
	 * @param finalPrice конечная цена
	 * @param title      название товара
	 * @param numberPage номер страницы
	 * @see pages.ru.yandex.market.YandexCatalogLaptop#checkCompliance(String, String, ArrayList)
	 */
	@Step("Проверить, что товар на странице соответствует диапазону цен. От {startPrice} до {finalPrice}")
	public static void assertPricesProductOnPage(Double price, String startPrice, String finalPrice, String title, Integer numberPage) {
		Assertions.assertTrue(price >= Double.valueOf(startPrice) && price <= Double.valueOf(finalPrice),
			"Диапазон цены от " + startPrice + " до " + finalPrice + ". На странице №" + numberPage +
				" находится товар с неверной ценой. Название товара: " + title);
	}

	/**
	 * Шаг №13.1
	 *
	 * @param titleFirstElement яндекс маркет (каталог ноутбуки)
	 * @param allProductsOnPage Ключ - title продукта, значение - //article на странице
	 */
	@Step("Проверить, что в результатах поиска есть товар с наименованием равным сохраненному значению {titleFirstElement}")
	public static void assertCheckResult(String titleFirstElement, LinkedHashMap<String, WebElement> allProductsOnPage) {
		Assertions.assertTrue(allProductsOnPage.entrySet().stream().anyMatch(k -> k.getKey().contains(titleFirstElement)),
			"В результатах поиска, товара с названием " + titleFirstElement + " не обнаружено");
	}
}
