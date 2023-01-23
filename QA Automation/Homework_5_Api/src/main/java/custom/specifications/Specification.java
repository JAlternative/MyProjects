package custom.specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static custom.properties.Properties.reqresProperties;

/**
 * Класс спецификаций
 *
 * @author Сергей Хорошков
 */
public class Specification {
    /**
     * Задать начальные параметры для request
     */
    private static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(reqresProperties.reqresUrl())
                .setContentType("application/json")
                .build();
    }

    /**
     * Задать начальные параметры для request
     */
    private static ResponseSpecification responseSpec(Integer statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    /**
     * Задать начальные параметры для request и response
     *
     * @param statusCode код состояния
     */
    public static void installSpec(Integer statusCode) {
        RestAssured.requestSpecification = requestSpec();
        RestAssured.responseSpecification = responseSpec(statusCode);
    }

    /**
     * Удалить все заданные параметры
     */
    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
