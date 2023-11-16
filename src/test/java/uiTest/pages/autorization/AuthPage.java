package uiTest.pages.autorization;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.element;


public class AuthPage {

    private final SelenideElement userNameInput = element("[name=username]");
    private final SelenideElement passwordInput = element("[name=password]");
    private final SelenideElement loginButton = element("[name=login]");

    public SelenideElement getUserNameInput() {
        return userNameInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    // метод ввода логина и пароля
    public void loginInPageAuth(String login, String pass) {
        //ввод в инпут username логина менеджера
        getUserNameInput().setValue(login);
        //ввод в инпут password пароля менеджера
        getPasswordInput().setValue(pass);
        //нажатие на иконку "Вход"
        getLoginButton().click();
    }
}
