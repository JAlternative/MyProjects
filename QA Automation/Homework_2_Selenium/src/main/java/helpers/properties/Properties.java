package helpers.properties;


import helpers.properties.interfaces.DriverProperties;
import helpers.properties.interfaces.UrlProperties;
import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для инициализации параметров properties
 */
public class Properties {
	/**
	 * Url значения
	 */
	public static UrlProperties urlProperties = ConfigFactory.create(UrlProperties.class);

	/**
	 * WebDriver значения
	 */
	public static DriverProperties driverProperties = ConfigFactory.create(DriverProperties.class);
}