package pages.componets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.element;

public class Notifications {
    // метод проверяет появление уведомления с текстом прописанном в параметрах
    public void shouldHaveNotifications(String text) {
        element(".notifications")
                .shouldHave(text(text));
    }
}
