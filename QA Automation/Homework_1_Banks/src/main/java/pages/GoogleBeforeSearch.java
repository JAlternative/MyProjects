package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Google перед поиском
 *
 * @author Сергей Хорошков
 */
public class GoogleBeforeSearch {

    private WebDriver driver;
    private WebElement searchField;
    private WebElement searchButton;

    /**
     * Конструктор для инициализации драйвера, поля ввода и кнопки поиска
     * @param driver текущее состояние драйвера для работы с браузером
     */
    public GoogleBeforeSearch(WebDriver driver) {
        this.driver = driver;
        this.searchField = driver.findElement(By.xpath("//input[@*='Найти']"));
        this.searchButton = driver.findElement(By.xpath("//input[@*='Поиск в Google']"));
    }

    /**
     * Главая страница поисковика
     * 1. Кликни по полю поиска
     * 2. Введи слово
     * 3. Нажми поиск
     *
     * @param word Слово, которое нужно ввести в поисковике
     */
    public void find(String word) {
        searchField.click();
        searchField.sendKeys(word);
        searchButton.click();
    }
}
