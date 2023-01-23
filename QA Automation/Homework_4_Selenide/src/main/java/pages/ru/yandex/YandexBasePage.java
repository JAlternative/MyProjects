package pages.ru.yandex;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

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
     * @param <T>             тип, наследуемый от YandexBasePage
     * @param pageObjectClass pageObject наследуемого класса
     * @return перейти на pageObject наследника
     */
    @Step("Зайти на {url}")
    public static <T extends YandexBasePage> T goPage(String url, Class<T> pageObjectClass) {
        open(url, pageObjectClass);
        return pageObjectClass.cast(page(pageObjectClass));
    }
}
