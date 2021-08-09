package persistence;

import model.Time;
import model.TimeDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads times from JSON data stored in file
// NOTE: much of this code is borrowed from the JsonReader class of the WorkRoomApp example repo of CPSC 210
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of times from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TimeDatabase readTimeDatabaseFile() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTimesDatabase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private TimeDatabase parseTimesDatabase(JSONObject jsonObject) {
        // TODO uncomment below for time database identification id (end)
        // String name = jsonObject.getString("name");
        TimeDatabase tdb = new TimeDatabase();                          // TODO give a param e.g. TimeDatabase(name)
        addTimes(tdb, jsonObject); //calls method below
        return tdb;
    }

    // MODIFIES: tdb
    // EFFECTS: parses times from JSON object and adds them to times database
    private void addTimes(TimeDatabase tdb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("times");
        for (Object json : jsonArray) {
            JSONObject nextTime = (JSONObject) json; // casted Object -> JSONObject
            addTime(tdb, nextTime);
        }
    }

    // MODIFIES: tdb
    // EFFECTS: parses time from JSON object and adds it to time database
    private void addTime(TimeDatabase tdb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String swimmerGroup = jsonObject.getString("sex");
        String age = jsonObject.getString("age");
        String meetName = jsonObject.getString("meet");
        String eventName = jsonObject.getString("event");
        String eventTime = jsonObject.getString("time");
        Time time = new Time(name, swimmerGroup, age, meetName, eventName, eventTime);
        tdb.addTime(time);                                                              //why am I re-adding to tdb??
                                                                // because when reading from a loaded file must create
                                                                // a new time database from which to read from
    }
}


