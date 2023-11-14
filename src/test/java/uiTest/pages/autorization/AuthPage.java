package uiTest.pages.autorization;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.element;


public class AuthPage {
    private static final String LOGIN_MANAGER = "manager";
    private static final String PASS_MANAGER = "manager";
    private static final String LOGIN_OPERATOR = "operator";
    private static final String PASS_OPERATOR = "operator";

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

    public String getLoginManager() {
        return LOGIN_MANAGER;
    }

    public String getPassManager() {
        return PASS_MANAGER;
    }

    public String getLoginOperator() {
        return LOGIN_OPERATOR;
    }

    public String getPassOperator() {
        return PASS_OPERATOR;
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
