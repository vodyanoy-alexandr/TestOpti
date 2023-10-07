package frontend.login;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.*;
import datatest.DataTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

class FrontLoginTest {
    DataTest data = new DataTest();

    // open page autorizacion
    @Test
    void openPageAutorization() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = data.getUrlStand();

        // открытие страницы авторизации
        open("/");
        //проверка надписи "Вход в учетную запись"на странице авторизации
        element("#kc-page-title").shouldHave(Condition.text("Вход в учетную запись"));
        closeWindow();
    }

    //autorizacion invalid manager
    @Test
    void InvalidLoginManager() {
        Configuration.baseUrl = data.getUrlStand();
        open("/");
        //ввод в инпут username невалидного логина менеджера
        element("[name=username]").setValue("invalid" + data.getLoginManager());
        //ввод в инпут password невалидного пароля менеджера
        element("[name=password]").setValue("invalid" + data.getPassManager());
        //нажатие на иконку "Вход"
        element("[name=login]").click();
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
        closeWindow();

    }

    //autorizacion manager
    @Test
    void loginAndLoguotManager() {
        Configuration.baseUrl = data.getUrlStand();
        open("/");
        //ввод в инпут username логина менеджера
        element("[name=username]").setValue(data.getLoginManager());
        //ввод в инпут password пароля менеджера
        element("[name=password]").setValue(data.getPassManager());
        //нажатие на иконку "Вход"
        element("[name=login]").click();
        //проверка на открытой странице расписания раздела "Расписание" после авторизации
        element(".admin-menu").shouldHave(Condition.text("Расписание"));
        // проверка разлогина менеджера
        element("[title=Выйти]").click();
        clearBrowserCache();
        clearBrowserCookies();
        closeWindow();
    }

    //autorizacion invalid operator
    @Test
    void InvalidLoginOperator() {
        Configuration.baseUrl = data.getUrlStand();
        open("/");
        //ввод в инпут username невалидного логина менеджера
        element("[name=username]").setValue("invalid" + data.getLoginOperator());
        //ввод в инпут password невалидного пароля менеджера
        element("[name=password]").setValue("invalid" + data.getPassOperator());
        //нажатие на иконку "Вход"
        element("[name=login]").click();
        //проверка на предупреждение о невалидном логине или пароле
        element("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
        closeWindow();

    }

    //autorizacion operator
    @Test
    void loginAndLoguotOperator() {
        Configuration.baseUrl = data.getUrlStand();
        open("/");
        //ввод в инпут username логина менеджера
        element("[name=username]").setValue(data.getLoginOperator());
        //ввод в инпут password пароля менеджера
        element("[name=password]").setValue(data.getPassOperator());
        //нажатие на иконку "Вход"
        element("[name=login]").click();
        //проверка на открытой странице расписания раздела "Рабочие смены" после авторизации оператора
        element(".main-menu").shouldHave(Condition.text("Рабочие смены"));
        // проверка разлогина operator
        element("[title=Выйти]").click();
        clearBrowserCache();
        clearBrowserCookies();
        closeWindow();

    }

}
