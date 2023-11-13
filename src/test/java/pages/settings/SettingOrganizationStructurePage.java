package pages.settings;

import pages.autorization.AuthPage;

import static com.codeborne.selenide.Selenide.open;

public class SettingOrganizationStructurePage {
    AuthPage authPage = new AuthPage();

    public void openPage() {
        // открытие страницы настроек офиса
        open("/settings/permission-groups");
        // вызов метода авторизации на странице из класс AuthPage
        authPage.loginInPageAuth(authPage.getLoginManager(), authPage.getPassManager());
    }

}
