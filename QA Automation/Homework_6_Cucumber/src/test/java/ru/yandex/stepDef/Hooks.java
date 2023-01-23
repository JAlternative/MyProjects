package ru.yandex.stepDef;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import cucumber.api.java.Before;
import custom.selenide.CustomAllureSelenide;

import static custom.properties.Properties.driverProperties;
import static custom.properties.Properties.screenshotProperties;

/**
 * Базовые настройки
 *
 * @author Сергей Хорошков
 */
public class Hooks {
    /**
     * Конструктор по умолчанию
     */
    public Hooks() {

    }

    /**
     * Настройки для скриншотов
     */
    @Before()
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide",
                new CustomAllureSelenide().screenshots(true)
                        .savePageSource(true)
                        .screenOfEventStatus(screenshotProperties.screenshotFailStatus()));
    }

    /**
     * Опции для браузера и время ожидания
     */
    @Before("@Run")
    public void option() {
        Configuration.timeout = driverProperties.defaultTimeout();
        Configuration.browser = driverProperties.browser();
        Configuration.browserSize = driverProperties.browserSize();
    }

}
