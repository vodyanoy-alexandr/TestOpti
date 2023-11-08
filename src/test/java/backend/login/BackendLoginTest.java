package backend.login;

import datatest.DataTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class BackendLoginTest {

    DataTest dataTest = new DataTest();
    @DisplayName("Тест на ручку авторизации для стенда без кк")
    @Test
    void authToken() {
        //DataTest dataTest = new DataTest();
        baseURI = "https://release36.dc.oswfm.ru/api/oauth/token";
        String body = "username=manager&password=manager&grant_type=password&client_id=wfm";

        given()
                .log().uri()
                .log().body()
                .header("Content-Type",
                        "application/x-www-form-urlencoded")  // Установка типа контента, взято из постмана в хедерах
                .body(body)
                .when()
                .post()
                .then()
                .log().all()
                .body("success", is(true)) // проверка тела ответа
                .body("token_type", equalTo("bearer")) // проверка тела ответа
                .statusCode(200); // проверка статус кода ответа
    }

    @DisplayName("Авторизация на стенде с кейклоком в режиме full")
    @Test
    void authTokenWithKeycloak() {
        String body = "username=manager&password=manager&grant_type=password&client_id=wfm";

        given()
                .log().uri()
                .log().body()
                .header("Content-Type", "application/x-www-form-urlencoded")  // Установка типа контента, взято из постмана в хедерах
                .body(body)
                .when()
                .post("https://release36.dc.oswfm.ru" + "/api/oauth/token") // todo
                .then()
                .log().all()
                .body("success", is(true)) // проверка тела ответа
                .body("token_type", equalTo("bearer")) // проверка тела ответа
                .statusCode(200); // проверка статус кода ответа
    }
}
