package testAPI.testCase.login;

import dataTest.BaseDataTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Тесты на авторизацию на стенде с Keycloak через Api")
public class LoginKeycloakApiTest {

    BaseDataTest baseDataTest = new BaseDataTest();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = baseDataTest.getUrlKC();
    }

    @DisplayName("Тест на авторизацию менеджером на стенде с кейклоком")
    @Test
    void authManagerApi() {
        String body = "grant_type=password&username=" + baseDataTest.getLoginManager() + "&password=" + baseDataTest.getPassManager() + "&client_id=wfm-frontend";
        given()
                .log().all()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(body)
                .when()
                .post("/realms/os_master/protocol/openid-connect/token")
                .then()
                .log().all()
                .statusCode(200)
                .body("access_token", notNullValue()); // Проверка наличия access_token в ответе
    }

    @DisplayName("Тест на авторизацию оператором на стенде с кейклоком")
    @Test
    void authOperatorApi() {
        given()
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
                .body("access_token", notNullValue()); // Проверка наличия access_token в ответе
    }
}
