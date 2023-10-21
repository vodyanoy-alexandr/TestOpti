package frontend.manager.settings;

import frontend.BaseTest;
import org.junit.jupiter.api.Test;
import pages.settings.SettingOfficePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

class SettingOfficesTest extends BaseTest {
    SettingOfficePage settingOfficePage = new SettingOfficePage();

    @Test
        // Проверка открытия страницы настроек "Офисы"
     void openPageSetOffice() {
        settingOfficePage.openPage();
    }

    @Test
        // Добавление нового офиса и удаление
    void addNewOffice() {
        String nameOffice = "Новый офис 888";

        // открытие страницы настроек офиса через вызов метода из класса SetOfficePage
        settingOfficePage.openPage();
        // открытие шторки добавления нового офиса
        settingOfficePage.openModalAddNewOffice();
        // ввод в инпут "Название" имя офиса
        settingOfficePage.setNameOffice(nameOffice);
        // добавление тайм зоны
        settingOfficePage.setTimeZone("+04:00 (Europe/Samara)");
        // ставим чекбокс выходной на понедельник
        settingOfficePage.checkboxWeekend("Пн.");
        // выставляем время начала работы офиса во вторник с 12:00
       settingOfficePage.setStartWork("Вт.", "1200");
        // выставляем время окончания работы офиса во вторник с 22:30
        settingOfficePage.setEndWork("Вт.", "2230");
        // снимаем чекбокс выходной с субботы
       settingOfficePage.checkboxWeekend("Сб.");
        // ввод в инпут "Комментарий" комментарий
        settingOfficePage.setComment("Офис создан в рамках автотеста на Selenide");
        // ввод в инпут "Рабочих мест" колличество рабочих мест
        settingOfficePage.setWorkplaces(666);
        // нажатие кнопки "Добавить офис"
        settingOfficePage.clickButtonAddOffice();
        // проверка уведомления что офис создан
        settingOfficePage.shouldHaveNotification("Добавлен новый офис");
        //Проверка что в таблице появился офис с названием
        element(".offices__table-wrap").shouldHave(text(nameOffice));
        // удаление офиса
        settingOfficePage.delOffice(nameOffice);

    }

    @Test
        // Создание офиса с неуникальным именем
    void addNewOfficeWithInvalidName() {
        String nameOffice = "Новый офис 7777";
        // открытие страницы настроек офиса через вызов метода из класса SetOfficePage
        settingOfficePage.openPage();
        // открытие шторки добавления нового офиса
        settingOfficePage.openModalAddNewOffice();
        // ввод в инпут "Название" имя офиса
        settingOfficePage.setNameOffice(nameOffice);
        // добавление тайм зоны
        settingOfficePage.setTimeZone("+03:00 (Europe/Moscow)");
        // нажатие кнопки добавления офиса
        settingOfficePage.clickButtonAddOffice();
        // проверка уведомления что офис создан
        settingOfficePage.shouldHaveNotification("Добавлен новый офис");
        // повторное открытие шторки добавления офиса
        settingOfficePage.openModalAddNewOffice();
        // ввод в инпут "Название" имя уже существуещего офиса
        settingOfficePage.setNameOffice(nameOffice);
        // добавление тайм зоны
        settingOfficePage.setTimeZone("+03:00 (Europe/Moscow)");
        // нажатие кнопки добавления офиса
        settingOfficePage.clickButtonAddOffice();
        // проверка уведомления что офис не уникальный
        settingOfficePage.shouldHaveNotification("Не уникально");
        // удаление офиса todo переделать на апишку удаление тестовых данных (офиса)
        settingOfficePage.delOffice(nameOffice);
    }
}
