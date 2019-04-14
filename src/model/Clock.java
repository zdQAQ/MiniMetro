package model;

public class Clock {
    // private enum weekDay {LUN, MAR, MER, JEU, VEN, SAM, DIM};
    private static final String[] weekDay = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天" };

    int currentDay;
    int time;

    public Clock() {
        time = 0;
        currentDay = 0;
    }

    public void incrementeTime() {
        time++;
        if (time >= 24) {
            time = 0;
            incrementeDay();
        }
    }

    public void incrementeDay() {
        currentDay++;
        if (currentDay >= 7)
            currentDay = 0;
    }

    public String getDay() {
        return weekDay[currentDay];
    }

    public int getTime() {
        return time;
    }

}
