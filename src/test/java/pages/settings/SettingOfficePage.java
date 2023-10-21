package pages.settings;

import com.codeborne.selenide.SelenideElement;
import frontend.componets.Notifications;
import frontend.componets.TimeZoneComponents;
import pages.autorization.AuthPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;

public class SettingOfficePage {
    AuthPage authPage = new AuthPage();
    TimeZoneComponents timeZoneComponents = new TimeZoneComponents();
    Notifications notifications = new Notifications();

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
    public void setNameOffice(String name) {
        setField(0)
                .$("[type = text]")
                .setValue(name);
    }

    // метод выбирает тайм зону в шторке создания нового офиса (передать в параметрах тайм зону)
    public void setTimeZone(String timeZone) {
        // клик на инпут "Часовой пояс"
        setField(1)
                .$("[type = text]")
                .click();
        // выбор часового пояса в выпадашке
        timeZoneComponents.selectTimeZone(timeZone);
    }

    // метод устанавливает/снимает чекбокс "Выходной" на день недели (передать в параметрах)
    public void checkboxWeekend(String day) {
        setField(2)
                .$(byText(day))
                .parent()
                .$(".checkbox__input")
                .click();
    }

    // метод устанавливает время начала работы офиса (в параметрах передать день и время "1200")
    public void setStartWork(String day, String time) {
        setField(2)
                .$(byText(day))
                .parent()
                .$("[type=text]", 0)
                .doubleClick()
                .sendKeys(time);
    }

    // метод устанавливает время начала работы офиса (в параметрах передать день "Пн." и время "1200")
    public void setEndWork(String day, String time) {
        setField(2)
                .$(byText(day))
                .parent()
                .$("[type=text]", 1)
                .doubleClick()
                .sendKeys(time);
    }

    //метод вводит в инпут "Комментарий" комментарий (текст передать в параметрах)
    public void setComment(String comment) {
        setField(3)
                .$("[type = text]")
                .setValue(comment);
    }

    // ввод в инпут "Рабочих мест" колличество рабочих мест (передать в параметрах количество мест)
    public void setWorkplaces(int places) {
        setField(4)
                .$("[type = text]")
                .setValue(String.valueOf(places));
    }

    // метод нажимает кнопку "Сохранить изменения"/"Добавить офис" в шторке редактирования/добавления офиса
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

    // метод вызывает метод из класса Notification (проверка на текст уведомления)
    public void shouldHaveNotification(String text) {
        notifications.shouldHaveNotification(text);
    }

    // приватный метод выбирает элемент (поле с параметрами) по переданному индексу (порядок  расположения полей) в шторке создания/редактирования офиса
    private SelenideElement setField(int index) {
        return element(".form-table-row", index);
    }

}
