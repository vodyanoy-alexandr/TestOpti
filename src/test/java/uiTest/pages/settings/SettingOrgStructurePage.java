package uiTest.pages.settings;

import com.codeborne.selenide.SelenideElement;
import dataTest.BaseDataTest;
import uiTest.pages.autorization.AuthPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SettingOrgStructurePage {
    AuthPage authPage = new AuthPage();
    static BaseDataTest dataTest = new BaseDataTest();
    private final SelenideElement pageTitle = $(".aqNAZYWTMtzG6F7JgO78");

    public SelenideElement getPageTitle() {
        return pageTitle;
    }

    public void openPage() {
        open("/");
        authPage.loginInPageAuth(dataTest.getLoginManager(), dataTest.getPassManager());
    }

    public void openPagePermGroup() {
        // открытие страницы настроек офиса
        open("/settings/permission-groups");
        // вызов метода авторизации на странице из класс AuthPage

    }

}
