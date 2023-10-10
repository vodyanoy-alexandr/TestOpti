package datatest;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.element;

public class AuthorizationPageDataTest {
    private String
            urlStand = "https://?.dc.oswfm.ru",
            loginManager = "manager",
            passManager = "manager",
            loginOperator = "operator",
            passOperator = "operator";

    private SelenideElement
            userNameInput = element("[name=username]"),
            passwordInput = element("[name=password]"),
            loginButton = element("[name=login]");

    public SelenideElement getUserNameInput() {
        return userNameInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public String getUrlStand() {
        return urlStand;
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


}
