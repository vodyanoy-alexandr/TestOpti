package dataTest;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseDataTest {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String LOGIN_MANAGER = properties.getProperty("LOGIN_MANAGER");
    private static final String PASS_MANAGER = properties.getProperty("PASS_MANAGER");
    private static final String LOGIN_OPERATOR = properties.getProperty("LOGIN_OPERATOR");
    private static final String PASS_OPERATOR = properties.getProperty("PASS_OPERATOR");
    private static final String URL_STAND = properties.getProperty("URL_STAND");

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
        return URL_STAND;
    }
}



