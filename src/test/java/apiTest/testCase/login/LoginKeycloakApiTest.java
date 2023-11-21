package apiTest.testCase.login;

import dataTest.BaseDataTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class LoginKeycloakApiTest {

    BaseDataTest baseDataTest = new BaseDataTest();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://kc.dc.oswfm.ru";
    }

    @DisplayName("Тест на авторизацию менеджером на стенде с кейклоком")
    @Test
    public void authManagerTest() {
        String body = "grant_type=password&username=" + baseDataTest.getLoginManager() + "&password=" + baseDataTest.getPassManager() + "&client_id=wfm-frontend";
        Response response = given()
                .log().all()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(body)
                .when()
                .post("/realms/os_master/protocol/openid-connect/token")
                .then()
                .log().all()
                .statusCode(200)
                .body("access_token", notNullValue()) // Проверка наличия access_token в ответе
                .extract().response();

        // Получение токена из респонса и сохранение в переменную
        String token = response.jsonPath().getString("access_token");

        // Получение кук из респонса и сохранение в переменную
        String cookies = response.getDetailedCookies().toString();
    }

    @DisplayName("Тест на авторизацию оператором на стенде с кейклоком")
    @Test
    public void authOperatorTest() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "password")
                .formParam("username", baseDataTest.getLoginOperator())
                .formParam("password", baseDataTest.getPassOperator())
                .formParam("client_id", "wfm-frontend")
                .when()
                .post("/realms/os_master/protocol/openid-connect/token")
                .then()
                .log().all()
                .statusCode(200)
                .body("access_token", notNullValue()) // Проверка наличия access_token в ответе
                .extract().response();

        // Получение токена из респонса и сохранение в переменную
        String accessToken = response.jsonPath().getString("access_token");

        // Получение кук из респонса и сохранение в переменную
        String cookies = response.getDetailedCookies().toString();

    }
}
