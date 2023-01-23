package ru.yandex;

/**
 * Опции кукумбера
 *
 * @author Сергей Хорошков
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "pretty", "json:target/cucumber-report/report.json"},
        features = "src/test/java/ru/yandex/features",
        glue = "ru.yandex.stepDef",
        tags = {"@Run"}
)
public class CucumberRunner {

}
