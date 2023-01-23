package pages.ru.yandex.market;

import helpers.CustomAssertions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static driver.WebDriverManager.getDriver;
import static helpers.properties.Properties.mainProperties;


/**
 * PageObject для работы с яндекс маркетом (каталог ноутбуки)
 */
public class YandexCatalogLaptop {
	/**
	 * Конструктор по умолчанию
	 */
	public YandexCatalogLaptop() {
	}

	private String selectorStartPrice = "//label[contains(text(),'Цена, ₽ от')]/../descendant::input";
	private String selectorFinalPrice = "//label[contains(text(),'Цена, ₽ до')]/../descendant::input";
	private String selectorLoadingCenter = "//div[@data-zone-name='SearchSerp']//span[@aria-label]";
	private String selectorLoadingFilters = "//div[@data-zone-name='SearchFilters']//span[@aria-label]";
	private String selectorShowAll = "//fieldset//span[text()='Показать всё']";
	private String selectorManufacturers = "//div[@data-test-id='virtuoso-scroller']//div[@data-index]";
	private String selectorProduct = "//article[@class]";
	private String selectorTitleProduct = ".//h3//a";
	private String selectorButtonNext = "//span[contains(text(),'Вперёд')]";
	private String selectorPriceProduct = ".//span[@data-auto='mainPrice']";
	private String selectorSearchString = "//input[@placeholder='Искать товары']";
	private String selectorButtonSearch = "//button//span[text()='Найти']";
	private String selectorText = "//span[text()]";
	/**
	 * Url страниц с результатами
	 */
	private List<String> urlsPages = new ArrayList<>();

	/**
	 * Элементы страницы. Ключ - title продукта,
	 * значение - //article на странице
	 */
	private LinkedHashMap<String, WebElement> allProductsOnPage = new LinkedHashMap<>();

	/**
	 * Геттер, для работы с селектором в степе
	 *
	 * @return селектор, для работы с ожиданием загрузки элементов в середине страницы
	 */
	public String getSelectorLoadingCenter() {
		return selectorLoadingCenter;
	}

	/**
	 * Метод устанавливает минимальную и максимальную цену товаров
	 *
	 * @param startPrice Цена от
	 * @param finalPrice Цена до
	 */
	public void setAPrice(String startPrice, String finalPrice) {
		WaitUtils.waitVisibilityOf(selectorFinalPrice);
		getDriver().findElement(By.xpath(selectorStartPrice)).sendKeys(startPrice);
		getDriver().findElement(By.xpath(selectorFinalPrice)).sendKeys(finalPrice);
		WaitUtils.waitInvisibilityOf(selectorLoadingCenter);
	}

	/**
	 * Метод устанавливает производителя товаров
	 * endElement - сохраняем последний элемент на момент прокрутки ползунка,
	 * чтобы когда прокрутим список до конца, сравнить последние элементы и закрыть цикл.
	 * Т.е. весь список прокрутили вниз.
	 * count - уберём зацикливание
	 * lastElAfterScroll - последний элемент на момент прокрутки.
	 * Вложенный цикл while работает так, что если мы кликаем по нужному нам элементу, то после сразу же удаляем его из
	 * copyManufacturers, для того, чтобы, когда будем делать Assertions было видно, какие аргументы не соответствуют списку производителей.
	 *
	 * @param manufacturers список производителей
	 */

	public void selectManufacturer(ArrayList<String> manufacturers) {
		getDriver().findElement(By.xpath(selectorShowAll)).click();
		ArrayList<String> copyManufacturers = new ArrayList<>(manufacturers);
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		WaitUtils.waitInvisibilityOf(selectorLoadingFilters);
		String endElement = "";
		Integer count = 0;
		while (count <= mainProperties.countOfRepetitions()) {
			WebElement scrollTo = getDriver().findElement
				(By.xpath(selectorManufacturers + "[last()]"));
			je.executeScript("arguments[0].scrollIntoView(true);", scrollTo);
			List<WebElement> listModel = getDriver().findElements(By.xpath(selectorManufacturers + selectorText));
			String lastElAfterScroll = listModel.stream().reduce((el, el2) -> el2).get().getText();
			ListIterator<String> itManufactures = copyManufacturers.listIterator();
			while (itManufactures.hasNext()) {
				String iterator = itManufactures.next();
				WebElement elModel = listModel.stream().filter(l -> l.getText().trim().equals(iterator.trim())).findFirst().orElse(null);
				if (Objects.nonNull(elModel)) {
					elModel.click();
					itManufactures.remove();
				}
			}

			if (endElement.equals(lastElAfterScroll)) break;
			endElement = lastElAfterScroll;
			count++;
		}
		CustomAssertions.assertManufacturer(copyManufacturers);
	}

	/**
	 * Метод вызывает внутренний приватный метод, который прокручивает страницу
	 * вниз и проверяет, что элементов на странице больше 12
	 *
	 * @see YandexCatalogLaptop#productsOnPage()
	 */
	public void twelveElementsCheck() {
		productsOnPage();
		CustomAssertions.assertTwelveElementsOnPage(allProductsOnPage);
	}

	/**
	 * Метод проверяет, что товары на всех страницах соответствуют заданным фильтрам.
	 * Основные элементы, это значения allProductsOnPage. Ключи - title элементов
	 * Если какая-нибудь из моделей соответствует ключу, то result = true
	 *
	 * @param startPrice    начальная цена
	 * @param finalPrice    конечная цена
	 * @param manufacturers список производителей
	 */
	public void checkCompliance(String startPrice, String finalPrice, ArrayList<String> manufacturers) {
		urlsPages.add(getDriver().getCurrentUrl());
		Boolean result = false;
		for (String title : allProductsOnPage.keySet()) {
			for (String model : manufacturers) {
				if (title.toLowerCase().contains(model.toLowerCase())) {
					result = true;
				}
			}
			CustomAssertions.assertNameProductOnPage(result, urlsPages.size() + 1, manufacturers);
			result = false;

			Double price = Double.valueOf(
				allProductsOnPage.get(title).findElement(By.xpath(selectorPriceProduct))
					.getText().replaceAll("[^0-9]", ""));
			CustomAssertions.assertPricesProductOnPage(price, startPrice, finalPrice, title, urlsPages.size() + 1);
		}

		getDriver().manage().timeouts().implicitlyWait(mainProperties.smallTimeout(), TimeUnit.SECONDS);
		if (!getDriver().findElements(By.xpath(selectorButtonNext)).isEmpty()) {
			getDriver().findElement(By.xpath(selectorButtonNext)).click();
			WaitUtils.waitInvisibilityOf(selectorLoadingCenter);
			productsOnPage();
			checkCompliance(startPrice, finalPrice, manufacturers);
		}
		getDriver().manage().timeouts().implicitlyWait(mainProperties.defaultTimeout(), TimeUnit.SECONDS);
	}

	/**
	 * Метод прокручивает страницу вниз и парсит все товары
	 * Ключ - заголовок товара
	 * Значение - весь веб элемент с ним, чтобы можно было по xpath добраться к любому элементу
	 * clear() делаем для того, чтобы, если будем переключаться на следующую страницу и вызывать
	 * данный метод, очистить коллекцию и добавить в неё новые элементы
	 */
	public void productsOnPage() {
		allProductsOnPage.clear();
		WaitUtils.waitVisibilityOfElementLocated(selectorProduct);
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		Integer count = 0;
		String endElement = "";
		while (count <= mainProperties.countOfRepetitions()) {
			List<WebElement> products = getDriver().findElements(By.xpath(selectorProduct));
			je.executeScript("window.scrollBy(0,1000)");
			products.stream().forEach(webElement -> {
				allProductsOnPage.put(
					webElement.findElement(By.xpath(selectorTitleProduct)).getAttribute("title"),
					webElement
				);
			});
			String lastElAfterScroll = allProductsOnPage.entrySet()
				.stream().reduce((first, second) -> second).orElse(null).getKey();
			if (Objects.isNull(lastElAfterScroll) || endElement.equals(lastElAfterScroll)) break;
			endElement = lastElAfterScroll;
		}
	}


	/**
	 * Метод возвращается на первую страницу
	 *
	 * @return возвращает первое значение с первой страницы
	 */
	public String backOnFirstPage() {
		getDriver().get(urlsPages.get(0));
		return getDriver().findElement(
			By.xpath(selectorProduct + selectorTitleProduct.replace(".", ""))).getAttribute("title");
	}

	/**
	 * Метод берёт название из сохраненного значения и вводит в поисковую строку
	 *
	 * @param titleFirstElement сохранённое значение названия продукта
	 */
	public void enterValue(String titleFirstElement) {
		getDriver().findElement(By.xpath(selectorSearchString)).sendKeys(titleFirstElement);
	}

	/**
	 * Клик по кнопке найти
	 */
	public void clickOnButtonSearch() {
		getDriver().findElement(By.xpath(selectorButtonSearch)).click();
	}

	/**
	 * Метод собирает результаты поисков в Map и проверяет,
	 * что в результатах поиска есть значение {titleFirstElement}
	 *
	 * @param titleFirstElement сохранённое значение названия продукта
	 */
	public void checkResult(String titleFirstElement) {
		productsOnPage();
		CustomAssertions.assertCheckResult(titleFirstElement, allProductsOnPage);
	}
}
