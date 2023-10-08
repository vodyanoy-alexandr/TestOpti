package frontend.manager.settings;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import datatest.DataTest;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class OfficesTest {
  static public DataTest data = new DataTest();

  @BeforeAll
  static void beforeAll() {
        Configuration.browserSize = "1920x1080"; // размер окна браузера
        Configuration.holdBrowserOpen = true; // оставлять окно браузера открытым
        Configuration.baseUrl = data.getUrlStand(); // задать базовый url
        System.out.println("Start tests");
    }

    @AfterEach
    void afterEach(){
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @Test // open page Offices
    void openPageSetOffies() {
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
