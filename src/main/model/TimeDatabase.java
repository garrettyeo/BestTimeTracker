package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

// Represents a database of all times with different functionality such as filtering, add, and removing from database
public class TimeDatabase {
    private List<Time> timeList;

    // EFFECTS: constructs an empty list of Times
    public TimeDatabase() {
        timeList = new ArrayList<>();
    }

    // EFFECTS: returns Time at given index of timeList
    public Time getTime(int index) {
        return timeList.get(index);
    }

    // MODIFIES: this
    // EFFECTS: adds a Time to the list
    public void addTime(Time t) {
        changeBestTime(t);
        timeList.add(t); // adding before messed up the loop
    }

    // MODIFIES: this
    // EFFECTS: if event under swimmer does not exist, then set to best time. If exist, then compare
    // and change isBestTime flags as required
    public void changeBestTime(Time t) {
        //check for existence
        if (containsSwimmerEvent(t)) { //exists!
            for (Time times : timeList) {
                if (times.eventTimeInSeconds(times.getEventTime()) > t.eventTimeInSeconds(t.getEventTime())) {
                    t.setNewBestTime();
                    times.setNoBestTime();
                }
            }
        } else {        // no existence
            t.setNewBestTime();
        }
    }

    // MODIFIES: this
    // EFFECTS: returns true if a swimmer has already swum a specified event before, false if otherwise
    public boolean containsSwimmerEvent(Time t) {
        for (Time times : timeList) {
            if (times.getName().equals(t.getName()) && times.getEvent().equals(t.getEvent())) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: filters a list of all the best times under a swimmer's name
    public List<Time> getSwimmerBestTime(String swimmerName) {
        List<Time> listOfBestTimes = new ArrayList<>();

        for (Time times : timeList) {
            if (times.getName().equals(swimmerName) && times.isBestTime()) {
                listOfBestTimes.add(times);
            }
        }
        return listOfBestTimes;
    }

    // MODIFIES: this
    // EFFECTS: filters a list of all times under a swimmer's name
    public List<Time> getAllSwimmerTimes(String swimmerName) {
        List<Time> listOfAllSwimmerTimes = new ArrayList<>();

        for (Time times : timeList) {
            if (times.getName().equals(swimmerName)) {
                listOfAllSwimmerTimes.add(times);
            }
        }
        return listOfAllSwimmerTimes;
    }

    // EFFECTS: returns length of a list
    public int numTimes() {
        return timeList.size();
    }

    // MODIFIES: this
    // EFFECTS: removes a time from the list of times
    public void removeTime(String name, String meetName, String eventName) {
        try {
            for (Time times : timeList) {
                if (times.getName().equals(name) && times.getMeetName().equals(meetName)
                        && times.getEvent().equals(eventName)) {
                    timeList.remove(times);
                }
            }
        } catch (ConcurrentModificationException e) {
            //Nothing happens
        }

    }

    // EFFECTS: returns an unmodifiable list of times in this time database
    public List<Time> getTimeList() {
        return Collections.unmodifiableList(timeList);
    }

    // MODIFIES: json
    // EFFECTS: writes to json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("times", timesToJson());
        return json;
    }

    // EFFECTS: returns things in this time as a JSON array
    private JSONArray timesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Time times : timeList) {
            jsonArray.put(times.toJson());
        }
        return jsonArray;
    }
}
