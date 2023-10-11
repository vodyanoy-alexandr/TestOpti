package frontend.manager.settings;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import datatest.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class OfficesTest extends TestBase {

    @Test
        // open page Offices
    void openPageSetOffies() {
        // открытие страницы настроек офиса
        open("/settings/offices");
        // вызов метода авторизации на странице из класс AuthPageDataTest
        authPage.loginManagerInPageAuth(authPage.getLoginManager(), authPage.getPassManager());
        // проверка открытия страницы "Настройка офисов"
        element(".offices").shouldHave(text("Настройка офисов"));
    }

    @Test
        // Добавление нового офиса
    void addNewOffies() {
        // открытие страницы настроек офиса
        open("/settings/offices");
        // вызов метода авторизации на странице из класс AuthPageDataTest
        authPage.loginManagerInPageAuth(authPage.getLoginManager(), authPage.getPassManager());

        // нажатие на кнопку "Добавить офис"
        element(".button-transparent-wrap")
                .$(byText("Добавить офис"))
                .click();
        // проверка что открылась шторка "Добавление офиса"
        element("div.header-drawer")
                .shouldHave(text("Добавление офиса"));
        // ввод в инпут "Название" имя офиса
        element(".form-table-row", 0)
                .$("[type = text]")
                .setValue("testOffice");
        // клик на инпут "Часовой пояс"
        element(".form-table-row", 1)
                .$("[type = text]")
                .click();
        // выбор екб
        elements(".auto-complete__item")
                .filterBy(text("+05:00 (Asia/Yekaterinburg)"))
                .first()
                .click();
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
                .setValue("Комментарий для newOffice");
        // ввод в инпут "Рабочих мест" колличество рабочих мест
        element(".form-table-row", 4)
                .$("[type = text]")
                .setValue("777");
        // нажатие кнопки "Сохранить изменения"
        element(".button.button-stripped")
                .click();
        //Проверка что в таблице появился офис с названием
        element(".offices__table-wrap").shouldHave(text("testOffice"));

        sleep(5000);


    }
}
