package com.example.kidsactivityreminder;

public class ActivityName {
    private String activityName;
    private String activityTime;
    private String activityDate;
    private String activityStatus;

    public ActivityName(String activityName, String activityTime, String activityDate, String activityStatus) {
        this.activityName = activityName;
        this.activityTime = activityTime;
        this.activityDate = activityDate;
        this.activityStatus = activityStatus;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }
}
