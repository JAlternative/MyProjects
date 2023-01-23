package custom.helpers;

import org.testng.annotations.DataProvider;

import static custom.properties.Properties.gatewayProperties;

/**
 * Класс с аргументами для тестов
 *
 * @author Сергей Хорошков
 */
public class GatewayDataProvider {
    /**
     * url, количество тегов отображаемых на странице
     *
     * @return аргументы задания №2_4
     */
    @DataProvider
    public static Object[][] provideCountTagsOnPage() {
        return new Object[][]{
                {gatewayProperties.gatewayUrl(), 14}
        };
    }
}
