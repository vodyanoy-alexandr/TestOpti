package apiTest.model.settings.office;

public class SettingOfficesBodyModel {
    //
    private WorkTimeOfficesBodyModel[] workTime;
    private String name;
    private String timezone;
    private String comment;
    private int maxSeats;

    public WorkTimeOfficesBodyModel[] getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTimeOfficesBodyModel[] workTime) {
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

