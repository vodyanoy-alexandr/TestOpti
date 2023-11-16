package uiTest.pages.settings;

import dataTest.DataTest;
import uiTest.pages.autorization.AuthPage;

import static com.codeborne.selenide.Selenide.open;

public class SettingOrganizationStructurePage {
    AuthPage authPage = new AuthPage();
    static DataTest dataTest = new DataTest();

    public void openPage() {
        // открытие страницы настроек офиса
        open("/settings/permission-groups");
        // вызов метода авторизации на странице из класс AuthPage
        authPage.loginInPageAuth(dataTest.getLoginManager(), dataTest.getPassManager());
    }

}
