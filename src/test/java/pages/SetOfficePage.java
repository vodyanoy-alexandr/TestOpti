package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;

public class SetOfficePage {
    AuthPage authPage = new AuthPage();

    // метод открывает страницу настроек офисов
    public void openPage() {
        // открытие страницы настроек офиса
        open("/settings/offices");
        // вызов метода авторизации на странице из класс AuthPage
        authPage.loginManagerInPageAuth(authPage.getLoginManager(), authPage.getPassManager());
        // проверка открытия страницы "Настройка офисов"
        element(".offices").shouldHave(text("Настройка офисов"));
    }

    // метод открывает шторку добавления нового офиса
    public void addNewOffice() {
        // нажатие на кнопку "Добавить офис"
        element(".button-transparent-wrap")
                .$(byText("Добавить офис"))
                .click();
        // проверка что открылась шторка "Добавление офиса"
        element("div.header-drawer")
                .shouldHave(text("Добавление офиса"));
    }

    //метод вводит название нового офиса (в параметрах передать название)
    public void setNameOffice(String var) {
        element(".form-table-row", 0)
                .$("[type = text]")
                .setValue(var);
    }
}