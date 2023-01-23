package custom.helpers;

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
     * @return аргументы задания №4_1
     */
    public static Stream<Arguments> provideYandexMarket() {
        return Stream.of(
                Arguments.of("Маркет", "Электроника",
                        "Смартфоны", "Apple", "iPhone"));
    }

}
