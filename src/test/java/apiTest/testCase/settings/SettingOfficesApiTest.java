package apiTest.testCase.settings;

import apiTest.model.settings.office.OfficeIdBodyModel;
import apiTest.model.settings.office.SettingOfficesBodyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import dataTest.DataTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class SettingOfficesApiTest {
    DataTest dataTest = new DataTest();
    static Faker faker = new Faker(new Locale("ru"));
    private final static String nameOffice = faker.address().cityName();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = dataTest.getUrlStand();
        // todo добавить запрос на авторизацию и результат ложить в token, cookie
    }

    @DisplayName("Тест на чтение всех офисов в системе через api ")
    @Test
    public void readAllOfficesTest() {
        String token = ""; // todo сюда надо добавить Bearer токен
        String cookie = ""; // todo сюда надо добавить cookie

        given()
                .log().all()
                .header("authorization", "Bearer " + token)
                .header("cookie", cookie)
                .header("Content-Type", "application/json")
                .when()
                .post("/api/office/read-all")
                .then()
                .log().all()
                .statusCode(200);
    }

    @DisplayName("Тест добавления нового офиса через api")
    @Test
    // throws JsonProcessingException для обработки исключений в методе objectMapper.readValue
    public void createOfficeTest() throws JsonProcessingException {
        String token = ""; // todo сюда надо добавить Bearer токен
        String cookie = ""; // todo сюда надо добавить cookie
        String json = "{\"name\":\"Тест111\",\"timezone\":\"Europe/Moscow\",\"comment\":\"TestApi\",\"maxSeats\":777,\"workTime\":[{\"dayOfWeek\":1,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":2,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":3,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":4,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":5,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":6,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":true},{\"dayOfWeek\":7,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":true}]}";

        // Инициализация ObjectMapper (Jackson)
        ObjectMapper objectMapper = new ObjectMapper();

        // Десериализация JSON в объект bodyModel
        SettingOfficesBodyModel bodyModel = objectMapper.readValue(json, SettingOfficesBodyModel.class);

        bodyModel.setName(nameOffice);

        Response response =
                given()
                        .log().all()
                        .header("authorization", "Bearer " + token)
                        .header("cookie", cookie)
                        .header("Content-Type", "application/json")
                        .body(bodyModel)
                        .when()
                        .post("/api/office/create")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("response.id", notNullValue())
                        .extract().response();

        String idValue = response.path("response.id");
        System.out.println("Значение ID из респонса: " + idValue);

    }

    @DisplayName("Тест на удаление офиса по id через api")
    @Test
    public void deleteOfficeTest() {
        OfficeIdBodyModel bodyModel = new OfficeIdBodyModel();
        bodyModel.setId("9a66c660-0df5-4b6d-b64e-c0f85b6e95a8");
        String token = ""; // todo  token
        String cookie = ""; // todo сюда надо добавить cookie

        Response response =
                given()
                        .log().all()
                        .header("Authorization", "Bearer " + token)
                        .header("cookie", cookie)
                        .header("Content-Type", "application/json")
                        .body(bodyModel)
                        .when()
                        .post("/api/office/delete")
                        .then()
                        .log().all()
                        .statusCode(200) // Проверка кода состояния HTTP
                        .extract()
                        .response();
        // response.then().requestBody("some_key", equalTo("expected_value"));
    }
}

