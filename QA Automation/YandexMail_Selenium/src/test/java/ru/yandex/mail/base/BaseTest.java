package ru.yandex.mail.base;


import driver.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Базовый класс для тестов
 *
 * @author Сергей Хорошков
 */
public class BaseTest {
    /**
     * Перед каждым запуском любого теста, создаётся новый экземпляр драйвера
     *
     * @see WebDriverManager#createDriver()
     */
    @BeforeEach
    public void createDriver() {
        WebDriverManager.createDriver();
    }

    /**
     * После того, как любой тест завершится, драйвер прекратит свою работу
     *
     * @see WebDriverManager#quitDriver()
     */
    @AfterEach
    public void quitDriver() {
        WebDriverManager.quitDriver();
    }
}
