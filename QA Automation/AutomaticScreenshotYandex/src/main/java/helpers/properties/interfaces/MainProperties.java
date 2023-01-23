package helpers.properties.interfaces;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для работы с константами
 *
 * @author Сергей Хорошков
 **/
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
	"file:src/main/resources/properties/main.properties"
})
public interface MainProperties extends Config {
	/**
	 * Время
	 *
	 * @return Ожидание по умолчанию
	 */
	@Config.Key("timeout.default")
	Integer defaultTimeout();

	/**
	 * Время
	 *
	 * @return Наименьшее ожидание
	 */
	@Config.Key("timeout.small")
	Integer smallTimeout();

	/**
	 * Элементы
	 *
	 * @return Количество элементов на странице (12)
	 */
	@Config.Key("elements.count")
	Integer countElementsOnPage();

	/**
	 * Цикл
	 *
	 * @return Количество повторений цикла while
	 */
	@Config.Key("repetitions.count")
	Integer countOfRepetitions();
}
