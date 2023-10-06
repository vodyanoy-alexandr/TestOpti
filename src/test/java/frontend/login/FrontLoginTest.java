package frontend.login;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.*;
import datatest.DataTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FrontLoginTest {
    DataTest data = new DataTest();

    // open page autorizacion
    @Test
    void openPageAutorization() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = data.getUrlStand();

        // открытие страницы авторизации
        Selenide.open("/");
        //проверка надписи "Вход в учетную запись"на странице авторизации
        Selenide.$("#kc-page-title").shouldHave(Condition.text("Вход в учетную запись"));
    }

    //autorizacion invalid manager
    @Test
    void invalidLoginManagerWFM() {
        //ввод в инпут username невалидного логина менеджера
        Selenide.$("[name=username]").setValue("invalid" + data.getLogin());
        //ввод в инпут password невалидного пароля менеджера
        Selenide.$("[name=password]").setValue("invalid" + data.getPassword());
        //нажатие на иконку "Вход"
        Selenide.$("[name=login]").click();
        //проверка на предупреждение о невалидном логине или пароле
        Selenide.$("#input-error").shouldHave(Condition.text("Неправильное имя пользователя или пароль."));
    }

    //autorizacion manager
    @Test
    void loginManagerWFM() {

        //ввод в инпут username логина менеджера
        Selenide.$("[name=username]").setValue(data.getLogin());
        //ввод в инпут password пароля менеджера
        Selenide.$("[name=password]").setValue(data.getPassword());
        //нажатие на иконку "Вход"
        Selenide.$("[name=login]").click();
        //проверка на открытой странице расписания раздела "Расписание" после авторизации
        Selenide.$(".admin-menu").shouldHave(Condition.text("Расписание"));
        // проверка разлогина
        Selenide.$("[title=Выйти]").click();

    }
}
