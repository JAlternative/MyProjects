package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Factory яндекса
 *
 * @author Сергей Хорошков
 */
public class PageFactoryYandex {

    /**
     * Конструктор по умолчанию
     */
    public PageFactoryYandex() {

    }

    @FindBy(xpath = "//input[@aria-label='Запрос']")
    private WebElement searchField;
    @FindBy(xpath = "//button[@class and text()='Найти']")
    private WebElement searchButton;
    @FindBy(xpath = "//li[contains(@class,'card')]//h2//span")
    private List<WebElement> headers;

    /**
     * Метод для взаимодействия с результатами поиска на странице
     *
     * @param header - Заголовок сайта, на который нужно будет перейти
     * @return возвращает элемент, по которому нам нужно будет кликнуть на странице поиска
     */
    public WebElement getResult(String header) {
        List<WebElement> elements = headers.stream().filter(t -> !t.getText().equals("")).collect(Collectors.toList());
        return elements.stream().filter(e -> e.getText().equals(header)).findFirst().orElse(null);
    }

    /**
     * Главая страница поисковика
     *
     * @param word Слово, которое нужно ввести в поисковике
     *             1. Кликни по полю поиска
     *             2. Введи слово
     *             3. Нажми поиск
     */
    public void find(String word) {
        searchField.click();
        searchField.sendKeys(word);
        searchButton.click();
    }
}
