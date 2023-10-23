package uiTests.componets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TimeZoneComponents {
    // метод выбирает в выпадашке один из часовых поясов (пример передаваемого параметра "+05:00 (Asia/Yekaterinburg)")
    public void selectTimeZone (String timeZone) {
        element(".MuiPopover-paper").$$(".auto-complete__item")
                .filterBy(text(timeZone))
                .first()
                .click();
    }
}
