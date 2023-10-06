package backend.login;

import datatest.DataTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.w3c.dom.CDATASection;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BackendLoginTest {
    @Test
    void authTokenTest() {
        DataTest data = new DataTest();
        String body = "username=manager&password=manager&grant_type=password&client_id=wfm";

        given()
                .log().uri()
                .log().body()
                .header("Content-Type", "application/x-www-form-urlencoded")  // Установка типа контента, взято из постмана в хедерах
                .body(body)
                .when()
                .post(data.getUrlStand() + "/api/oauth/token")
                .then()
                .log().all()
                .body("success", is(true)) // проверка тела ответа
                .body("token_type", equalTo("bearer")) // проверка тела ответа
                .statusCode(200); // проверка статус кода ответа
    }
}