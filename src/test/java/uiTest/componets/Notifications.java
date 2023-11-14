package uiTest.componets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.element;

public class Notifications {
    // метод проверяет появление уведомления с текстом прописанном в параметрах
    public void shouldHaveNotification(String text) {
        element(".notifications")
                .shouldHave(text(text));
    }
}
