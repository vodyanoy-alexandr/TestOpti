package frontend.login;

import com.codeborne.selenide.Condition;
import datatest.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;

class FrontLoginTest extends TestBase {

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
        authPage.loginManagerInPageAuth(authPage.getLoginManager() + "invalid", authPage.getPassManager() + "invalid");
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @Test
        // autorizacion manager
    void loginAndLoguotManager() {
        // открытие страницы авторизации
        open("/");
        // вызов метода авторизации менеджера
        authPage.loginManagerInPageAuth(authPage.getLoginManager(), authPage.getPassManager());
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
        authPage.loginOperatorInPageAuth(authPage.getLoginOperator() + "invalid", authPage.getPassOperator() + "invalid");
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    @Test
        // autorizacion operator
    void loginAndLoguotOperator() {
        open("/");
        // вызов метода авторизации оператора
        authPage.loginOperatorInPageAuth(authPage.getLoginOperator(), authPage.getPassOperator());
        //проверка на открытой странице расписания раздела "Рабочие смены" после авторизации оператора
        element(".main-menu").shouldHave(Condition.text("Рабочие смены"));
        // проверка разлогина operator
        element("[title=Выйти]").click();
    }
}
