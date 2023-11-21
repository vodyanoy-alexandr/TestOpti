package uiTest.testCase.manager.settings;

import com.codeborne.selenide.Configuration;
import dataTest.BaseDataTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uiTest.pages.settings.SettingOrganizationStructurePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

class SettingOrganizationStructureTest {

    static BaseDataTest dataTest = new BaseDataTest();
    SettingOrganizationStructurePage settingOrganizationStructurePage = new SettingOrganizationStructurePage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = dataTest.getUrlStand(); // базовый url
        Configuration.browserSize = "1920x1080"; // размер окна браузера
        Configuration.holdBrowserOpen = false; // оставлять окно браузера открытым
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
        settingOrganizationStructurePage.openPage();
        // проверка открытия страницы "Настройка офисов"
        $(".C5o9c51jzim5-OJ5iDtI4g\\=\\=").shouldHave(text("Структура организации"));
    }
}
