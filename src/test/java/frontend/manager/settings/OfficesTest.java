package frontend.manager.settings;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import datatest.DataTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class OfficesTest {
  static public DataTest data = new DataTest();

    public static void main(String[] args) {
        Configuration.browserSize = "1920x1080"; // размер окна браузера
        Configuration.holdBrowserOpen = true; // оставлять окно браузера открытым
        Configuration.baseUrl = data.getUrlStand(); // задать базовый url
        System.out.println("Start tests");
    }

    @AfterEach
    void closeWindowAfterTest(){
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

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
