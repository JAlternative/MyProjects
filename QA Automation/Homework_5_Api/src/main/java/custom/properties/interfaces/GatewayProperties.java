package custom.properties.interfaces;

import org.aeonbits.owner.Config;

/**
 * Зависимости для тестирования api
 * https://gateway.autodns.com/
 *
 * @author Сергей Хорошков
 **/
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/properties/gateway.properties"
})
public interface GatewayProperties extends Config {
    /**
     * Url
     *
     * @return основной url адрес
     */
    @Key("url.gateway")
    String gatewayUrl();
}
