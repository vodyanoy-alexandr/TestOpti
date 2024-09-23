package testUI.pages.autorization;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;


public class AuthPage {

    private final SelenideElement userNameInput = element("[name=username]");
    private final SelenideElement passwordInput = element("[name=password]");
    private final SelenideElement loginButton = element("[name=login]");
    private final SelenideElement logoutButton = element("[title=Выйти]");

    public SelenideElement getUserNameInput() {
        return userNameInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public SelenideElement getLogoutButton() {
        return logoutButton;
    }

    // Метод ввода логина и пароля
    public void loginInPageAuth(String login, String pass) {
        // Ввод в инпут username логина менеджера
        getUserNameInput().setValue(login);
        // Ввод в инпут password пароля менеджера
        getPasswordInput().setValue(pass);
        // Нажатие на кнопку "Вход"
        getLoginButton().click();
    }

    // Метод открывает страницу авторизации
    public void openPage() {
        open("/");
    }

    // Метод разлогиневает пользователя
    public void logoutUser() {
        getLogoutButton().click();
    }
}
