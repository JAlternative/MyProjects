package ru.yandex.mail.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ru.yandex.YandexMainPage;
import pages.ru.yandex.mail.YandexMailPage;
import ru.yandex.mail.base.BaseTest;

/**
 * @author Сергей Хорошков
 */
@DisplayName("Тест Яндекс Почты")
public class YandexMailTest extends BaseTest {
    /**
     * Задание №1
     * 1. Перейти на главную страницу Яндекс Почта
     * 2. Включить чекбокс у двух случайно выбранных
     * писем на вкладке "Входящие"
     *
     * @param yandexService название сервиса, по которому будет осуществлён переход с главной страницы
     * @param userLogin     логин пользователя для авторизации
     * @param userPassword  пароль пользователя для авторизации
     * @param tabName       название вкладки с письмами
     * @param countCheckBox Количество выделяемых писем
     */
    @ParameterizedTest
    @MethodSource("helpers.DataProvider#provideYandexMailOne")
    @DisplayName("YA-1")
    public void yandexMailTestOne(String yandexService, String userLogin, String userPassword, String tabName, Integer countCheckBox) {
        YandexMainPage yandexMainPage = new YandexMainPage();
        yandexMainPage.goToYandexService(yandexService);

        YandexMailPage yandexMailPage = new YandexMailPage();
        yandexMailPage.userAuthorization(userLogin, userPassword);
        yandexMailPage.clickOnCheckBox(tabName, countCheckBox);
    }

    /**
     * Задание №2
     * 1. Перейти на главную страницу Яндекс Почта
     * 2. Включить чекбокс у случайно выбранного письма
     * на вкладке "Входящие"
     * 3. Нажать кнопку "Удалить"
     *
     * @param yandexService название сервиса, по которому будет осуществлён переход с главной страницы
     * @param userLogin     логин пользователя для авторизации
     * @param userPassword  пароль пользователя для авторизации
     * @param tabName       название вкладки с письмами
     * @param countCheckBox Количество выделяемых писем
     */
    @ParameterizedTest
    @MethodSource("helpers.DataProvider#provideYandexMailTwo")
    @DisplayName("YA-2")
    public void yandexMailTestTwo(String yandexService, String userLogin, String userPassword, String tabName, Integer countCheckBox) {
        YandexMainPage yandexMainPage = new YandexMainPage();
        yandexMainPage.goToYandexService(yandexService);

        YandexMailPage yandexMailPage = new YandexMailPage();
        yandexMailPage.userAuthorization(userLogin, userPassword);
        yandexMailPage.clickOnCheckBox(tabName, countCheckBox);
        yandexMailPage.deleteMessage();
    }

    /**
     * Задание №3
     * 1. Перейти на главную страницу
     * 2. Яндекс Почта
     * 3. Нажать на кнопку "Написать"
     * 4. В поле "Кому" ввести свой email
     * 5. В поле "Тема" ввести
     * 6. Произвольный текст
     * 7. Нажать на кнопку "Отправить"
     *
     * @param yandexService название сервиса, по которому будет осуществлён переход с главной страницы
     * @param userLogin     логин пользователя для авторизации
     * @param userPassword  пароль пользователя для авторизации
     * @param tabName       название вкладки с письмами
     * @param countCheckBox Количество выделяемых писем
     * @param email         почта, на которую будет отправлено письмо
     * @param theme         тема письма
     * @param message       текст сообщения
     */
    @ParameterizedTest
    @MethodSource("helpers.DataProvider#provideYandexMailThree")
    @DisplayName("YA-3")
    public void yandexMailTestThree(String yandexService, String userLogin, String userPassword, String tabName,
                                    Integer countCheckBox, String email, String theme, String message) {
        YandexMainPage yandexMainPage = new YandexMainPage();
        yandexMainPage.goToYandexService(yandexService);

        YandexMailPage yandexMailPage = new YandexMailPage();
        yandexMailPage.userAuthorization(userLogin, userPassword);
        yandexMailPage.clickOnCheckBox(tabName, countCheckBox);
        yandexMailPage.sendMessage(email, theme, message);
    }
}
