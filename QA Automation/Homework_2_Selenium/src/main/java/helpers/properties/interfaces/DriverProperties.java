package helpers.properties.interfaces;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для работы с WebDriver
 *
 * @author Сергей Хорошков
 **/
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/properties/driver.properties"
})
public interface DriverProperties extends Config {
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
}
