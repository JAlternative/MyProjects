package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * Класс с аргументами для тестов
 *
 * @author Сергей Хорошков
 */
public class DataProvider {
    /**
     * Метод возвращает
     *
     * @return аргументы задания №1_4
     */
    public static Stream<Arguments> provideYandexMailOne() {
        return Stream.of(
                Arguments.of("Почта", "KhoroshkovTestMail@yandex.ru", "TestQwerty", "Входящие", 2));
    }

    public static Stream<Arguments> provideYandexMailTwo() {
        return Stream.of(
                Arguments.of("Почта", "KhoroshkovTestMail@yandex.ru", "TestQwerty", "Входящие", 1));
    }

    public static Stream<Arguments> provideYandexMailThree() {
        return Stream.of(
                Arguments.of("Почта", "KhoroshkovTestMail@yandex.ru", "TestQwerty", "Входящие", 1,
                        "s.khoroshkov@gmail.com", "QA Automation", "All autotests are written in the language Java"));
    }
}
