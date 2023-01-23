package custom.properties.interfaces;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для работы со ссылками
 *
 * @author Сергей Хорошков
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/properties/url.properties"
})
public interface UrlProperties extends Config {
    /**
     * Url
     *
     * @return Url адрес
     */
    @Key("url.yandex")
    String urlYandex();
}
