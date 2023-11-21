package uiTest.pages.settings;

import dataTest.BaseDataTest;
import uiTest.pages.autorization.AuthPage;

import static com.codeborne.selenide.Selenide.open;

public class SettingOrganizationStructurePage {
    AuthPage authPage = new AuthPage();
    static BaseDataTest dataTest = new BaseDataTest();

    public void openPage() {
        // открытие страницы настроек офиса
        open("/settings/permission-groups");
        // вызов метода авторизации на странице из класс AuthPage
        authPage.loginInPageAuth(dataTest.getLoginManager(), dataTest.getPassManager());
    }

}
