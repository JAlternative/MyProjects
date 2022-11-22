package pages.home_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;
import pages.base.Waits;
import pages.helpers.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YandexMarketHomePage extends BasePage {

    public YandexMarketHomePage(WebDriver driver) {
        super(driver);
    }
    /**
     * @param selectorElementYandexMarket - переход на яндекс маркет
     * @param selectorButtonCatalogInMarket - xpath для открытия каталога
     * @param selectorGetTableLeft - //li[@class] получаем левую табличку из каталога
     * @param selectorGetTableRight - получим полный правый квадрат
     * @param selectorButtonYet - кнопка еще в правой стороне таблицы, чтобы открыть все записи и потом получить все ссылки на технику
     * @param productListRight - получение списка товаров справа, ключ - название, ссылка на него - значение
     * @param mapsCatalog - всю страничку сюда соберем, ключ будет название левого столбца, значение, что выводится в правом
     * */
    private WebElement webElement;
    private final By selectorElementYandexMarket = By.xpath("//a[@data-id='market']");
    private final By selectorButtonCatalogInMarket = By.xpath("//button[@id = 'catalogPopupButton']");

    private String selectorGetTableLeft = "//div[@data-apiary-widget-name='@MarketNode/HeaderCatalog']";

    private String selectorGetTableRight = "//div[@data-apiary-widget-name='@MarketNode/NavigationTree']";
    private String selectorButtonYet = ".//li//span[contains(text(), 'Ещё')]";
    private Map<String, String> productListRight = new HashMap<>();

    public String getSelectorGetTableLeft() {
        return selectorGetTableLeft;
    }

    private List<Map<String, Map<String, String>>> mapsCatalog = new ArrayList<>();

    /**
     * @param - переходим на сайт яндекс маркета, но наверное нужно сделать, чтобы открывался в новом окне
     * */
    public void openSiteYandexMarket(String url) {
        open(url); //перейдём на сайт яндекса
        open(BasePage.getDriver().findElement(selectorElementYandexMarket).getAttribute("href"));
    }

    public List<Map<String, Map<String, String>>> getCatalog(String product) {

        webElement = BasePage.getDriver().findElement(selectorButtonCatalogInMarket); //тут программа падала
        Waits.waitListElementsIsVisible(webElement);
        webElement.click();
        Waits.implicitWait();

        String selectorLineName = selectorGetTableLeft + "//li[@class]//span[contains(text(), '" + product + "')]";

        WebElement valueTableLeft = null;
        try {
            valueTableLeft = BasePage.getDriver().findElement(By.xpath(selectorLineName)); //получаем значение из левого столбца
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("Не найдено в каталоге значение: " + product + " " + ex.getSupportUrl());
        }


        /**
         * передаём селектор в метод, на который будем наводить мышкой, чтобы окошко справа открылось
         * */
        String lineName = valueTableLeft.getText();

        if (valueTableLeft.getText().equals(product)) {
            mapsCatalog.add(Map.of(
                    lineName, rightTable(valueTableLeft)
            ));
        }

        return mapsCatalog;
    }


    public Map<String, String> rightTable(WebElement el) {

        Action.mouseMovement(el);

        if (el.findElement(By.xpath(selectorGetTableRight)).isDisplayed()) {
            Waits.waitElementIsVisible(el);
            clickOnButton(el);
        }

        /**
         * @param selectorRightElements - получим справа элементы, на которые будем потом переходить
         * */
        String selectorRightElements = selectorGetTableRight + "//a[@href]";
        List<WebElement> wb = BasePage.getDriver().findElements(By.xpath(selectorRightElements));


        /**
         * метод, который с правого столбца собирает данные. Ключ - имя товара, Значение - его Xpath, в другом методе будем кликать по нему
         * */
        wb.stream().forEach(l ->
        {
            String keyName = l.getText();
            String selectorValue = selectorGetTableRight + "//a[@href and text()='" + keyName + "']";
            productListRight.put(keyName, selectorValue);
        });

        return productListRight;
    }

    private void clickOnButton(WebElement el) {
        try {

            if (el.isDisplayed()) {
                el.findElement(By.xpath(selectorGetTableRight))
                        .findElements(By.xpath(selectorButtonYet))
                        .stream()
                        .forEach(e -> {
                            Waits.waitElementIsClickable(e).click();
                        }); //Прокликаем по кнопке "Ещё в правом столбце"
            }

        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            System.out.println(ex.getStackTrace() + " " + el.getText());
        }

    }


}
