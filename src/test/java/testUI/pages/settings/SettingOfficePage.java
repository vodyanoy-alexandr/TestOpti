package testUI.pages.settings;

import com.codeborne.selenide.SelenideElement;
import dataTest.BaseDataTest;
import testUI.componets.Notifications;
import testUI.componets.TimeZoneComponents;
import testUI.pages.autorization.AuthPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SettingOfficePage {
    public static final String MONDAY = "Пн.";
    public static final String TUESDAY = "Вт.";
    public static final String WEDNESDAY = "Ср.";
    public static final String THURSDAY = "Чт.";
    public static final String FRIDAY = "Пт.";
    public static final String SATURDAY = "Сб.";
    public static final String SUNDAY = "Вс.";
    private final SelenideElement pageTitle = $(".offices");
    AuthPage authPage = new AuthPage();
    static BaseDataTest dataTest = new BaseDataTest();
    TimeZoneComponents timeZoneComponents = new TimeZoneComponents();
    Notifications notifications = new Notifications();

    public String getMonday() {
        return MONDAY;
    }

    public String getTuesday() {
        return TUESDAY;
    }

    public String getWednesday() {
        return WEDNESDAY;
    }

    public String getThursday() {
        return THURSDAY;
    }

    public String getFriday() {
        return FRIDAY;
    }

    public String getSaturday() {
        return SATURDAY;
    }

    public String getSunday() {
        return SUNDAY;
    }

    public SelenideElement getPageTitle() {
        return pageTitle;
    }// метод открывает страницу настроек офисов

    public void openPage() {
        // открытие страницы настроек офиса
        open("/settings/offices");
        // вызов метода авторизации на странице из класс AuthPage
        authPage.loginInPageAuth(dataTest.getLoginManager(), dataTest.getPassManager());
    }

    // метод открывает шторку добавления нового офиса
    public void openModalAddNewOffice() {
        // нажатие на кнопку "Добавить офис"
        $(".button-transparent-wrap")
                .$(byText("Добавить офис"))
                .click();
        // проверка что открылась шторка "Добавление офиса"
        $("div.header-drawer")
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
    public void clickButtonAddEditOffice() {
        $(".button.button-stripped")
                .click();
    }

    // метод удаляет офис из таблицы (в параметрах передать название офиса)
    public void delOffice(String nameOffice) {
        $(".offices__table-wrap")
                .$$(".enable-transition, .virtualized-table__cell")
                .find(text(nameOffice))
                .$(".svg-icon ")
                .click();
        // подтверждение удаления офиса
        $(".button.button--confirm-buttons")
                .click();
        //проверка что офиса больше нет
        $(".offices__table-wrap").shouldNotHave(text(nameOffice));
    }

    // метод открывает офис из таблицы (в параметрах передать название офиса)
    public void openOffice(String nameOffice) {
        $(".offices__table-wrap")
                .$$(".enable-transition, .virtualized-table__cell")
                .find(text(nameOffice))
                .click();
    }

    // метод нажимает на кнопку "Импортировать" и загружает файл из resources (в параметрах передать путь к файлу)
    public void importOffice(String file) {
        // нажатие на кнопку "Импортировать" и загрузка файла
        $("[type=file]").uploadFile(new File(file));
    }

    // метод вызывает метод из класса Notification (проверка на текст уведомления)
    public void shouldHaveNotification(String text) {
        notifications.shouldHaveNotification(text);
    }

    // метод проверяет что офис с введеным названием есть в таблице
    public void shouldHaveOffice(String nameOffice) {
        $(".offices__table-wrap").shouldHave(text(nameOffice));
    }

    // приватный метод выбирает элемент (поле с параметрами) по переданному индексу (порядок  расположения полей) в шторке создания/редактирования офиса
    private SelenideElement setField(int index) {
        return $(".form-table-row", index);
    }
}