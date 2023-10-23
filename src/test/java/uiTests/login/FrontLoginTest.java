package uiTests.login;

import com.codeborne.selenide.Condition;
import uiTests.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.autorization.AuthPage;

import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;

class FrontLoginTest extends BaseTest {
    AuthPage authPage = new AuthPage();

    @DisplayName("Открытие страницы авторизации")
    @Test
    void openPageAutorization() {
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
        authPage.loginInPageAuth(authPage.getLoginManager() + "invalid", authPage.getPassManager() + "invalid");
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @DisplayName("Авторизация менеджера")
    @Test
    void loginAndLoguotManager() {
        // открытие страницы авторизации
        open("/");
        // вызов метода авторизации менеджера
        authPage.loginInPageAuth(authPage.getLoginManager(), authPage.getPassManager());
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
        authPage.loginInPageAuth(authPage.getLoginOperator() + "invalid", authPage.getPassOperator() + "invalid");
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @DisplayName("Авторизация оператора")
    @Test
    void loginAndLoguotOperator() {
        open("/");
        // вызов метода авторизации оператора
        authPage.loginInPageAuth(authPage.getLoginOperator(), authPage.getPassOperator());
        //проверка на открытой странице расписания раздела "Рабочие смены" после авторизации оператора
        element(".main-menu").shouldHave(Condition.text("Рабочие смены"));
        // проверка разлогина operator
        element("[title=Выйти]").click();
    }
}
