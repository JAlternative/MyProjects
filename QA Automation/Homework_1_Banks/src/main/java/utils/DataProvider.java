package utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

/**
 * Класс с аргументами для тестов
 */
public class DataProvider {

    /**
     * Конструктор по умолчанию
     */
    public DataProvider() {
    }


    /**
     * Метод возвращает
     *
     * @return аргументы задания №1
     */
    public static Stream<Arguments> provideSearchGoogle() {
        return Stream.of(Arguments.of("Гладиолус", "https://ru.wikipedia.org"));
    }

    /**
     * Метод возвращает
     *
     * @return аргументы задания №2
     */
    public static Stream<Arguments> provideOpenBank() {
        List<String> currencyList = List.of("USD", "EUR");
        return Stream.of(Arguments.of("Открытие", "Банк Открытие: кредит наличными — под 8,9% каждому", "Банк продает", "Банк покупает", currencyList));
    }

    /**
     * Метод возвращает
     *
     * @return аргументы задания №3
     */
    public static Stream<Arguments> provideYandexAndWikipedia() {
        return Stream.of(Arguments.of("таблица", "Таблица — Википедия", "Преподаватели кафедры программирования" ,"Сергей Владимирович", "Сергей Адамович"));
    }
}
