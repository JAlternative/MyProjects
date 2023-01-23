package in.reqres;

import custom.assertions.CustomAssertions;
import custom.helpers.ReqresDataProvider;
import dto.*;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static custom.specifications.Specification.deleteSpec;
import static custom.specifications.Specification.installSpec;
import static io.restassured.RestAssured.given;

/**
 * Задание 3_1. Rest Assured
 *
 * @author Сергей Хорошков
 */
public class ApiReqresTests {
    /**
     * API
     * Задание 2.1
     * 1. Используя сервис https://reqres.in/ получить список пользователей со второй страницы
     * 2. Убедиться, что имена файлов аватаров пользователей уникальны
     * @param statusCode код ответа http
     * @param api api страницы
     */
    @Test(testName = "Убедиться, что имена файлов аватаров пользователей уникальны", dataProviderClass = ReqresDataProvider.class,
            dataProvider = "provideAvatarNames")
    public void uniqueAvatarNames(Integer statusCode, String api) {
        installSpec(statusCode);
        UsersListResponse usersListResponse = given()
                .when()
                .get(api)
                .then()
                .log().all()
                .extract().body()
                .as(UsersListResponse.class);
        List<String> filesName = usersListResponse.getData().stream().map(e -> e.getAvatar())
                .map(s -> s.substring(s.lastIndexOf("/") + 1)).collect(Collectors.toList());
        CustomAssertions.assertTrue(filesName.stream().distinct().collect(Collectors.toList()).size() == filesName.size(),
                "Имена файлов аватаров пользователей не уникальны");
        deleteSpec();
    }

    /**
     * API
     * Задание 2.2(1)
     * 1. Используя сервис https://reqres.in/ протестировать авторизацию пользователя в системе.
     * 2. Необходимо создание двух тестов на успешный логи и логин с ошибкой из-за не введённого пароля
     * @param statusCode код ответа http
     * @param api api страницы
     * @param userLogin логин пользователя
     * @param userPassword пароль пользователя
     */
    @Test(testName = "Тест на успешную авторизацию c логином и паролем", dataProviderClass = ReqresDataProvider.class,
            dataProvider = "provideAuthorizationSuccessful")
    public void authorizationSuccessful(Integer statusCode, String userLogin,
                                        String userPassword, String api) {
        installSpec(statusCode);
        UserAuthResponse userAuthResponse = given()
                .body(new UserAuthRequest(userLogin, userPassword))
                .when()
                .post(api)
                .then()
                .log().all()
                .extract()
                .as(UserAuthResponse.class);
        CustomAssertions.assertTrue(Objects.nonNull(userAuthResponse.getToken()),
                "Пользователь не прошёл авторизацию");
        deleteSpec();
    }

    /**
     * Задания 2.2(2)
     * @param statusCode код ответа http
     * @param api api страницы
     * @param userLogin логин пользователя
     */
    @Test(testName = "Тест на неудачную авторизацию только с логином", dataProviderClass = ReqresDataProvider.class,
            dataProvider = "provideAuthorizationUnsuccessful")
    public void authorizationUnsuccessful(Integer statusCode, String userLogin, String api) {
        installSpec(statusCode);
        ErrorUserAuthResponse errorUserAuthResponse = given()
                .body(new UserAuthRequest(userLogin))
                .when()
                .post(api)
                .then()
                .log().all()
                .extract()
                .as(ErrorUserAuthResponse.class);
        CustomAssertions.assertTrue(errorUserAuthResponse.getError().equals("Missing password"),
                "При попытке авторизации только с логином не пришло в ответ сообщение об ошибке");
        deleteSpec();
    }

    /**
     * API
     * Задание 2.3
     * Используя сервис https://reqres.in/ убедиться что операция LIST RESOURCE возвращает данные отсортированные по годам
     *
     * @param statusCode код ответа http
     * @param api        api страницы
     */
    @Test(testName = "Убедиться что операция LIST RESOURCE возвращает данные отсортированные по годам",
            dataProviderClass = ReqresDataProvider.class, dataProvider = "provideResultSortOnYears")
    public void resultSortOnYears(Integer statusCode, String api) {
        installSpec(statusCode);
        ResourceListResponse resourceListResponse = given()
                .when()
                .get(api)
                .then()
                .log().all()
                .extract().body()
                .as(ResourceListResponse.class);

        resourceListResponse.getData().stream()
                .map(v -> v.getYear())
                .reduce((year1, year2) -> {
                    CustomAssertions.assertTrue(year1 < year2,
                            "Год: " + year1 + " по порядку стоит раньше, чем " + year2 + " год");
                    return year2;
                });
        deleteSpec();
    }
}
