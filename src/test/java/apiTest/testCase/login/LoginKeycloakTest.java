package apiTest.testCase.login;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginKeycloakTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://kc.dc.oswfm.ru";
    }

    @DisplayName("Запрос на странице авторизации кейклок")
    @Test
    public void authenticateTest() {
        given()
                .log().all()
                .header("content-type", "application/x-www-form-urlencoded")
                .header("cookie", "KEYCLOAK_LOCALE=ru; AUTH_SESSION_ID=29b8af1e-ebcd-4e2d-ae65-61ebf77a803a.keycloak-7d7d6fb64b-ghngn-13613; AUTH_SESSION_ID_LEGACY=29b8af1e-ebcd-4e2d-ae65-61ebf77a803a.keycloak-7d7d6fb64b-ghngn-13613;KEYCLOAK_LOCALE=ru; AUTH_SESSION_ID=29b8af1e-ebcd-4e2d-ae65-61ebf77a803a.keycloak-7d7d6fb64b-ghngn-13613; AUTH_SESSION_ID_LEGACY=29b8af1e-ebcd-4e2d-ae65-61ebf77a803a.keycloak-7d7d6fb64b-ghngn-13613; KC_RESTART=eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI0NzgwYjc3OS1kYjE1LTRkYTAtOWU2MS1iNzIyNzJjZmMyMDYifQ.eyJjaWQiOiJ3Zm0tZnJvbnRlbmQiLCJwdHkiOiJvcGVuaWQtY29ubmVjdCIsInJ1cmkiOiJodHRwczovL21hc3Rlci5kYy5vc3dmbS5ydS9zZXR0aW5ncy9vZmZpY2VzIiwiYWN0IjoiQVVUSEVOVElDQVRFIiwibm90ZXMiOnsic2NvcGUiOiJvcGVuaWQiLCJpc3MiOiJodHRwczovL2tjLmRjLm9zd2ZtLnJ1L3JlYWxtcy9vc19tYXN0ZXIiLCJyZXNwb25zZV90eXBlIjoiY29kZSIsImNvZGVfY2hhbGxlbmdlX21ldGhvZCI6IlMyNTYiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL21hc3Rlci5kYy5vc3dmbS5ydS9zZXR0aW5ncy9vZmZpY2VzIiwic3RhdGUiOiJlY2MzYTJkYS0zM2I0LTQ3NzEtOGVmOS1iMjYzMmI5Y2QxNjAiLCJub25jZSI6IjViMjYyY2NlLWM2M2EtNDY1Ny04ZjI0LThjNzAxZTY4YzczOSIsImNvZGVfY2hhbGxlbmdlIjoiVkxCRkpPMVNhZmVEXzZ1aFg0MlZsNXQ2MFBjSDB4ZUI0UVZfbF9Jd0d6OCIsInJlc3BvbnNlX21vZGUiOiJmcmFnbWVudCJ9fQ.dRnOqHVjlGVv76E5guIGyegWt2jf4vbsj-va62pvkqg; INGRESSCOOKIE=1696573657.574.8107.659570|fa5e88e34c5da18405c2d037c3279dd0; rl_anonymous_id=RudderEncrypt%3AU2FsdGVkX1%2BtbrznxujVEnARrP0d0xLEh5k0Yolhw2MmKsYrlV1pAPqhNSI2CATcct%2F1ZjVFE89aOX0XuAGJlw%3D%3D; rl_group_id=RudderEncrypt%3AU2FsdGVkX18vrulLXBJSoIYAycJWBTplpHGRC0UJaf8%3D; rl_group_trait=RudderEncrypt%3AU2FsdGVkX1%2FTerVbK%2FCqOYaqhiCJBk9i8AaCQ7fGELs%3D; rl_user_id=RudderEncrypt%3AU2FsdGVkX19qcSMkxtT%2FMVPSNIU9s%2FS2P0zyTeRIrjusB2XKGo74rzToNQm17YPu; rl_trait=RudderEncrypt%3AU2FsdGVkX1%2B9oAyZznjlVsoJcAoaJxd7tV8bH0B9lpo%3D")
                .formParam("username", "manager")
                .formParam("password", "manager")
                .formParam("credentialId", "")
                .when()
                .post("/realms/os_master/login-actions/authenticate?session_code=TahxQivh3u-LufFo7Bx9JqGErsKV7CKUprpP6dk0qlM&execution=d523701f-0379-4dd1-9645-766a80693151&client_id=wfm-frontend&tab_id=afLLd_8DVEM")
                .then()
                .log().all()
                .statusCode(302); // Проверка кода ответа
    }
}