package datatest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.AuthPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

// класс с базовыми тестами
public class DataTest {
    private static String urlStand = "https://master.dc.oswfm.ru"; // базовый url

    public String getUrlStand() {
        return urlStand;
    }
}


