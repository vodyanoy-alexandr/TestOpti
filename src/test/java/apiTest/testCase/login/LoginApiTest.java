package apiTest.testCase.login;

import dataTest.DataTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class LoginApiTest {

    DataTest dataTest = new DataTest();

    @DisplayName("Тест на ручку авторизации для стенда без кк")
    @Test
    void authToken() {
        baseURI = "https://release36.dc.oswfm.ru";
        String body = "username=manager&password=manager&locale=ru&grant_type=password&client_id=wfm&device_id=46d0e3be-44cf-2e4b-edd2-fb64840600f4";
        given()
                .log().uri()
                .log().body()
                .header("Content-Type",
                        "application/x-www-form-urlencoded")  // Установка типа контента, взято из постмана в хедерах
                .body(body)
                .when()
                .post("/api/oauth/token")
                .then()
                .log().all()
                .statusCode(200) // проверка статус кода ответа
                .body("success", is(true)) // проверка тела ответа
                .body("access_token", notNullValue())
                .body("token_type", equalTo("bearer")); // проверка тела ответа
    }

    @Disabled("не готов")
    @DisplayName("Авторизация на стенде с кейклоком в режиме full")
    @Test
    public void retrieveTokenTest() {
        baseURI = "https://kc.dc.oswfm.ru";

        // Отправка POST-запроса для получения токена доступа
        Response response =
                given()
                        .header("content-type", "application/x-www-form-urlencoded")
                        .formParam("code", "eaed2c5b-b1ba-4a7b-809c-badbad222c18.a398d61e-f4b6-4255-8201-dc4a8a77dd11.30213853-2a78-41c2-a20c-3e7728ccacbd")
                        .formParam("grant_type", "authorization_code")
                        .formParam("client_id", "wfm-frontend")
                        .formParam("redirect_uri", "https://master.dc.oswfm.ru/settings/offices")
                        .formParam("code_verifier", "KcK1cMOeNNEgRtk0ygxLGDrkBCtXJiuv6O97mLOs2JbXe9klnbzbPYIejQTySGKQTRxAaECHF68TNCyLiJqs69nGfC0XpV4w")
                        .when()
                        .log().all()
                        .post("/realms/os_master/protocol/openid-connect/token")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();

        // Здесь можно добавить проверки для ответа, например, проверку кода состояния и т.д.
        // response.then().statusCode(200);
        // response.then().body("some_key", equalTo("expected_value"));
    }
}
