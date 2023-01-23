package custom.properties.interfaces;

import com.codeborne.selenide.logevents.LogEvent;
import org.aeonbits.owner.Config;

/**
 * Интерфейс для работы с константами
 *
 * @author Сергей Хорошков
 **/
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/properties/screenshot.properties"
})
public interface ScreenshotProperties extends Config {

    /**
     * Скриншот статус
     *
     * @return игнорируем скриншоты
     */
    @Key("screenshot.progress")
    LogEvent.EventStatus screenshotProgressStatus();


    /**
     * Скриншот статус
     *
     * @return скриншот при каждом шаге
     */
    @Key("screenshot.pass")
    LogEvent.EventStatus screenshotPassStatus();


    /**
     * Скриншот статус
     *
     * @return скриншот при ошибках
     */
    @Key("screenshot.fail")
    LogEvent.EventStatus screenshotFailStatus();
}
