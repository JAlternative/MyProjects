package pages.ru.yandex;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

/**
 * Яндекс, базовый класс
 *
 * @author Сергей Хорошков
 */
public abstract class YandexBasePage {

    /**
     * Шаг №1
     *
     * @param url             ссылка, по который будет осуществлён переход
     */
    @Step("Зайти на {url}")
    public static void goPage(String url) {
        open(url);
    }
}
