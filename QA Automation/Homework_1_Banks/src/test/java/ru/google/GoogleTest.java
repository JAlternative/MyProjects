package ru.google;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;
import pages.GoogleAfterSearch;
import pages.GoogleBeforeSearch;
import pages.OpenBankPageObject;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Задание 1_1, 1_2
 *
 * @author Сергей Хорошков
 */
@DisplayName("Задания: №1_1, №1_2")
public class GoogleTest extends BaseTest {

    /**
     * Конструктор по умолчанию
     */
    public GoogleTest() {

    }

    /**
     * Ввести слово гладиолус в поисковик и проверить, что в результатах поиска содержится ссылка на википедию
     *
     * @param word слово, которое будем вводить в поисковик google
     * @param url  ссылка, который должна содержаться в результатах поиска
     */

    @DisplayName("Task1_1. Проверка на содержание ссылки на википедию после вводимого слова")
    @ParameterizedTest
    @MethodSource("utils.DataProvider#provideSearchGoogle")
    public void gladiolusTest(String word, String url) {
        driver.get("https://www.google.com/");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(driver);
        googleBeforeSearch.find(word);
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(driver);
        Assertions.assertTrue(
                googleAfterSearch.getResultsLink().stream().anyMatch(link -> link.contains(url)),
                "В результатах поиска, найденных после ввода слова " + word + ", страницы, содержащей ссылку на сайт " + url + " не найдено.");
    }


    /**
     * Ищем через поисковик google банк, переходим по ссылке и считываем курсы валют.
     * Тестируем, что курс продажи больше курса покупки
     *
     * @param word         слово, которое вводим в поисковик
     * @param header       заголовок, который будем искать на странице с результатами поиска
     * @param bankSells    по какому курсу банк продает валюту
     * @param bankBuy      по какому курсу банка покупает валюту
     * @param currencyList список валют
     */
    @DisplayName("Task1_2. Банк открытие, проверка курса валют")
    @ParameterizedTest
    @MethodSource("utils.DataProvider#provideOpenBank")
    public void openBankTest(String word, String header, String bankSells, String bankBuy, List<String> currencyList) {
        driver.get("https://www.google.com/");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(driver);
        googleBeforeSearch.find(word);
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(driver);
        WebElement element = googleAfterSearch.getResultSearch(header);
        Assertions.assertTrue(
                Objects.nonNull(element),
                "На странице поиска, элемента с заголовком «" + header + "» не найдено!");
        element.click();

        OpenBankPageObject openBankPageObject = new OpenBankPageObject(driver);
        List<Map<String, String>> tableBanks = openBankPageObject.getTableBanks();

        currencyList.forEach(currency -> {
            Double sells = openBankPageObject.getCurrencyExchangeRate(tableBanks, currency, bankSells);
            Double buys = openBankPageObject.getCurrencyExchangeRate(tableBanks, currency, bankBuy);
            Assertions.assertTrue(sells > buys,
                    "Ошибка курса валюты " + currency + ". Курс покупки " + buys + " не может быть больше курса продажи " + sells);
        });
    }
}
