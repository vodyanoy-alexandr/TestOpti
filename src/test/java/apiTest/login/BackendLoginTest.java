package apiTest.login;

import dataTest.DataTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class BackendLoginTest {

    DataTest dataTest = new DataTest();

    @DisplayName("Тест на ручку авторизации для стенда без кк")
    @Test
    void authToken() {
        baseURI = "https://release36.dc.oswfm.ru";
        String body = "username=manager&password=manager&grant_type=password&client_id=wfm";
//        String body = "username:manager\n" +
//                "password:manager\n" +
//                "locale:ru\n" +
//                "grant_type:password\n" +
//                "client_id:wfm\n" +
//                "device_id:46d0e3be-44cf-2e4b-edd2-fb64840600f4";

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
    void authTokenWithKeycloak() {
        baseURI = dataTest.getUrlStand();
        String body = "";

        given()
                .log().uri()
                .log().body()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(body)
                .when()
                .post("")
                .then()
                .log().all()
                .body("success", is(true)) // проверка тела ответа
                .statusCode(200); // проверка статус кода ответа
    }
}
