package custom.properties.interfaces;

import org.aeonbits.owner.Config;

/**
 * Зависимости для тестирования api
 * https://reqres.in/
 *
 * @author Сергей Хорошков
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/properties/reqres.properties"
})
public interface ReqresProperties extends Config {
    /**
     * Url
     *
     * @return основной url адрес
     */
    @Key("url.reqres")
    String reqresUrl();

    /**
     * Url
     *
     * @return cписок пользователей со второй страницы
     */
    @Key("api.users.page.two")
    String apiUsersPageTwo();

    /**
     * User
     *
     * @return страница авторизации
     */
    @Key("api.login")
    String apiLogin();

    /**
     * User
     *
     * @return список ресурсов
     */
    @Key("api.list.resource")
    String apiListResource();
}
