package pages.ru.yandex.market;

import helpers.CustomAssertions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static driver.WebDriverManager.getDriver;
import static helpers.Constant.COUNT_REPETITIONS;
import static helpers.properties.Properties.driverProperties;


/**
 * PageObject для работы с яндекс маркетом (каталог ноутбуки)
 */
public class YandexCatalogLaptop extends YandexMarketBase {
    private String selectorStartPrice = "//label[contains(text(),'Цена, ₽ от')]/../descendant::input";
    private String selectorFinalPrice = "//label[contains(text(),'Цена, ₽ до')]/../descendant::input";
    private String selectorLoadingCenter = "//div[@data-zone-name='SearchSerp']//span[@aria-label]";
    private String selectorLoadingFilters = "//div[@data-zone-name='SearchFilters']//span[@aria-label]";
    private String selectorShowAll = "//fieldset//span[text()='Показать всё']";
    private String selectorManufacturers = "//div[@data-test-id='virtuoso-scroller']//div[@data-index]//span[text()]";
    private String selectorProduct = "//article[@class]";
    private String selectorTitleProduct = ".//h3//a";
    private String selectorButtonNext = "//span[contains(text(),'Вперёд')]";
    private String selectorPriceProduct = ".//span[@data-auto='mainPrice']";
    private String selectorSearchString = "//input[@placeholder='Искать товары']";
    private String selectorButtonSearch = "//button//span[text()='Найти']";

    /**
     * По умолчанию, количество прокручиваний страницы
     */
    private final Integer defaultCount = 0;
    /**
     * По умолчанию, сохраненный элемент в аргумент метода
     */
    private final String defaultSaveElements = "";

    /**
     * Метод устанавливает минимальную и максимальную цену товаров
     *
     * @param startPrice Цена от
     * @param finalPrice Цена до
     * @see WaitUtils#waitVisibilityOf(String)
     * @see WaitUtils#waitInvisibilityOf(String)
     */
    public void setAPrice(String startPrice, String finalPrice) {
        WaitUtils.waitVisibilityOf(selectorFinalPrice);
        getDriver().findElement(By.xpath(selectorStartPrice)).sendKeys(startPrice);
        getDriver().findElement(By.xpath(selectorFinalPrice)).sendKeys(finalPrice);
        WaitUtils.waitInvisibilityOf(selectorLoadingCenter);
    }

    /**
     * Метод прокручивает страницу вниз и устанавливает производителей товаров
     * Смысл метода - рекурсия. Будет вызывать сам себя, пока не прокрутит до
     * последнего элемента.
     * Цикл for нужен для того, чтобы узнать, какие модели из списка manufacturers
     * подошли и удалось по ним кликнуть, на какие не удалось, попадут в Assert
     * if (!saveEndElement.equals(lastElAfterScroll.getText())) Если название
     * сохраненного элемента не сходится с последним названием из
     * списка на момент прокрутки, крутим ползунок вниз и делаем рекурсию.
     *
     * @param manufacturers  список производителей
     * @param saveEndElement последнее сохранённое значение на момент прокрутки
     * @param count          количество срабатываний метода
     * @see YandexCatalogLaptop#presenceElement(String)
     * @see YandexCatalogLaptop#selectManufacturer(ArrayList, String, Integer)
     * @see WaitUtils#waitInvisibilityOf(String) 
     */
    public void selectManufacturer(ArrayList<String> manufacturers, String saveEndElement, Integer count) {
        if (presenceElement(selectorShowAll)) {
            getDriver().findElement(By.xpath(selectorShowAll)).click();
            WaitUtils.waitInvisibilityOf(selectorLoadingFilters);
        }
        CustomAssertions.assertTrue(count <= COUNT_REPETITIONS,
                "Произошло зацикливание! Количество попыток прокрутить страницу вниз больше " + COUNT_REPETITIONS + " раз");

        List<WebElement> listModel = getDriver().findElements(By.xpath(selectorManufacturers));
        for (int e = 0; e < manufacturers.size(); e++) {
            String modelName = manufacturers.get(e);
            WebElement elModel = listModel.stream()
                    .filter(l -> l.getText().trim().equals(modelName.trim()))
                    .findFirst().orElse(null);
            if (Objects.nonNull(elModel)) {
                elModel.click();
                manufacturers.remove(e);
            }
        }

        WebElement lastElAfterScroll = listModel.get(listModel.size() - 1);
        if (!saveEndElement.equals(lastElAfterScroll.getText())) {
            JavascriptExecutor je = (JavascriptExecutor) getDriver();
            je.executeScript("arguments[0].scrollIntoView(true);", lastElAfterScroll);
            selectManufacturer(manufacturers, lastElAfterScroll.getText(), count + 1);
        }
        CustomAssertions.assertTrue(manufacturers.isEmpty(),
                "Производитель с моделью " + manufacturers + " отсутствует в выборочном списке");
    }

    /**
     * Дождаться результатов поиска
     */
    public void waitsResultSearch() {
        if (presenceElement(selectorLoadingCenter)) {
            WaitUtils.waitInvisibilityOf(selectorLoadingCenter);
        }
    }

    /**
     * Метод вызывает внутренний приватный метод, который прокручивает страницу
     * вниз и проверяет, что элементов на странице больше {countElementsOnPage}
     *
     * @param countElementsOnPage минимальное количество элементов на странице
     * @see YandexCatalogLaptop#productsOnPage(HashMap, String, Integer) 
     */
    public void checkCountElements(Integer countElementsOnPage) {
        HashMap<String, WebElement> allProductsOnPage = productsOnPage(new HashMap<>(), defaultSaveElements, defaultCount);
        CustomAssertions.assertTrue(allProductsOnPage.size() >= countElementsOnPage,
                "Количество элементов на странице меньше " + countElementsOnPage);
    }

    /**
     * Метод проверяет, что товары на всех страницах соответствуют заданным фильтрам.
     * Основные элементы, это значения allProductsOnPage. Ключи - title элементов
     * Если какая-нибудь из моделей соответствует ключу, то result = true
     *
     * @param startPrice    начальная цена
     * @param finalPrice    конечная цена
     * @param manufacturers список производителей
     * @see YandexCatalogLaptop#checkCompliance(String, String, ArrayList)
     * @see YandexCatalogLaptop#presenceElement(String) проверить наличие кнопки "Вперед"
     * @see YandexCatalogLaptop#productsOnPage(HashMap, String, Integer) Прокрутить страницу вниз и получить данные товаров
     */
    public void checkCompliance(String startPrice, String finalPrice, ArrayList<String> manufacturers) {
        HashMap<String, WebElement> allProductsOnPage = productsOnPage(new HashMap<>(), defaultSaveElements, defaultCount);
        String urlOfPage = getDriver().getCurrentUrl();
        Integer numberPage = urlOfPage.contains("page") ? Integer.valueOf(urlOfPage.substring(urlOfPage.indexOf("page")).replaceAll("[^0-9]", "")) : 1;

        for (String title : allProductsOnPage.keySet()) {
            String urlOnProduct = allProductsOnPage.get(title).findElement(By.xpath(selectorTitleProduct)).getAttribute("href");
            CustomAssertions.assertTrue(manufacturers.stream().anyMatch(s -> title.toLowerCase().contains(s.toLowerCase())),
                    "На странице №" + numberPage + " товар по ссылке: " + urlOnProduct + " не содержит в названии одну из моделей " + manufacturers);

            Double priceProduct = Double.valueOf(allProductsOnPage.get(title).findElement(By.xpath(selectorPriceProduct)).getText().replaceAll("[^0-9]", ""));
            CustomAssertions.assertTrue(priceProduct >= Double.valueOf(startPrice) && priceProduct <= Double.valueOf(finalPrice),
                    "Диапазон цены от " + startPrice + " до " + finalPrice + ". На странице №" + numberPage +
                            " находится товар с неверной ценой. Ссылка на товар : " + urlOnProduct);
        }

        if (presenceElement(selectorButtonNext)) {
            getDriver().findElement(By.xpath(selectorButtonNext)).click();
            WaitUtils.waitInvisibilityOf(selectorLoadingCenter);
            checkCompliance(startPrice, finalPrice, manufacturers);
        }
    }

    /**
     * Метод прокручивает страницу вниз и парсит все товары
     * Ключ - заголовок товара
     * Значение - весь веб элемент с ним, чтобы можно было по xpath добраться к любому элементу
     * if (!saveEndElement.equals(lastElAfterScrollStr)) Если название
     * сохраненного элемента не сходится с последним названием из
     * списка на момент прокрутки, крутим ползунок вниз и делаем рекурсию.
     * @param allProductsOnPage при рекурсии уже будет передаваться сохраненное значение
     * @param saveEndElement заголовок последнего элемента на момент прокрутки страницы
     * @param count количество вызовов метода
     * @see YandexCatalogLaptop#productsOnPage(HashMap, String, Integer) 
     * @see WaitUtils#waitVisibilityOfElementLocated(String)
     * @return результаты со страницы
     */
    public HashMap<String, WebElement> productsOnPage(HashMap<String, WebElement> allProductsOnPage, String saveEndElement, Integer count) {
        CustomAssertions.assertTrue(count <= COUNT_REPETITIONS,
                "Произошло зацикливание! Количество попыток прокрутить страницу вниз больше " + COUNT_REPETITIONS + " раз");
        WaitUtils.waitVisibilityOfElementLocated(selectorProduct);

        List<WebElement> products = getDriver().findElements(By.xpath(selectorProduct));
        WebElement lastElAfterScroll = products.get(products.size() - 1);
        String lastElAfterScrollStr = lastElAfterScroll.findElement(By.xpath(selectorTitleProduct)).getText();
        if (!saveEndElement.equals(lastElAfterScrollStr)) {
            JavascriptExecutor je = (JavascriptExecutor) getDriver();
            je.executeScript("arguments[0].scrollIntoView(true);", lastElAfterScroll);
            products.stream().forEach(webElement -> {
                allProductsOnPage.put(
                        webElement.findElement(By.xpath(selectorTitleProduct)).getText(),
                        webElement
                );
            });
            productsOnPage(allProductsOnPage, lastElAfterScrollStr, count + 1);
        }
        return allProductsOnPage;
    }

    /**
     * Метод возвращается на первую страницу
     * @param numberPage номер страницы, на которую требуется вернуться
     * @return возвращает первое значение с первой страницы
     * @see WaitUtils#waitVisibilityOfElementLocated(String)
     */
    public String backOnPage(Integer numberPage) {
        getDriver().get(getDriver().getCurrentUrl() + "&page=" + numberPage);
        WaitUtils.waitVisibilityOfElementLocated(selectorProduct);
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
     * @see YandexCatalogLaptop#productsOnPage(HashMap, String, Integer)
     */
    public void checkResult(String titleFirstElement) {
        HashMap<String, WebElement> allProductsOnPage = productsOnPage(new HashMap<>(), defaultSaveElements, defaultCount);
        CustomAssertions.assertTrue(allProductsOnPage.entrySet().stream().anyMatch(k -> k.getKey().contains(titleFirstElement)),
                "В результатах поиска, товара с названием " + titleFirstElement + " не обнаружено");
    }

    /**
     * Метод проверяет, присутствует ли веб элемент
     * с определенным xpath на странице
     *
     * @param xpath веб элемент со страницы
     */
    private Boolean presenceElement(String xpath) {
        getDriver().manage().timeouts().implicitlyWait(driverProperties.smallTimeout(), TimeUnit.SECONDS);
        Boolean result = getDriver().findElements(By.xpath(xpath)).isEmpty() ? false : true;
        getDriver().manage().timeouts().implicitlyWait(driverProperties.defaultTimeout(), TimeUnit.SECONDS);
        return result;
    }
}
