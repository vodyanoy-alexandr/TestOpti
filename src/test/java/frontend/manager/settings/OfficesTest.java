package frontend.manager.settings;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import datatest.DataTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class OfficesTest {
   DataTest data = new DataTest();

    @Test
        // open page Offices
    void openPageSetOffies() {
        //Configuration.holdBrowserOpen = true; // включить для визуализации происходящего в браузере
        Configuration.baseUrl = data.getUrlStand();

        // открытие страницы настроек офиса
        open("/settings/offices");
        //ввод в инпут username логина менеджера
        element("[name=username]").setValue(data.getLoginManager());
        //ввод в инпут password пароля менеджера
        element("[name=password]").setValue(data.getPassManager());
        //нажатие на иконку "Вход"
        element("[name=login]").click();
        // проверка открытия страницы "Настройка офисов"
        element(".offices").shouldHave(Condition.text("Настройка офисов"));

    }
}
