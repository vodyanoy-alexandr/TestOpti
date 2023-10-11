package datatest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.AuthPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

// класс с базовыми тестами
public class TestBase {
    private static String urlStand = "https://master.dc.oswfm.ru"; // базовый url

    public String getUrlStand() {
        return urlStand;
    }

    public static AuthPage authPage = new AuthPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = urlStand; // базовый url
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
}
