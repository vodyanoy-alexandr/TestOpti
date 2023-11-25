package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApiKeycloak {
    public String getToken(String login, String pass) {
        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "password")
                .formParam("client_id", "wfm-frontend")
                .formParam("username", login)
                .formParam("password", pass)
                .when()
                .post("https://kc.dc.oswfm.ru/realms/os_master/protocol/openid-connect/token")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.jsonPath().getString("access_token");
    }
}
