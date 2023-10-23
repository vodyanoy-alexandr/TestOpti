package frontend.manager.settings;

import frontend.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.settings.SettingOfficePage;

//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Selenide.*;

class SettingOfficesTest extends BaseTest {
    SettingOfficePage settingOfficePage = new SettingOfficePage();

    @DisplayName("Проверка открытия страницы настроек 'Офисы'")
    @Test
     void openPageSetOffice() {
        settingOfficePage.openPage();
    }

    @DisplayName("Добавление нового офиса и удаление")
    @Test
    void addNewOffice() {
        String nameOffice = "Новый офис 888";
        String comment = "Офис создан в рамках автотеста на Selenide";

        // открытие страницы настроек офиса через вызов метода из класса SetOfficePage
        settingOfficePage.openPage();
        // открытие шторки добавления нового офиса
        settingOfficePage.openModalAddNewOffice();
        // ввод в инпут "Название" имя офиса
        settingOfficePage.setNameOffice(nameOffice);
        // добавление тайм зоны
        settingOfficePage.setTimeZone("+04:00 (Europe/Samara)");
        // ставим чекбокс выходной на понедельник
        settingOfficePage.checkboxWeekend(settingOfficePage.getMonday());
        // выставляем время начала работы офиса во вторник с 12:00
       settingOfficePage.setStartWork(settingOfficePage.getTuesday(), "1200");
        // выставляем время окончания работы офиса во вторник с 22:30
        settingOfficePage.setEndWork(settingOfficePage.getTuesday(), "2230");
        // снимаем чекбокс выходной с субботы
       settingOfficePage.checkboxWeekend(settingOfficePage.getSaturday());
        // ввод в инпут "Комментарий" комментарий
        settingOfficePage.setComment(comment);
        // ввод в инпут "Рабочих мест" колличество рабочих мест
        settingOfficePage.setWorkplaces(666);
        // нажатие кнопки "Добавить офис"
        settingOfficePage.clickButtonAddOffice();
        // проверка уведомления что офис создан
        settingOfficePage.shouldHaveNotification("Добавлен новый офис");
        //Проверка что в таблице появился офис с названием
        settingOfficePage.shouldHaveOffice(nameOffice);
        // удаление офиса
        settingOfficePage.delOffice(nameOffice);

    }

    @DisplayName("Создание офиса с неуникальным именем")
    @Test
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
