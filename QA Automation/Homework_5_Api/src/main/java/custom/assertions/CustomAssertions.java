package custom.assertions;

import io.qameta.allure.Step;
import org.testng.Assert;

/**
 * Класс проверок
 *
 * @author Сергей Хорошков
 **/
public class CustomAssertions {

    /**
     * Пользовательский assert
     *
     * @param condition условие
     * @param message   сообщение
     */
    @Step("Проверить, что нет ошибки: '{message}'")
    public static void assertTrue(Boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
}
