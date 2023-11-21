package uiTest.testCase.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import dataTest.BaseDataTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uiTest.pages.autorization.AuthPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

class FrontLoginTest {
    AuthPage authPage = new AuthPage();
    static BaseDataTest dataTest = new BaseDataTest();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = dataTest.getUrlStand(); // базовый url
        Configuration.browserSize = "1920x1080"; // размер окна браузера
        Configuration.holdBrowserOpen = false; // оставлять окно браузера открытым
        System.out.println("Start tests");
    }

    @AfterEach
    void afterEach() {
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @DisplayName("Открытие страницы авторизации")
    @Test
    void openPageAuthorization() {
        // открытие страницы авторизации
        open("/");
        //проверка надписи "Вход в учетную запись"на странице авторизации
        element("#kc-page-title").shouldHave(Condition.text("Вход в учетную запись"));
    }

    @DisplayName("Авторизация невалидного менеджера")
    @Test
    void InvalidLoginManager() {
        // открытие страницы авторизации
        open("/");
        // вызов метода авторизации
        authPage.loginInPageAuth(dataTest.getLoginManager() + "invalid", dataTest.getPassManager() + "invalid");
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @DisplayName("Авторизация менеджера")
    @Test
    void loginAndLogoutManager() {
        // открытие страницы авторизации
        open("/");
        // вызов метода авторизации менеджера
        authPage.loginInPageAuth(dataTest.getLoginManager(), dataTest.getPassManager());
        //проверка на открытой странице расписания раздела "Расписание" после авторизации
        element(".admin-menu").shouldHave(Condition.text("Расписание"));
        // проверка разлогина менеджера
        element("[title=Выйти]").click();
    }

    @DisplayName("Авторизация невалидного оператора")
    @Test
    void InvalidLoginOperator() {
        // открытие страницы авторизации
        open("/");
        // вызов метода авторизации оператора
        authPage.loginInPageAuth(dataTest.getLoginOperator() + "invalid", dataTest.getPassOperator() + "invalid");
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @DisplayName("Авторизация оператора")
    @Test
    void loginAndLogoutOperator() {
        open("/");
        // вызов метода авторизации оператора
        authPage.loginInPageAuth(dataTest.getLoginOperator(), dataTest.getPassOperator());
        //проверка на открытой странице расписания раздела "Рабочие смены" после авторизации оператора
        element(".main-menu").shouldHave(Condition.text("Рабочие смены"));
        // проверка разлогина operator
        element("[title=Выйти]").click();
    }
}
