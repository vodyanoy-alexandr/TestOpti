package frontend.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.*;
import datatest.AuthorizationPageDataTest;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

class FrontLoginTest {
    static AuthorizationPageDataTest data = new AuthorizationPageDataTest();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = data.getUrlStand(); // базовый url
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
        //ввод в инпут username невалидного логина менеджера
        data.getUserNameInput().setValue("invalid" + data.getLoginManager());
        //ввод в инпут password невалидного пароля менеджера
        data.getPasswordInput().setValue("invalid" + data.getPassManager());
        //нажатие на иконку "Вход"
        data.getLoginButton().click();
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @Test
        // autorizacion manager
    void loginAndLoguotManager() {
        // открытие страницы авторизации
        open("/");
        //ввод в инпут username логина менеджера
        data.getUserNameInput().setValue(data.getLoginManager());
        //ввод в инпут password пароля менеджера
        data.getPasswordInput().setValue(data.getPassManager());
        //нажатие на иконку "Вход"
        data.getLoginButton().click();
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
        //ввод в инпут username невалидного логина менеджера
        data.getUserNameInput().setValue("invalid" + data.getLoginOperator());
        //ввод в инпут password невалидного пароля менеджера
        data.getPasswordInput().setValue("invalid" + data.getPassOperator());
        //нажатие на иконку "Вход"
        data.getLoginButton().click();
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @Test
        // autorizacion operator
    void loginAndLoguotOperator() {
        open("/");
        //ввод в инпут username логина менеджера
        data.getUserNameInput().setValue(data.getLoginOperator());
        //ввод в инпут password пароля менеджера
        data.getPasswordInput().setValue(data.getPassOperator());
        //нажатие на иконку "Вход"
        data.getLoginButton().click();
        //проверка на открытой странице расписания раздела "Рабочие смены" после авторизации оператора
        element(".main-menu").shouldHave(Condition.text("Рабочие смены"));
        // проверка разлогина operator
        element("[title=Выйти]").click();
    }
}
