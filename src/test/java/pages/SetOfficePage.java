package pages;

import pages.componets.TimeZoneComponents;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;

public class SetOfficePage {
    AuthPage authPage = new AuthPage();
    TimeZoneComponents timeZoneComponents = new TimeZoneComponents();

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
    public void openModalAddNewOffice() {
        // нажатие на кнопку "Добавить офис"
        element(".button-transparent-wrap")
                .$(byText("Добавить офис"))
                .click();
        // проверка что открылась шторка "Добавление офиса"
        element("div.header-drawer")
                .shouldHave(text("Добавление офиса"));
    }

    // метод вводит название нового офиса (в параметрах передать название)
    public void setNameOffice(String var) {
        element(".form-table-row", 0)
                .$("[type = text]")
                .setValue(var);
    }

    // метод выбирает тайм зону в шторке создания нового офиса (передать в параметрах тайм зону)
    public void setTimeZone(String timeZone) {
        // клик на инпут "Часовой пояс"
        element(".form-table-row", 1)
                .$("[type = text]")
                .click();
        // выбор часового пояса в выпадашке
        timeZoneComponents.selectTimeZone(timeZone);
    }

    // метод нажимает кнопку "Сохранить изменения"/ "Добавить офис" в шторке редактирования/добавления офиса
    public void clickButtonAddOffice() {
        element(".button.button-stripped")
                .click();
    }

    // метод удаляет офис из таблицы (в параметрах передать название офиса)
    public void delOffice(String nameOffice) {
        element(".offices__table-wrap")
                .$$(".enable-transition, .virtualized-table__cell")
                .filterBy(text(nameOffice))
                .first()
                .$(".svg-icon ")
                .click();
        // подтверждение удаления офиса
        element(".button.button--confirm-buttons")
                .click();
        //проверка что офиса больше нет
        element(".offices__table-wrap").shouldNotHave(text(nameOffice));
    }

}
