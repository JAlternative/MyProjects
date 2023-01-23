package custom.properties.interfaces;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для работы с константами
 *
 * @author Сергей Хорошков
 **/
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/properties/driver.properties"
})
public interface DriverProperties extends Config {
    /**
     * Браузер
     *
     * @return название браузера
     */
    @Key("browser")
    String browser();

    /**
     * Браузер
     *
     * @return размер браузера
     */
    @Key("browser.size")
    String browserSize();

    /**
     * Время
     *
     * @return Ожидание по умолчанию
     */
    @Key("timeout.default")
    Integer defaultTimeout();
}
