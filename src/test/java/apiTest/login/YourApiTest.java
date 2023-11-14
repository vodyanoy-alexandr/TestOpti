package apiTest.login;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YourApiTest {

    @Test
    @DisplayName("Тест POST-запроса к API")
    public void testPostRequest() {
        // Установите базовый URL
        RestAssured.baseURI = "https://kc.dc.oswfm.ru/realms/os_master/protocol/openid-connect/token";

        // Определите параметры запроса
        String code = "04d69624-bb4a-4281-8616-365de5af256b.1d57f03d-b466-4510-9fd8-4be54bc4f0df.30213853-2a78-41c2-a20c-3e7728ccacbd";
        String grantType = "authorization_code";
        String clientId = "wfm-frontend";
        String redirectUri = "https://master.dc.oswfm.ru";
        String codeVerifier = "j2jOFAvCuUudfKUbaAOHPYMmxyDeugSG8Mrj7UagFQq8fzJsGFuw54jzdRbAy6oJVFbL74w8kpjqlyh3YnjJEf1OUSukK4Ed";

        given()
                .log().all()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("code", code)
                .formParam("grant_type", grantType)
                .formParam("client_id", clientId)
                .formParam("redirect_uri", redirectUri)
                .formParam("code_verifier", codeVerifier)
                .when()
                .post()
                .then()
                        .log().all();

        // Проверьте статус-код ответа
        //assertEquals(200, response.getStatusCode());
        
    }
}
