package apiTest.testCase.settings;

import dataTest.DataTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class SettingOfficesApiTest {
    DataTest dataTest = new DataTest();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = dataTest.getUrlStand();
    }

    @DisplayName("Тест на чтение всех офисов в системе через api ")
    @Test
    public void readAllOfficesTest() {
        String token = ""; // todo сюда надо добавить Bearer токен

        given()
                .log().all()
                .header("authorization", "Bearer " + token)
                .contentType("application/json")
                .when()
                .post("/api/office/read-all")
                .then()
                .log().all()
                .statusCode(200);
    }

    @DisplayName("Тест добавления нового офиса через api")
    @Test
    public void createOfficeTest() {
        String token = ""; // todo сюда надо добавить Bearer токен
        String requestBody = "{\"workTime\":[{\"dayOfWeek\":1,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":2,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":3,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":4,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":5,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":6,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":true},{\"dayOfWeek\":7,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":true}],\"name\":\"new office\",\"timezone\":\"Europe/Moscow\",\"comment\":\"comments\",\"maxSeats\":777}";

        given()
                .log().all()
                .header("authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/office/create")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @DisplayName("Тест на удаление офиса по id через api")
    @Test
    public void deleteOfficeTest() {
        String
                requestBody = "{\"id\":\"fadc5c0d-d242-4838-bf84-7c06df467b59\"}", // id удаляемого офиса todo вынести в модель
                token = ""; // todo  token

        Response response =
                given()
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .delete("/api/office/delete")
                        .then()
                        .log().all()
                        .statusCode(200) // Проверка кода состояния HTTP
                        .extract()
                        .response();
        System.out.println(response);

        // Дополнительные проверки
        // Например, проверка содержимого ответа или других параметров
        // response.then().requestBody("some_key", equalTo("expected_value"));
    }
}

