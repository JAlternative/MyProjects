package custom.properties;


import custom.properties.interfaces.DriverProperties;
import custom.properties.interfaces.ScreenshotProperties;
import custom.properties.interfaces.UrlProperties;
import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для инициализации параметров properties
 *
 * @author Сергей Хорошков
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

    /**
     * Скриншот статус
     */
    public static ScreenshotProperties screenshotProperties = ConfigFactory.create(ScreenshotProperties.class);
}