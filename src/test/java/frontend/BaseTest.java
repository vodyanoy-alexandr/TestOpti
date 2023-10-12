package frontend;

import com.codeborne.selenide.Configuration;
import datatest.DataTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class BaseTest {
    static DataTest dataTest = new DataTest();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = dataTest.getUrlStand(); // базовый url
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
