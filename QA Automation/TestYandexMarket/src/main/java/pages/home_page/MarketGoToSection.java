package pages.home_page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;
import pages.base.Waits;

import java.util.Arrays;
import java.util.List;
/**
 * Переход в раздел ноутбуки
 * */
public class MarketGoToSection extends BasePage {

    public MarketGoToSection(WebDriver driver) {
        super(driver);
    }

    /**
     * @param grayWaiting - Когда страница грузится или обновляется, то выскакивает серый квадрат прогрузки
     * @param xpathAllElements - xpath выдаст все результаты поиска, после "Показать все"
     * */
    private String selectorManufacturerShowAll = "//div[@data-zone-data]//legend[text()='Производитель']/parent::fieldset//button[text()='Показать всё']";
    private String grayWaiting = "//div[@class='_2Lvbi _1oZmP']";
    private String xpathAllElements = "//article";
    private WebElement webElement;


    /**
     * Метод, который установит параметр цена startingPrice - стартовая, finalPrice конечная
    */
    public void setPriceParameter(Integer startingPrice, Integer finalPrice) {
        String selectorPriceStart = selectorButton("Цена от");

        webElement = BasePage.getDriver().findElement(By.xpath(selectorPriceStart));
        Waits.waitElementIsClickable(webElement);
        webElement.click();
        webElement.sendKeys(startingPrice.toString());

        String selectorPriceFinal = selectorButton("Цена до");
        webElement = BasePage.getDriver().findElement(By.xpath(selectorPriceFinal));
        Waits.waitElementIsClickable(webElement);
        webElement.click();
        webElement.sendKeys(finalPrice.toString());

    }
    /**
     * Передаётся массив элементов, в которые нужно поставить галочки
     * */
    public List<WebElement> setManufacturerParameter(String[] manufacturer, Integer elementsOnThePage) {

        webElement = BasePage.getDriver().findElement(By.xpath(selectorManufacturerShowAll));
        webElement.click();
        Arrays.stream(manufacturer).forEach(el -> {
            String selectorManufacturer = selectorButton(el);
            System.out.println("Дай: " + selectorManufacturer);
            try {
                if (BasePage.getDriver().findElement(By.xpath(selectorManufacturer)).isEnabled()) {
                    webElement = BasePage.getDriver().findElement(By.xpath(selectorManufacturer));
                    webElement.click();
                }
            } catch (org.openqa.selenium.NoSuchElementException ex) {
                Assertions.fail("Производителя под именем: " + el + " нет в данной ценовой категории или вовсе в списке " + ex.getSupportUrl());
            }

        });

        Waits.invisibilityOfElement(grayWaiting);
        return clickShow(elementsOnThePage);

    }

    private List<WebElement> clickShow(Integer number) {
        String selectorButton = "//button";
        String selectorSpan = "//span";
        String selectorText = "[contains(text(),'Показывать по')]";
        String selectorTextAndNumber = "[contains(text(),'Показывать по " + number + "')]";
        String selectorParent = "/parent::span/parent::button";

        /**
         * @param selectorBasicButton - Кликнем по кнопке "Показать по"
         * */
        String selectorBasicButton = selectorButton + selectorSpan + selectorText + selectorParent;

        webElement = BasePage.getDriver().findElement(By.xpath(selectorBasicButton));
        webElement.click();

        String selectorTwoButtons = selectorButton + selectorTextAndNumber;
        webElement = BasePage.getDriver().findElement(By.xpath(selectorTwoButtons));
        webElement.click();
        Waits.invisibilityOfElement(grayWaiting);
        List<WebElement> firstResultPage = getDriver().findElements(By.xpath(xpathAllElements));
        return firstResultPage;
    }

    public String searchResult(String titleProductFirst){

        String selectorSearch = "//input[@type='text' and @itemprop]";
        webElement = BasePage.getDriver().findElement(By.xpath(selectorSearch));
        webElement.click();
        webElement.sendKeys(titleProductFirst);

        String selectorButtonFindPage = "//button//span[text() ='Найти']"; //найти
        BasePage.getDriver().findElement(By.xpath(selectorButtonFindPage)).click();
        Waits.invisibilityOfElement(grayWaiting);

        List<WebElement> secondResultPage = getDriver().findElements(By.xpath(xpathAllElements));
        String titleProductSecond = secondResultPage.get(0).findElement(By.xpath("//h3")).getText();
        return titleProductSecond;

    }

    private String selectorButton(String buttonValue) {
        String[] concatValue = new String[3];
        if (buttonValue.contains("Цена")) {
            concatValue[0] = "//input[@name='";
            concatValue[1] = buttonValue;
            concatValue[2] = "']";
        } else {
            concatValue[0] = "//ul//input[not(@disabled) and contains(@name,'";
            concatValue[1] = buttonValue;
            concatValue[2] = "')]/parent::label";
            System.out.println("Нам нужен xpath " + concatValue[0] + concatValue[1] + concatValue[2]);
        }

        String selectorButton = concatValue[0] + concatValue[1] + concatValue[2];
        return selectorButton;
    }




}
