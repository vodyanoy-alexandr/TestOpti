package testAPI.testCase.settings;

import testAPI.model.settings.office.OfficeIdBodyModel;
import testAPI.model.settings.office.SettingOfficesBodyModel;
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
import utils.AuthApiKeycloak;

import java.util.Locale;

import static testAPI.specs.Spec.requestSpec;
import static testAPI.specs.Spec.responseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Disabled
public class SettingOfficesApiTest {
    private static String token;
    static BaseDataTest baseDataTest = new BaseDataTest();
    static AuthApiKeycloak authApiKeycloak = new AuthApiKeycloak();
    static Faker faker = new Faker(new Locale("ru"));
    private final static String nameOffice = faker.address().cityName();


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = baseDataTest.getUrlStand();
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        token = authApiKeycloak.getToken(baseDataTest.getLoginManager(), baseDataTest.getPassManager());
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

