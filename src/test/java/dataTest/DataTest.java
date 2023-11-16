package dataTest;

// класс с базовыми данными
public class DataTest {

    private static final String LOGIN_MANAGER = "manager";
    private static final String PASS_MANAGER = "manager";
    private static final String LOGIN_OPERATOR = "operator";
    private static final String PASS_OPERATOR = "operator";
    private static final String URL_STAND = "https://.dc.oswfm.ru";
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

    public String getUrlStand() {
        // базовый url
        return URL_STAND;
    }
}


