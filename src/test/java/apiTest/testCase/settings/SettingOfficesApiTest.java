package apiTest.testCase.settings;

import apiTest.model.settings.office.OfficeIdBodyModel;
import apiTest.model.settings.office.SettingOfficesBodyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import dataTest.DataTest;
import dataTest.office.OfficeDataTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static apiTest.specs.Spec.requestSpec;
import static apiTest.specs.Spec.responseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class SettingOfficesApiTest {
    static DataTest dataTest = new DataTest();
    static Faker faker = new Faker(new Locale("ru"));
    private final static String nameOffice = faker.address().cityName();


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = dataTest.getUrlStand();
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @DisplayName("Тест на чтение всех офисов в системе через api ")
    @Test
    public void readAllOfficesTest() {
        given()
                .spec(requestSpec)
                .when()
                .post("/office/read-all")
                .then()
                .spec(responseSpec);
    }

    @DisplayName("Тест добавления нового офиса через api")
    @Test
    // throws JsonProcessingException для обработки исключений в методе objectMapper.readValue
    public void createOfficeTest() throws JsonProcessingException {
        String json = OfficeDataTest.OFFICE_JASON;

        // Инициализация ObjectMapper (Jackson)
        ObjectMapper objectMapper = new ObjectMapper();

        // Десериализация JSON в объект officeRequestBody
        SettingOfficesBodyModel officeRequestBody = objectMapper.readValue(json, SettingOfficesBodyModel.class);

        officeRequestBody.setName(nameOffice);

        Response response =
                given()
                        .spec(requestSpec)
                        .body(officeRequestBody)
                        .when()
                        .post("/office/create")
                        .then()
                        .spec(responseSpec)
                        .body("response.id", notNullValue())
                        .extract().response();

        String idValue = response.path("response.id"); // выдергиваем значение id из респонса
        System.out.println("Значение ID из респонса: " + idValue);
    }

    @DisplayName("Тест на удаление офиса по id через api")
    @Test
    public void deleteOfficeTest() {
        OfficeIdBodyModel officeIdBody = new OfficeIdBodyModel();
        officeIdBody.setId("9a66c660-0df5-4b6d-b64e-c0f85b6e95a8");

        given()
                .spec(requestSpec)
                .body(officeIdBody)
                .when()
                .post("/office/delete")
                .then()
                .spec(responseSpec)
                .body("success", is(true)); // проверка тела ответа

    }

    @Disabled("не готов")
    @DisplayName("Tест на редактирование офиса через api")
    @Test
    public void updateOfficeTest() throws JsonProcessingException {
        String json = OfficeDataTest.OFFICE_JASON;

        // Инициализация ObjectMapper (Jackson)
        ObjectMapper objectMapper = new ObjectMapper();

        // Десериализация JSON в объект officeRequestBody
        SettingOfficesBodyModel officeRequestBody = objectMapper.readValue(json, SettingOfficesBodyModel.class);

        officeRequestBody.setName(nameOffice);

        given()
                .spec(requestSpec)
                .body(officeRequestBody)
                .when()
                .post("/api/office/update")
                .then()
                .spec(responseSpec);
    }
}

