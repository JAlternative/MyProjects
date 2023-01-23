package helpers.properties;


import helpers.properties.interfaces.MainProperties;
import helpers.properties.interfaces.UrlProperties;
import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для инициализации параметров properties
 */
public class Properties {

	/**
	 * Конструктор по умолчанию
	 */
	public Properties() {

	}

	/**
	 * Url значения
	 */
	public static UrlProperties urlProperties = ConfigFactory.create(UrlProperties.class);

	/**
	 * Константные значения
	 */
	public static MainProperties mainProperties = ConfigFactory.create(MainProperties.class);
}