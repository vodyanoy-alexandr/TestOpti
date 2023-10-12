package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.element;

public class AuthPage {
    private String

            loginManager = "manager", // логин менеджера
            passManager = "manager", // пароль менеджера
            loginOperator = "operator", // логин оператора
            passOperator = "operator"; // пароль оператора

    private SelenideElement
            userNameInput = element("[name=username]"), // инпут логина
            passwordInput = element("[name=password]"), // инпут пароля
            loginButton = element("[name=login]"); // кнопка входа

    public SelenideElement getUserNameInput() {
        return userNameInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public String getLoginManager() {
        return loginManager;
    }

    public String getPassManager() {
        return passManager;
    }

    public String getLoginOperator() {
        return loginOperator;
    }

    public String getPassOperator() {
        return passOperator;
    }

    // метод ввода логина и пароля менеджера для авторизации
    public void loginManagerInPageAuth(String login, String pass) {
        //ввод в инпут username логина менеджера
        getUserNameInput().setValue(login);
        //ввод в инпут password пароля менеджера
        getPasswordInput().setValue(pass);
        //нажатие на иконку "Вход"
        getLoginButton().click();
    }

    // метод ввода логина и пароля оператора для авторизации
    public void loginOperatorInPageAuth(String login, String pass) {
        //ввод в инпут username логина менеджера
        getUserNameInput().setValue(login);
        //ввод в инпут password пароля менеджера
        getPasswordInput().setValue(pass);
        //нажатие на иконку "Вход"
        getLoginButton().click();
    }
}
