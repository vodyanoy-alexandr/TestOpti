package frontend.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.*;
import datatest.AuthPageData;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

class FrontLoginTest {
    static AuthPageData authPageData = new AuthPageData();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = authPageData.getUrlStand(); // базовый url
        Configuration.browserSize = "1920x1080"; // размер окна браузера
        Configuration.holdBrowserOpen = true; // оставлять окно браузера открытым
        System.out.println("Start tests");
    }

    @AfterEach
    void afterEach() {
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @Test
        // open page autorizacion
    void openPageAutorization() {
        // открытие страницы авторизации
        open("/");
        //проверка надписи "Вход в учетную запись"на странице авторизации
        element("#kc-page-title").shouldHave(Condition.text("Вход в учетную запись"));
    }

    @Test
        // autorizacion invalid manager
    void InvalidLoginManager() {
        // открытие страницы авторизации
        open("/");
        // вызов метода авторизации
        authPageData.loginManagerInPageAuth(authPageData.getLoginManager() + "invalid", authPageData.getPassManager() + "invalid");
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @Test
        // autorizacion manager
    void loginAndLoguotManager() {
        // открытие страницы авторизации
        open("/");
        // вызов метода авторизации менеджера
        authPageData.loginManagerInPageAuth(authPageData.getLoginManager(), authPageData.getPassManager());
        //проверка на открытой странице расписания раздела "Расписание" после авторизации
        element(".admin-menu").shouldHave(Condition.text("Расписание"));
        // проверка разлогина менеджера
        element("[title=Выйти]").click();
    }

    @Test
        // autorizacion invalid operator
    void InvalidLoginOperator() {
        // открытие страницы авторизации
        open("/");
        // вызов метода авторизации оператора
        authPageData.loginOperatorInPageAuth(authPageData.getLoginOperator() + "invalid", authPageData.getPassOperator() + "invalid");
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @Test
        // autorizacion operator
    void loginAndLoguotOperator() {
        open("/");
        // вызов метода авторизации оператора
        authPageData.loginOperatorInPageAuth(authPageData.getLoginOperator(), authPageData.getPassOperator());
        //проверка на открытой странице расписания раздела "Рабочие смены" после авторизации оператора
        element(".main-menu").shouldHave(Condition.text("Рабочие смены"));
        // проверка разлогина operator
        element("[title=Выйти]").click();
    }
}
