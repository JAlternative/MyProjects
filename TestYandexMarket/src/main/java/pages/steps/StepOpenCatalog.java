package pages.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;
import pages.home_page.MarketGoToSection;
import pages.home_page.YandexMarketHomePage;

import java.util.List;
import java.util.Map;

import static pages.helpers.Action.mouseMovement;

public class StepOpenCatalog {
    private YandexMarketHomePage yandexMarketHomePage = new YandexMarketHomePage(BasePage.getDriver());
    private MarketGoToSection marketGoToSection = new MarketGoToSection(BasePage.getDriver());
    private WebDriver driver = BasePage.getDriver();
    @Step("Перейти на страницу {url} и попасть на яндекс маркет")
    public void goPage(String url) {
        yandexMarketHomePage.openSiteYandexMarket(url);
    }

    private String rightTableValue;

    @Step("Проверка раздела слева: {sectionTitleLeft} и переход по подразделу справа {sectionTitleRight}")
    public void enterProduct(String sectionTitleLeft, String sectionTitleRight) {
        List<Map<String, Map<String, String>>> mapsCatalog = yandexMarketHomePage.getCatalog(sectionTitleLeft);

        mapsCatalog.stream().forEach(k ->
        {
            if (k.containsKey(sectionTitleLeft)) {
                Map<String, String> valueRight = k.get(sectionTitleLeft);

                if (valueRight.containsKey(sectionTitleRight)) {
                    rightTableValue = valueRight.get(sectionTitleRight);
                } else {
                    Assertions.fail("В правой части таблицы не найдено значение: " + sectionTitleRight);
                }


            } else {
                Assertions.fail("В левой части таблицы не найдено значение: " + sectionTitleLeft);
            }
        });
        /**
         * @param selectorTextLeft - левый элемент
         * @param selectorParseLeftTableValue - xpath для наведения элемента слева
         * */
        String selectorTextLeft = "//span[contains(text(), '" + sectionTitleLeft + "')]";
        String selectorParseLeftTableValue = yandexMarketHomePage.getSelectorGetTableLeft() + selectorTextLeft;
        System.out.println(selectorParseLeftTableValue + " МОЙ ИКСПАС");
        mouseMovement(driver.findElement(By.xpath(selectorParseLeftTableValue)));
        driver.findElement(By.xpath(rightTableValue)).click();
    }

    @Step("Ввод названия производителей: {manufacturer} и отобразить по {elementsOnThePage} на странице")
    public void enterNoteBooks(String[] manufacturer, Integer elementsOnThePage){
        List<WebElement> elementsNoteBooks = marketGoToSection.setManufacturerParameter(manufacturer, elementsOnThePage);
        Assertions.assertTrue(elementsNoteBooks.size() == elementsOnThePage, "Количество элементов на странице не соответствует: " + elementsOnThePage);
        searchProduct(elementsNoteBooks);
    }

    @Step("Проверка, что наименование первого товара со страницы соответствует первому значению после поиска")
    public void searchProduct(List<WebElement> firstResultPage){
        String titleProductFirst = firstResultPage.get(0).findElement(By.xpath("//h3")).getText();
        String titleProductSecond = marketGoToSection.searchResult(titleProductFirst);
        System.out.println("Первый производитель: " + titleProductFirst);
        System.out.println("Второй производитель: " + titleProductSecond);
        Assertions.assertTrue(titleProductFirst.equals(titleProductSecond), "Первый элемент с первой страницы не соответствует элементу со второй страницы");
    }

}
