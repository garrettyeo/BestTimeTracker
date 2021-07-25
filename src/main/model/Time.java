package model;

// Represents a swimmer's time having the swimmer's name, swimmer group, age group, swim meet swum, event name, and
// time for that event
public class Time {
    private String name;
    private char swimmerGroup;          // the swimmer's sex (M or F)
    private int age;                    // age group
    private String meetName;            // name of swim meet where event was swum
    private String event;               // Butterfly, Backstroke, Breaststroke, Freestyle
    private String eventTime;              // mm:ss:ms
    private int timeInSeconds;
    private boolean isBestTime;

    // MODIFIES: THIS
    // EFFECTS: constructs a Time initialized with the following parameters
    public Time(String name, char swimmerGroup, int age, String meetName, String event, String eventTime) {
        this.name = name;
        this.swimmerGroup = swimmerGroup;
        this.age = age;
        this.meetName = meetName;
        this.event = event;
        this.eventTime = eventTime;
        timeInSeconds = eventTimeInSeconds(eventTime);
        isBestTime = false;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns the swimmer group
    public char getSwimmerGroup() {
        return swimmerGroup;
    }

    // EFFECTS: returns age
    public int getAge() {
        return age;
    }

    // EFFECTS: returns swim meet name
    public String getMeetName() {
        return meetName;
    }

    // EFFECTS: returns event name
    public String getEvent() {
        return event;
    }

    // EFFECTS: returns event time
    public String getEventTime() {
        return eventTime;
    }

    // REQUIRES: a time
    // MODIFIES: this
    // EFFECTS: converts event time field from mm:ss:ms format to a time in tenths of seconds
    public int eventTimeInSeconds(String eventTime) {
        String[] minSec = eventTime.split(":");
        int mins = Integer.parseInt(minSec[0]);
        int secs = Integer.parseInt(minSec[1]);
        int milliSecs = Integer.parseInt(minSec[2]);
        return (mins * 600) + (secs * 10) + milliSecs;
    }

    // EFFECTS: returns true if it is the best time, false otherwise
    public boolean isBestTime() {
        return isBestTime;
    }

    public void setNewBestTime() {
        isBestTime = true;
    }

    public void setNoBestTime() {
        isBestTime = false;
    }
}
