package custom.properties;


import custom.properties.interfaces.GatewayProperties;
import custom.properties.interfaces.ReqresProperties;
import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для инициализации параметров properties
 *
 * @author Сергей Хорошков
 */
public class Properties {
    /**
     * Зависимости для тестирования api
     * https://reqres.in/
     */
    public static ReqresProperties reqresProperties = ConfigFactory.create(ReqresProperties.class);

    /**
     * Зависимости для тестирования api
     * https://gateway.autodns.com/
     */
    public static GatewayProperties gatewayProperties = ConfigFactory.create(GatewayProperties.class);
}