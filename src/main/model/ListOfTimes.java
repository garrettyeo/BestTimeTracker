package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfTimes {
    private List<Time> timeList;

    // EFFECTS: constructs an empty list of Times
    public ListOfTimes() {
        timeList = new ArrayList<>();
    }

    // EFFECTS: returns Time at given index of timeList
    public Time getTime(int index) {
        return timeList.get(index);
    }

    // MODIFIES: this and Time
    // EFFECTS: adds a Time to the list
    public void addTime(Time t) {
        changeBestTime(t);
        timeList.add(t); // adding before messed up the loop
    }

    // if event under swimmer does not exist, then set to best time. If exist, then compare
    // and change flags as required
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

    // EFFECTS: returns true if a swimmer has already swum a specified event before, false if otherwise
    public boolean containsSwimmerEvent(Time t) {
        for (Time times : timeList) {
            if (times.getName().equals(t.getName()) && times.getEvent().equals(t.getEvent())) {
                return true;
            }
        }
        return false;
    }

    //get list of all best times for specific swimmer
    public ListOfTimes getSwimmerBestTime(String swimmerName) {
        ListOfTimes listOfBestTimes = new ListOfTimes();

        for (Time times : timeList) {
            if (times.getName().equals(swimmerName) && times.isBestTime()) {
                listOfBestTimes.addTime(times); //double flags itself????
            }
        }

        return listOfBestTimes;
    }

    //get All of the times for a specified swimmer
    public ListOfTimes getAllSwimmerTimes(String swimmerName) {
        ListOfTimes listOfAllSwimmerTimes = new ListOfTimes();

        for (Time times : timeList) {
            if (times.getName().equals(swimmerName)) {
                listOfAllSwimmerTimes.addTime(times);
            }
        }

        return listOfAllSwimmerTimes;
    }

    // remove a specified time ... return true if successful, false if otherwise
    public boolean removeTime(Time t) {
        if (containsTime(t)) {
            timeList.remove(t);
            return true;
        } else {
            return false;
        }
    }

    public boolean containsTime(Time t) {
        return timeList.contains(t);
    }
}
