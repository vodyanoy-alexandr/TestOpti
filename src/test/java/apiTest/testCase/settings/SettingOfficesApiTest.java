package apiTest.testCase.settings;

import apiTest.model.settings.office.SettingOfficesBodyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataTest.DataTest;
import io.restassured.RestAssured;
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
        String json = "{\"name\":\"Тест111\",\"timezone\":\"Europe/Moscow\",\"comment\":\"comments\",\"maxSeats\":777,\"workTime\":[{\"dayOfWeek\":1,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":2,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":3,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":4,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":5,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":false},{\"dayOfWeek\":6,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":true},{\"dayOfWeek\":7,\"from\":\"08:00\",\"to\":\"20:00\",\"weekend\":true}]}";

        // Инициализация ObjectMapper (Jackson)
        ObjectMapper objectMapper = new ObjectMapper();

        // Десериализация JSON в объект bodyModel
        SettingOfficesBodyModel bodyModel = objectMapper.readValue(json, SettingOfficesBodyModel.class);

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
                .body("response.id", notNullValue());

    }

    @DisplayName("Тест на удаление офиса по id через api")
    @Test
    public void deleteOfficeTest() {

        String requestBody = "{\"id\":\"9a66c660-0df5-4b6d-b64e-c0f85b6e95a8\"}"; // id удаляемого офиса todo вынести в модель
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSQkVCUlRkb0NWcW5KZlZTTkh1ckQ4bGs1MVIzUGw0aXJwbE1BU0JXM0l3In0.eyJleHAiOjE3MDAxMjQ5NDMsImlhdCI6MTcwMDEyNDY0MywiYXV0aF90aW1lIjoxNzAwMTI0NjQwLCJqdGkiOiI2ZGQ0MGE2Mi00MjRlLTQ5NTEtYTVlMi01MzI3OTg2MTJjN2MiLCJpc3MiOiJodHRwczovL2tjLmRjLm9zd2ZtLnJ1L3JlYWxtcy9vc19tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiYTU4NmRkNTgtZDMwMy00OTRmLWIwNGMtNDM3ODQ1M2I2MmUwIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoid2ZtLWZyb250ZW5kIiwibm9uY2UiOiJlNjM0M2I0Zi02ZjJmLTQzZTktODkzMi1jYmU5ZTkyNzIwOTkiLCJzZXNzaW9uX3N0YXRlIjoiMTY3NTFjMzEtNWY3NC00ZDVkLTg2YTQtOWUwM2E2MjdjM2Y0IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW9zbiIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiMTY3NTFjMzEtNWY3NC00ZDVkLTg2YTQtOWUwM2E2MjdjM2Y0IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhZG1pbiIsImxvY2FsZSI6InJ1In0.K2lPBVHH0Mv8VaPeboM6_5ufAngv1Wjv9-WxL1IEXR5s_NQK0lE23_3Q5zNiACVWvFde1QRp9rRuDic_yePIAWrDQJ5oDfwyoqCp6VQ6ymCQqWzHYFv47wFtidmK53IwPYgfixPqB-x-jnDwIaY1uk2VTWscVFMxjVIUqCmHhtgBl3of2jsE5mdgmeLCA-e1DUiP4Jsi8CbCPTHu9yA6b53KGqxPzczFL4ImXrD2uI6Pihd7iweR0Dx4hlqfG_5blFDZdq3_5r5J2z3UV4SXRcwwL45weWk22aLu28V5QBJB1xhGIDkLhQ9DSQzXR71JbshwP-_wjrlJBlwQvHAfiA"; // todo  token
        String cookie = "INGRESSCOOKIE=1697524826.506.12342.103057|27d02e9bd0328ccdb383216fe38836d0; INGRESSCOOKIE=1697524838.057.12342.570613|8d78fad643bac180d9fd7b95667074dc; rl_anonymous_id=RudderEncrypt%3AU2FsdGVkX19Y1CCDT0cgSy6jtkTxWWjNToRL0fwVep%2FWiq4GiiKq64ZwDgIASVLK2%2FcOSWccWzRUIg4Li1Syig%3D%3D; rl_group_id=RudderEncrypt%3AU2FsdGVkX1%2FG0C4Olqf%2B0xx0q%2BrUMVT7FxdfeLzMHsY%3D; rl_group_trait=RudderEncrypt%3AU2FsdGVkX19Ln3AqLtkhFOtQZUTDqU7Pop8lrLlqxlg%3D; rl_user_id=RudderEncrypt%3AU2FsdGVkX19FvqvkxJhX0%2FrCCR1hFgtTr4moGDnQRFiikizr9jBWTZFSAC0%2BQIdj; rl_trait=RudderEncrypt%3AU2FsdGVkX19fw%2BaPneP76dDo8douX%2BYKXKDo95oxrss%3D; @wfmfront-token=eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSQkVCUlRkb0NWcW5KZlZTTkh1ckQ4bGs1MVIzUGw0aXJwbE1BU0JXM0l3In0.eyJleHAiOjE3MDAxMjUwNzIsImlhdCI6MTcwMDEyNDc3MiwiYXV0aF90aW1lIjoxNzAwMTI0NjQwLCJqdGkiOiIxM2RkZGJmZC03YjUyLTQ0NzEtYjk2OC0wMDE0MGVlOWM3M2EiLCJpc3MiOiJodHRwczovL2tjLmRjLm9zd2ZtLnJ1L3JlYWxtcy9vc19tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiYTU4NmRkNTgtZDMwMy00OTRmLWIwNGMtNDM3ODQ1M2I2MmUwIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoid2ZtLWZyb250ZW5kIiwibm9uY2UiOiJlNjM0M2I0Zi02ZjJmLTQzZTktODkzMi1jYmU5ZTkyNzIwOTkiLCJzZXNzaW9uX3N0YXRlIjoiMTY3NTFjMzEtNWY3NC00ZDVkLTg2YTQtOWUwM2E2MjdjM2Y0IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW9zbiIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiMTY3NTFjMzEtNWY3NC00ZDVkLTg2YTQtOWUwM2E2MjdjM2Y0IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhZG1pbiIsImxvY2FsZSI6InJ1In0.9bnXMp_7csEt0-lEdMwRSCcn_WE9Jn1T5Ypc8J6pWlHZQQv02wMIDzuqL2UIMg3i3XQ58FYJ91ao4owhRm5x8g61snin-0f86hdvX7scUKd78Y_py0BKFbZLcw98aWDerSbtANqSH7a0TNP-1INYi5TYkJAP1l9XgeeeKSU0d7zxUsuILbbMdLGS6eBV0bTfSE_CnssioosyjZUhBLEiLbeDArlu75jNHQmKEeiiFrLOAbovGQ3xBv-qmfbsFuU_QCbCQ28W13SJYOjskovRk7XOltjx10a3EMI1th-VZ2j0EXSUpiAWT8rDTepe6xfDPhhYByo10DGiaVsWpTjGqg"; // todo сюда надо добавить cookie

        Response response =
                given()
                        .log().all()
                        .header("Authorization", "Bearer " + token)
                        .header("cookie", cookie)
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post("/api/office/delete")
                        .then()
                        .log().all()
                        .statusCode(200) // Проверка кода состояния HTTP
                        .extract()
                        .response();
        // Дополнительные проверки
        // Например, проверка содержимого ответа или других параметров
        // response.then().requestBody("some_key", equalTo("expected_value"));
    }
}

