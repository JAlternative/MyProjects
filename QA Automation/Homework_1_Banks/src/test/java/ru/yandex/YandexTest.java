package ru.yandex;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.PageFactoryWikipedia;
import pages.PageFactoryYandex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Задание 1_3
 *
 * @author Сергей Хорошков
 */
@DisplayName("Задание: №1_3")
public class YandexTest extends BaseTest {
    /**
     * Конструктор по умолчанию
     * */
    public YandexTest(){

    }

    /**
     * Введем в поисковик слово "таблица", перейдем на сайт википедии, спарсим таблицу
     * и сравним значения.
     *
     * @param word слово, которое вводим в поисковик
     * @param header заголовок, который будем искать в результатах поиска
     * @param tableName название таблицы, с какой будет работать
     * @param firstValueTable человек, который должен быть на первой строке в таблице
     * @param lastValueTable человек, который должен быть на последней строке в таблице
     */
    @DisplayName("Task1_3. Ввод слова в поисковую строку. Переход на адрес с википедией. Парсинг таблицы с сайта")
    @ParameterizedTest
    @MethodSource("utils.DataProvider#provideYandexAndWikipedia")
    public void wikipediaTest(String word, String header, String tableName, String firstValueTable, String lastValueTable) {
        driver.get("https://yandex.ru/");
        PageFactoryYandex yandex = PageFactory.initElements(
                driver.switchTo().frame(0), PageFactoryYandex.class);

        yandex.find(word);

        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(driver.getWindowHandles().size() - 1));
        WebElement headerElement = yandex.getResult(header);
        Assertions.assertTrue(
                Objects.nonNull(headerElement),
                "На странице поиска, элемента с заголовком «" + header + "» не найдено!");
        headerElement.click();
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(driver.getWindowHandles().size() - 1));

        PageFactoryWikipedia wikipedia = PageFactory.initElements(
                driver, PageFactoryWikipedia.class
        );
        List<Map<String, String>> tableValue = wikipedia.getTableValue(tableName);

        StringBuilder firstName = new StringBuilder();
        StringBuilder lastName = new StringBuilder();

        tableValue.stream().findFirst().get().entrySet()
                .forEach(s -> firstName.append(s.getValue() + " "));
        tableValue.get(tableValue.size() - 1).entrySet()
                .forEach(e -> lastName.append(e.getValue() + " "));

        Assertions.assertTrue(firstName.toString().contains(firstValueTable),
                firstValueTable + " не первый в списке таблицы, первым является: " + firstName);
        Assertions.assertTrue(lastName.toString().contains(lastValueTable),
                lastValueTable + " не последний в списке таблицы, последним является: " + lastName);
        //Тест выдаст ошибку, т.к сейчас на сайте википедии последний в списке Иван Иванович, но в условии задачи другое имя
    }
}
