package testUI.testCase.manager.settings;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import dataTest.BaseDataTest;
import org.junit.jupiter.api.*;
import testUI.pages.settings.SettingOfficePage;
import utils.RandomUtils;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
@Disabled
@DisplayName("Тесты на страницу 'Настройка офисов' через UI")
class SettingOfficesTest {
    static Faker faker = new Faker(new Locale("ru"));
    private final static String nameOffice = faker.address().cityName();
    static BaseDataTest baseDataTest = new BaseDataTest();
    SettingOfficePage settingOfficePage = new SettingOfficePage();

    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = baseDataTest.getUrlStand(); // базовый url
        Configuration.browserSize = "1920x1080"; // размер окна браузера
        Configuration.holdBrowserOpen = true; // оставлять окно браузера открытым
        Configuration.timeout = 5000; // таймаут ожидаия загрузки (появления элемента на странице), полезно когда стенд работает медленно
    }

    @AfterEach
    void afterEach() {
        clearBrowserCache();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @DisplayName("Проверка открытия страницы настроек 'Офисы'")
    @Test
    void openPageSetOffice() {
        settingOfficePage.openPage();
        // проверка открытия страницы "Настройка офисов"
        settingOfficePage.getPageTitle().shouldHave(text("Настройка офисов"));
    }

    @DisplayName("Добавление нового офиса и удаление")
    @Test
    void addNewOffice() {

        String comment = "Офис создан в рамках автотестов ui";

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
        settingOfficePage.setWorkplaces(RandomUtils.generateRandomInteger(4));
        // нажатие кнопки "Добавить офис"
        settingOfficePage.clickButtonAddEditOffice();
        // проверка уведомления что офис создан
        settingOfficePage.shouldHaveNotification("Добавлен новый офис");
        //Проверка что в таблице появился офис с названием
        settingOfficePage.shouldHaveOffice(nameOffice);
        // удаление офиса todo перенести удаление тестовых данных на апи
        settingOfficePage.delOffice(nameOffice);
    }

    @DisplayName("Создание офиса с неуникальным именем")
    @Test
    void addNewOfficeWithInvalidName() {

        // открытие страницы настроек офиса через вызов метода из класса SetOfficePage
        settingOfficePage.openPage();
        // открытие шторки добавления нового офиса
        settingOfficePage.openModalAddNewOffice();
        // ввод в инпут "Название" имя офиса
        settingOfficePage.setNameOffice(nameOffice);
        // добавление тайм зоны
        settingOfficePage.setTimeZone("+03:00 (Europe/Moscow)");
        // нажатие кнопки добавления офиса
        settingOfficePage.clickButtonAddEditOffice();
        // проверка уведомления что офис создан
        settingOfficePage.shouldHaveNotification("Добавлен новый офис");
        // повторное открытие шторки добавления офиса
        settingOfficePage.openModalAddNewOffice();
        // ввод в инпут "Название" имя уже существуещего офиса
        settingOfficePage.setNameOffice(nameOffice);
        // добавление тайм зоны
        settingOfficePage.setTimeZone("+03:00 (Europe/Moscow)");
        // нажатие кнопки добавления офиса
        settingOfficePage.clickButtonAddEditOffice();
        // проверка уведомления что офис не уникальный
        settingOfficePage.shouldHaveNotification("Не уникально");
        // удаление офиса todo переделать на апишку удаление тестовых данных (офиса)
        settingOfficePage.delOffice(nameOffice);
    }

    @DisplayName("Редактирование офиса")
    @Test
    void editOffice() {

        String comment = "Офис создан в рамках автотестов ui";

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
        settingOfficePage.setWorkplaces(RandomUtils.generateRandomInteger(4));
        // нажатие кнопки "Добавить офис"
        settingOfficePage.clickButtonAddEditOffice();
        // повторное открытие офиса для редактирования полей
        settingOfficePage.openOffice(nameOffice);
        // ввод в инпут "Название" новое имя офиса
        settingOfficePage.setNameOffice(nameOffice + " edit");
        // изменение тайм зоны
        settingOfficePage.setTimeZone("+03:00 (Europe/Moscow)");
        // снимаем чекбокс выходной с понедельника
        settingOfficePage.checkboxWeekend(settingOfficePage.getMonday());
        // ставим чекбокс выходной на среду
        settingOfficePage.checkboxWeekend(settingOfficePage.getWednesday());
        // выставляем время начала работы офиса во вторник с 10:00
        settingOfficePage.setStartWork(settingOfficePage.getTuesday(), "1000");
        // выставляем время окончания работы офиса во вторник с 21:45
        settingOfficePage.setEndWork(settingOfficePage.getTuesday(), "2145");
        // ставим чекбокс выходной в субботу
        settingOfficePage.checkboxWeekend(settingOfficePage.getSaturday());
        // снимаем чекбокс выходной в воскресенье
        settingOfficePage.checkboxWeekend(settingOfficePage.getSunday());
        // выставляем время начала работы офиса в воскресенье с 08:00
        settingOfficePage.setStartWork(settingOfficePage.getTuesday(), "0800");
        // изменение в инпуте "Комментарий" комментария
        settingOfficePage.setComment(RandomUtils.generateRandomString(10));
        // изменение в инпуте "Рабочих мест" колличество рабочих мест
        settingOfficePage.setWorkplaces(RandomUtils.generateRandomInteger(4));
        // нажимаем кнопку "Сохранить изменения"
        settingOfficePage.clickButtonAddEditOffice();
        // проверка уведомления что офис изменен
        settingOfficePage.shouldHaveNotification("Операция успешно завершена.");
        //Проверка что в таблице появился офис с названием
        settingOfficePage.shouldHaveOffice(nameOffice + " edit");
        // удаление офиса todo перенести удаление тестовых данных на апи
        settingOfficePage.delOffice(nameOffice + " edit");

    }

    @DisplayName("Импорт офиса через файл Excel")
    @Test
    void importOffice() {
        // открытие страницы настроек офиса через вызов метода из класса SetOfficePage
        settingOfficePage.openPage();
        //нажатие кнопки "Импортировать" и загрузка файла импорта
        settingOfficePage.importOffice("src/test/resources/import/importOffices.xlsx");
        // проверка уведомления что офис импортирован
        settingOfficePage.shouldHaveNotification("Офисы импортированы");
        //Проверка что в таблице появился офис с названием
        settingOfficePage.shouldHaveOffice("TestImportInSelenide");
        //// удаление импортированного офиса todo перенести удаление тестовых данных на апи
        settingOfficePage.delOffice("TestImportInSelenide");
    }
}
