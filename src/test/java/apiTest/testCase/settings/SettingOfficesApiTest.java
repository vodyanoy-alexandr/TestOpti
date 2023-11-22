package apiTest.testCase.settings;

import apiTest.model.settings.office.OfficeIdBodyModel;
import apiTest.model.settings.office.SettingOfficesBodyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import dataTest.BaseDataTest;
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
    private static String token;
    static BaseDataTest baseData = new BaseDataTest();
    static Faker faker = new Faker(new Locale("ru"));
    private final static String nameOffice = faker.address().cityName();


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = baseData.getUrlStand();
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        String body = "grant_type=password&username=" + baseData.getLoginManager() + "&password=" + baseData.getPassManager() + "&client_id=wfm-frontend";
        //запрос на авторизацию менеджера
        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(body)
                .when()
                .post("https://kc.dc.oswfm.ru/realms/os_master/protocol/openid-connect/token")
                .then()
                .statusCode(200)
                .extract().response();

        token = response.jsonPath().getString("access_token");
    }

    @DisplayName("Тест на чтение всех офисов в системе через api ")
    @Test
    public void readAllOfficesTest() {
        given()
                .spec(requestSpec)
                .header("authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .post("/office/read-all")
                .then()
                .spec(responseSpec);
    }

    @DisplayName("Тест на чтение офиса по id через api")
    @Test
    public void readOfficeTest() {
        OfficeIdBodyModel officeIdBody = new OfficeIdBodyModel();
        officeIdBody.setId("a30aec13-67ad-48c6-a9e7-48328c449ff1");

        given()
                .log().all()
                .header("authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(officeIdBody)
                .when()
                .post("/office/read")
                .then()
                .spec(responseSpec)
                .body("success", is(true)); // проверка тела ответа
    }

    @DisplayName("Тест добавления нового офиса через api")
    @Test
    // throws JsonProcessingException для обработки исключений в методе objectMapper.readValue
    public void createOfficeTest() throws JsonProcessingException {
        String json = OfficeDataTest.OFFICE_JASON;

        ObjectMapper objectMapper = new ObjectMapper(); // Инициализация ObjectMapper (Jackson)

        SettingOfficesBodyModel officeRequestBody = objectMapper.readValue(json, SettingOfficesBodyModel.class); // Десериализация JSON в объект officeRequestBody

        officeRequestBody.setName(nameOffice);
        officeRequestBody.setComment("Тест добавления нового офиса через api");

        Response response =
                given()
                        .log().body()
                        .header("authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .body(officeRequestBody)
                        .when()
                        .post("/office/create")
                        .then()
                        .spec(responseSpec)
                        .body("response.id", notNullValue())
                        .extract().response();

        String idValue = response.path("response.id"); // выдергиваем значение id из респонса пригодится для других тестов
    }

    @DisplayName("Тест на удаление офиса по id через api")
    @Test
    public void deleteOfficeTest() {
        OfficeIdBodyModel officeIdBody = new OfficeIdBodyModel();
        officeIdBody.setId("a30aec13-67ad-48c6-a9e7-48328c449ff1");

        given()
                .log().all()
                .header("authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
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
                .log().all()
                .header("authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(officeRequestBody)
                .when()
                .post("/api/office/update")
                .then()
                .spec(responseSpec);
    }
}

