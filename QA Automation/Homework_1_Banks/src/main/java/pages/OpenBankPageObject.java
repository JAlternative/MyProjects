package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Page Object главной страницы «Банк открытие»
 *
 * @author Сергей Хорошков
 */
public class OpenBankPageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Конструктор для инициализации драйвера
     *
     * @param driver текущее состояние драйвера для работы с браузером
     */
    public OpenBankPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 120);
    }

    private String selectorFullTable = "//table[contains(@class,'main-page-exchange')]";
    private String selectorHeadersTable = "//tr[contains(@class, 'header')]";
    private String selectorRowsTable = "//tr[contains(@class, 'row')]";
    private String selectorNextColumns = "//td";

    /**
     * Метод собирает данные с таблицы в Map
     *
     * @return (ключи - заголовки, значения - данные о валютах) и кладёт их в список
     * Stream на 64 строке фильтрует HashMap так, чтобы пустые значения и слеши не добавлялись HashMap
     */
    public List<Map<String, String>> getTableBanks() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectorFullTable)));
        List<WebElement> headers = driver.findElements(By.xpath(selectorFullTable + selectorHeadersTable + selectorNextColumns));
        List<WebElement> rows = driver.findElements(By.xpath(selectorFullTable + selectorRowsTable));
        List<Map<String, String>> tableBanks = new ArrayList<>();

        for (int r = 0; r < rows.size(); r++) {
            Map<String, String> valuesTable = new HashMap<>();
            for (int h = 0; h < headers.size(); h++) {
                String xpathValue = selectorFullTable + selectorRowsTable +
                        "[" + (r + 1) + "]" + selectorNextColumns + "[" + (h + 1) + "]";

                valuesTable.put(
                        headers.get(h).getText(),
                        rows.get(r).findElement(By.xpath(xpathValue)).getText()
                );
            }

            tableBanks.add(
                    valuesTable.entrySet().stream()
                            .filter(f -> !f.getKey().equals("") && !f.getValue().equals("/") && !f.getKey().equals("/") && !f.getValue().equals(""))
                            .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()))
            );
        }
        return tableBanks;
    }

    /**
     * Метод, который вернет нам курс валюты, согласно передаваемым аргументам
     *
     * @param tableBanks    спарсенная таблица с сайта с курсами валют
     * @param currency      валюта, которую мы хотим получить
     * @param bankOperation операция, банк продает или покупает
     * @return возвращает курс валюты, согласно условиям, указанным в аргументе метода
     */
    public Double getCurrencyExchangeRate(List<Map<String, String>> tableBanks, String currency, String bankOperation) {
        return Double.valueOf(tableBanks.stream().filter(c -> c.get("Валюта обмена").contains(currency))
                .findFirst().get()
                .entrySet()
                .stream().filter(k -> k.getKey().equals(bankOperation))
                .findFirst().get().getValue().replaceAll(",", "."));
    }
}
