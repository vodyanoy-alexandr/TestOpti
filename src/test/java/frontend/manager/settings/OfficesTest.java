package frontend.manager.settings;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import datatest.AuthPageData;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

class OfficesTest {
    static AuthPageData authPageData = new AuthPageData();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = authPageData.getUrlStand(); // задать базовый url
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
        // вызов метода авторизации на странице из класс AuthPageDataTest
        authPageData.loginManagerInPageAuth(authPageData.getLoginManager(), authPageData.getPassManager());
        // проверка открытия страницы "Настройка офисов"
        element(".offices").shouldHave(Condition.text("Настройка офисов"));
    }
}
