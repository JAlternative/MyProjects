package com.autodns.gateway;

import custom.assertions.CustomAssertions;
import custom.helpers.GatewayDataProvider;
import io.restassured.path.xml.element.Node;
import org.testng.annotations.Test;
import pages.com.autodns.gateway.Gateway;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Задание 3_1. Rest Assured
 *
 * @author Сергей Хорошков
 */
public class ApiGatewayTest {
    /**
     * API
     * Задание 2.4
     * Используя сервис https://gateway.autodns.com/ убедиться, что количество тегов равно 14.
     * @param url ссылка на страницу
     * @param tagsCount количество тегов, которое должно находиться на странице
     */
    @Test(testName = "Убедиться, что количество тегов равно {gatewayProperties.countTags()}",
            dataProviderClass = GatewayDataProvider.class, dataProvider = "provideCountTagsOnPage")
    public void countTagsOnPage(String url, Integer tagsCount) {
        Node node = given()
                .when()
                .get(url)
                .then()
                .log().all()
                .extract()
                .body().htmlPath().get();
        List<String> tagsNames = new Gateway().getAllTagsNameOnPage(node);
        CustomAssertions.assertTrue(tagsNames.size() == tagsCount,
                "Количество тегов на странице не равно " + tagsCount);
    }
}
