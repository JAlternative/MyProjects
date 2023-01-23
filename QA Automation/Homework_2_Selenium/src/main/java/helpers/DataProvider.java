package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Класс с аргументами для тестов
 * @author Сергей Хорошков
 */
public class DataProvider {
	/**
	 * Метод возвращает
	 *
	 * @return аргументы задания №1_4
	 */
	public static Stream<Arguments> provideYandexMarket() {
		List<String> manufacturers = new ArrayList<>(Arrays.asList("Microsoft", "DELL"));
		return Stream.of(
			Arguments.of("Маркет", "Электроника",
				"Ноутбуки", "10000", "90000", manufacturers, 12, 1));
	}
}
