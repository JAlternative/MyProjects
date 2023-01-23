package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Результаты поиска google
 * @author Сергей Хорошков
 * */
public class GoogleAfterSearch {

    private WebDriver driver;

    /**
     * Конструктор для инициализации драйвера
     * @param driver текущее состояние драйвера для работы с браузером
     */
    public GoogleAfterSearch(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * headersLocator получим заголовки элементов, после результатов поиска
     */
    private final String selectorHeaders = "//div[@lang]//h3";

    /**
     * Метод для работы с результатами поиска
     *
     * @return ссылки, из результатов поиска с первой страницы
     */
    public List<String> getResultsLink() {
        String selectorUrlsResult = selectorHeaders + "//..//..//a[@href]";
        return driver.findElements(By.xpath(selectorUrlsResult))
                .stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
    }

    /**
     * Метод смотрит результаты поиска с первой страницы google
     * и выбирает веб элемент, заголовок которого равен нашему аргументу header
     *
     * @param header искомый заголовок, по которому будет осуществлён клик
     * @return элемент, по которому нужно будет кликнуть
     */
    public WebElement getResultSearch(String header) {
           return driver.findElements(By.xpath(selectorHeaders)).stream()
                .filter(e -> e.getText().equals(header))
                .findFirst().orElse(null);
    }
}
