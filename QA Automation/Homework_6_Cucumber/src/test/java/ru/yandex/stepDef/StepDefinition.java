package ru.yandex.stepDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ru.yandex.YandexBasePage;
import pages.ru.yandex.YandexMainPage;
import pages.ru.yandex.market.CatalogSmartphones;
import pages.ru.yandex.market.YandexMarketHomePage;

import static custom.properties.Properties.urlProperties;

public class StepDefinition {
    private YandexMainPage yandexMainPage = new YandexMainPage();
    private YandexMarketHomePage yandexMarketHomePage = new YandexMarketHomePage();
    private CatalogSmartphones catalogSmartphones = new CatalogSmartphones();

    @Given("Открыть Яндекс")
    public void openBrowser() {
        YandexBasePage.goPage(urlProperties.urlYandex());
    }

    @When("Перейти на Яндекс '(.*)'")
    public void goToYandexService(String serviceName) {
        yandexMainPage.goToYandexService(serviceName);
    }

    @And("Перейти в Каталог -> Выбрать раздел '(.*)'")
    public void openProductSection(String productCategory) {
        yandexMarketHomePage.openProductSection(productCategory);
    }

    @And("Выбрать раздел '(.*)'")
    public void selectSection(String sectionName) {
        yandexMarketHomePage.selectSection(sectionName);
    }

    @And("Задать производителя '(.*)'")
    public void selectManufacturer(String manufacturer) {
        catalogSmartphones.selectManufacturer(manufacturer);
    }
    @And("Дождаться результатов поиска")
    public void waitResultSearch(){
        catalogSmartphones.waitResultSearch();
    }
    @Then("Убедиться, что в выборку попали только '(.*)'. Если страниц несколько – проверить все")
    public void checkCompliance(String word){
        catalogSmartphones.checkCompliance(word);
    }
}
