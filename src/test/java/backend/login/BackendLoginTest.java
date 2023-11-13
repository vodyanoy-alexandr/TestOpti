package backend.login;

import datatest.DataTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class BackendLoginTest {

    DataTest dataTest = new DataTest();

    @DisplayName("Тест на ручку авторизации для стенда без кк")
    @Test
    void authToken() {
        baseURI = dataTest.getUrlStand();
        String body = "username=manager&password=manager&grant_type=password&client_id=wfm";

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
                .body("success", is(true)) // проверка тела ответа
                .body("token_type", equalTo("bearer")) // проверка тела ответа
                .statusCode(200); // проверка статус кода ответа
    }

    @Disabled("не готов")
    @DisplayName("Авторизация на стенде с кейклоком в режиме full")
    @Test
    void authTokenWithKeycloak() {
        baseURI = dataTest.getUrlStand();
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
