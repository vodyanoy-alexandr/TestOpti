package uiTest.pages.settings;

import com.codeborne.selenide.SelenideElement;
import dataTest.BaseDataTest;
import uiTest.pages.autorization.AuthPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SettingOrgStructurePage {
    AuthPage authPage = new AuthPage();
    static BaseDataTest dataTest = new BaseDataTest();
    private final SelenideElement pageTitle = $(".C5o9c51jzim5-OJ5iDtI4g\\=\\=");

    public SelenideElement getPageTitle() {
        return pageTitle;
    }



    public void openPage() {
        // открытие страницы настроек офиса
        open("/settings/permission-groups");
        // вызов метода авторизации на странице из класс AuthPage
        authPage.loginInPageAuth(dataTest.getLoginManager(), dataTest.getPassManager());
    }

}
