package ru.yandex;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import custom.selenide.CustomAllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static custom.properties.Properties.driverProperties;
import static custom.properties.Properties.screenshotProperties;

/**
 * Базовый класс тестов
 *
 * @author Сергей Хорошков
 */
public class BaseTest {
    /**
     * Настройки для скриншотов
     */
    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide",
                new CustomAllureSelenide().screenshots(true)
                        .savePageSource(true)
                        .screenOfEventStatus(screenshotProperties.screenshotPassStatus()));
    }

    /**
     * Опции для браузера и время ожидания
     */
    @BeforeEach
    public void option() {
        Configuration.timeout = driverProperties.defaultTimeout();
        Configuration.browser = driverProperties.browser();
        Configuration.browserSize = driverProperties.browserSize();
    }
}
