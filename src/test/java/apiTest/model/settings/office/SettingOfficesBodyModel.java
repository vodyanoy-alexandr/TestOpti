package apiTest.model.settings.office;

public class SettingOfficesBodyModel {
    //{
//    "workTime": [
//        {
//            "dayOfWeek": 1,
//            "from": "08:00",
//            "to": "20:00",
//            "weekend": false
//        },
//        {
//            "dayOfWeek": 2,
//            "from": "08:00",
//            "to": "20:00",
//            "weekend": false
//        },
//        {
//            "dayOfWeek": 3,
//            "from": "08:00",
//            "to": "20:00",
//            "weekend": false
//        },
//        {
//            "dayOfWeek": 4,
//            "from": "08:00",
//            "to": "20:00",
//            "weekend": false
//        },
//        {
//            "dayOfWeek": 5,
//            "from": "08:00",
//            "to": "20:00",
//            "weekend": false
//        },
//        {
//            "dayOfWeek": 6,
//            "from": "08:00",
//            "to": "20:00",
//            "weekend": true
//        },
//        {
//            "dayOfWeek": 7,
//            "from": "08:00",
//            "to": "20:00",
//            "weekend": true
//        }
//    ],
//    "name": "new office",
//    "timezone": "Europe/Moscow",
//    "comment": "comments",
//    "maxSeats": 777
//}
    private WorkTimeOffices[] workTime;
    private String name;
    private String timezone;
    private String comment;
    private int maxSeats;

    public WorkTimeOffices[] getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTimeOffices[] workTime) {
        this.workTime = workTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }
}

