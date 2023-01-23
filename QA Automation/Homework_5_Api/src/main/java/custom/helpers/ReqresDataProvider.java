package custom.helpers;

import org.testng.annotations.DataProvider;

import static custom.properties.Properties.reqresProperties;

/**
 * Класс с аргументами для тестов
 *
 * @author Сергей Хорошков
 */
public class ReqresDataProvider {
    /**
     * статус код, api
     *
     * @return аргументы задания №2_1
     */
    @DataProvider
    public static Object[][] provideAvatarNames() {
        return new Object[][]{
                {200, reqresProperties.apiUsersPageTwo()}
        };
    }

    /**
     * статус код логин, пароль, api
     *
     * @return аргументы задания №2_2(1)
     */
    @DataProvider
    public static Object[][] provideAuthorizationSuccessful() {
        return new Object[][]{
                {200, "eve.holt@reqres.in", "cityslicka", reqresProperties.apiLogin()}
        };
    }

    /**
     * статус код логин, api
     *
     * @return аргументы задания №2_2(2)
     */
    @DataProvider
    public static Object[][] provideAuthorizationUnsuccessful() {
        return new Object[][]{
                {400, "eve.holt@reqres.in", reqresProperties.apiLogin()}
        };
    }

    /**
     * статус код, api
     *
     * @return аргументы задания №2_3(2)
     */
    @DataProvider
    public static Object[][] provideResultSortOnYears() {
        return new Object[][]{
                {200, reqresProperties.apiListResource()}
        };
    }
}
