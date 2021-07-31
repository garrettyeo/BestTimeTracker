package persistence;

import model.Time;
import model.TimeDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// NOTE: much of this code for these tests are borrowed from the JsonReaderTest class of the WorkRoomApp example repo
// of CPSC 210
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TimeDatabase tdb = reader.readTimeDatabaseFile();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDatabase");
        try {
            TimeDatabase tdb = reader.readTimeDatabaseFile();
            //assertEquals("My work room", wr.getName());
            assertEquals(0, tdb.numTimes());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDatabase");
        try {
            TimeDatabase tdb = reader.readTimeDatabaseFile();
            List<Time> timeList = tdb.getTimeList();                  // create a list of times
            assertEquals(2, timeList.size());
            checkTime("Garrett", "M", 21, "Tokyo Olympics", "200 butterfly", "2:00:00", timeList.get(0));
            checkTime("Caeleb Dressel", "M", 24, "Tokyo Olympics", "200 butterfly", "1:50:00", timeList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
