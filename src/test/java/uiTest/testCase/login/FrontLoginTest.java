package uiTest.testCase.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import dataTest.BaseDataTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uiTest.pages.autorization.AuthPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

@DisplayName("Тесты на авторизацию на стенде с Keycloak через UI")
class FrontLoginTest {
    AuthPage authenticationPage = new AuthPage();
    static BaseDataTest dataTest = new BaseDataTest();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = dataTest.getUrlStand(); // базовый URL
        Configuration.browserSize = "1920x1080"; // размер окна браузера
        Configuration.headless = true; // Безголовый режим для CI
        Configuration.holdBrowserOpen = false; // Не оставлять окно браузера открытым в CI
    }

    @AfterEach
    void afterEach() {
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        Selenide.closeWindow();
    }

    @DisplayName("Открытие страницы авторизации")
    @Test
    void openPageAuthorization() {
        // открытие страницы авторизации
        authenticationPage.openPage();
        // проверка надписи "Вход в учетную запись" на странице авторизации
        element("#kc-page-title").shouldHave(Condition.text("Войдите в аккаунт"));
    }

    @DisplayName("Авторизация невалидного менеджера")
    @Test
    void invalidLoginManager() {
        // открытие страницы авторизации
        authenticationPage.openPage();
        // вызов метода авторизации
        authenticationPage.loginInPageAuth(dataTest.getLoginManager() + "invalid", dataTest.getPassManager() + "invalid");
        // проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неверный логин или пароль"));
    }

    @DisplayName("Авторизация менеджера")
    @Test
    void loginAndLogoutManager() {
        // открытие страницы авторизации
        authenticationPage.openPage();
        // вызов метода авторизации менеджера
        authenticationPage.loginInPageAuth(dataTest.getLoginManager(), dataTest.getPassManager());
        // проверка на открытой странице расписания раздела "Расписание" после авторизации
        element(".admin-menu").shouldHave(Condition.text("Расписание"));
        // разлогин менеджера
        authenticationPage.logoutUser();
        // проверка надписи "Вход в учетную запись" на странице авторизации
        element("#kc-page-title").shouldHave(Condition.text("Войдите в аккаунт"));
    }

    @DisplayName("Авторизация невалидного оператора")
    @Test
    void invalidLoginOperator() {
        // открытие страницы авторизации
        authenticationPage.openPage();
        // вызов метода авторизации оператора
        authenticationPage.loginInPageAuth(dataTest.getLoginOperator() + "invalid", dataTest.getPassOperator() + "invalid");
        // проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неверный логин или пароль"));
    }

    @DisplayName("Авторизация оператора")
    @Test
    void loginAndLogoutOperator() {
        authenticationPage.openPage();
        // вызов метода авторизации оператора
        authenticationPage.loginInPageAuth(dataTest.getLoginOperator(), dataTest.getPassOperator());
        // проверка на открытой странице расписания раздела "Рабочие смены" после авторизации оператора
        element(".main-menu").shouldHave(Condition.text("Рабочие смены"));
        // проверка разлогина оператора
        authenticationPage.logoutUser();
        // проверка надписи "Вход в учетную запись" на странице авторизации
        element("#kc-page-title").shouldHave(Condition.text("Войдите в аккаунт"));
    }
}
