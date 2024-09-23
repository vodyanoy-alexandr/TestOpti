package testUI.testCase.manager.settings;

import com.codeborne.selenide.Configuration;
import dataTest.BaseDataTest;
import org.junit.jupiter.api.*;
import testUI.pages.settings.SettingOrgStructurePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
@Disabled
class SettingOrgStructureTest {

    static BaseDataTest baseDataTest = new BaseDataTest();
    SettingOrgStructurePage settingOrgStructurePage = new SettingOrgStructurePage();


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = baseDataTest.getUrlStand(); // базовый url
        Configuration.browserSize = "1920x1080"; // размер окна браузера
        Configuration.holdBrowserOpen = true; // оставлять окно браузера открытым
        Configuration.timeout = 5000; // таймаут ожидаия загрузки (появления элемента на странице), полезно когда стенд работает медленно
    }

    @AfterEach
    void afterEach() {
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @DisplayName("Проверка открытия страницы настроек 'Структура организации'")
    @Test
    void openPageSetOffice() {
        settingOrgStructurePage.openPage();
        // проверка открытия страницы "Настройка офисов"
        settingOrgStructurePage.getPageTitle().shouldHave(text("Структура организации"));
    }
}
