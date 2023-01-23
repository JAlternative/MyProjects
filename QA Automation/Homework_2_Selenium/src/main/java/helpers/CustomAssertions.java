package helpers;

import io.qameta.allure.Step;

/**
 * Класс проверок
 *
 * @author Сергей Хорошков
 **/
public class CustomAssertions {

    /**
     * Пользовательский assert
     * @param condition условие
     * @param message   сообщение
     */
    @Step("Проверить, что нет ошибки: '{message}'")
    public static void assertTrue(Boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}
