package frontend.manager.settings;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import datatest.AuthorizationPageDataTest;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

class OfficesTest {
    static AuthorizationPageDataTest data = new AuthorizationPageDataTest();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = data.getUrlStand(); // задать базовый url
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
        // open page Offices
    void openPageSetOffies() {
        // открытие страницы настроек офиса
        open("/settings/offices");
        //ввод в инпут username логина менеджера
        data.getUserNameInput().setValue(data.getLoginManager());
        //ввод в инпут password пароля менеджера
        data.getPasswordInput().setValue(data.getPassManager());
        //нажатие на иконку "Вход"
        data.getLoginButton().click();
        // проверка открытия страницы "Настройка офисов"
        element(".offices").shouldHave(Condition.text("Настройка офисов"));

    }
}
