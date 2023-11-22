package apiTest.model.settings.office;

public class SettingOfficesBodyModel {
    //    {
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
    private String name;
    private String timezone;
    private String comment;
    private int maxSeats;
    private WorkTime[] workTime;

    // Геттеры и сеттеры для полей

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

    public WorkTime[] getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime[] workTime) {
        this.workTime = workTime;
    }

    // Вложенный класс WorkTime для представления данных о рабочем времени
    public static class WorkTime {
        private int dayOfWeek;
        private String from;
        private String to;
        private boolean weekend;

        // Геттеры и сеттеры для полей

        public int getDayOfWeek() {
            return dayOfWeek;
        }

        public void setDayOfWeek(int dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public boolean isWeekend() {
            return weekend;
        }

        public void setWeekend(boolean weekend) {
            this.weekend = weekend;
        }
    }
}