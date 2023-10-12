package frontend.manager.settings;

import frontend.BaseTest;
import frontend.componets.TimeZoneComponents;
import org.junit.jupiter.api.Test;
import pages.AuthPage;
import pages.SetOfficePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class SetOfficesTest extends BaseTest {
    SetOfficePage setOfficePage = new SetOfficePage();
    @Test
        // Проверка открытия страницы настроек "Офисы"
    void openPageSetOffice() {
        setOfficePage.openPage();
    }

    @Test
        // Добавление нового офиса
    void addNewOffice() {
        String nameOffice = "Новый офис";

        // открытие страницы настроек офиса через вызов метода из класса SetOfficePage
        setOfficePage.openPage();
        // открытие шторки добавления нового офиса
        setOfficePage.addNewOffice();
        // ввод в инпут "Название" имя офиса
        setOfficePage.setNameOffice(nameOffice);
        // добавление тайм зоны
        setOfficePage.setTimeZone("+04:00 (Europe/Samara)");
        // ставим чекбокс выходной на понедельник
        element(".form-table-row", 2)
                .$(byText("Пн."))
                .parent()
                .$(".checkbox__input")
                .click();
        // выставляем время начала работы офиса во вторник с 12:00
        element(".form-table-row", 2)
                .$(byText("Вт."))
                .parent()
                .$("[type=text]", 0)
                .doubleClick()
                .sendKeys("1200");
        // выставляем время окончания работы офиса во вторник с 22:30
        element(".form-table-row", 2)
                .$(byText("Вт."))
                .parent()
                .$("[type=text]", 1)
                .doubleClick()
                .sendKeys("2230");
        // снимаем чекбокс выходной с субботы
        element(".form-table-row", 2)
                .$(byText("Сб."))
                .parent()
                .$(".checkbox__input")
                .click();
        // ввод в инпут "Комментарий" комментарий
        element(".form-table-row", 3)
                .$("[type = text]")
                .setValue("Офис создан в рамках автотеста на Selenide");
        // ввод в инпут "Рабочих мест" колличество рабочих мест
        element(".form-table-row", 4)
                .$("[type = text]")
                .setValue("777");
        // нажатие кнопки "Сохранить изменения"
        element(".button.button-stripped")
                .click();
        //Проверка что в таблице появился офис с названием
        element(".offices__table-wrap").shouldHave(text(nameOffice));
    }
}
