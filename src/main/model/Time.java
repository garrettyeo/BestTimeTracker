package model;

import org.json.JSONObject;

// Represents a swimmer's time that contains details like the swimmer's name, swimmer group, age group, meet swum,
// event name, and time for that event
public class Time implements persistence.Writable {
    private String name;
    private String swimmerGroup;          // the swimmer's sex (M or F)
    private String age;                    // age group
    private String meetName;            // name of swim meet where event was swum
    private String event;               // Butterfly, Backstroke, Breaststroke, Freestyle
    private String eventTime;              // mm:ss:ms
    private int timeInSeconds;
    private boolean isBestTime;

    // MODIFIES: THIS
    // EFFECTS: constructs a Time initialized with the following parameters
    public Time(String name, String swimmerGroup, String age, String meetName, String event, String eventTime) {
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
    public String getSwimmerGroup() {
        return swimmerGroup;
    }

    // EFFECTS: returns age
    public String getAge() {
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
    // EFFECTS: converts event time field from mm:ss:ms format to a time in deciseconds
    public int eventTimeInSeconds(String eventTime) throws NumberFormatException {
        if (!eventTime.matches("\\d+:\\d\\d:\\d\\d")) {
            throw new NumberFormatException();
        } else {
            String[] minSec = eventTime.split(":");
            int mins = Integer.parseInt(minSec[0]);
            int secs = Integer.parseInt(minSec[1]);
            int milliSecs = Integer.parseInt(minSec[2]);
            return (mins * 600) + (secs * 10) + milliSecs;
        }
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("sex", swimmerGroup);
        json.put("age", age);
        json.put("meet", meetName);
        json.put("event", event);
        json.put("time", eventTime);
        return json;
    }
}
