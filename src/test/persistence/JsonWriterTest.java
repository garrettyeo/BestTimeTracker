package persistence;

import model.Time;
import model.TimeDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// NOTE: much of this code is borrowed from the JsonWriterTest class of the WorkRoomApp example repo of CPSC 210
public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            TimeDatabase tdb = new TimeDatabase();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.openTimeDatabaseFile();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDatabase() {
        try {
            TimeDatabase tdb = new TimeDatabase();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.openTimeDatabaseFile();
            writer.write(tdb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            tdb = reader.readTimeDatabaseFile();
            assertEquals(0, tdb.numTimes());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDatabase() {
        try {
            TimeDatabase tdb = new TimeDatabase();
            tdb.addTime(new Time("Garrett", "M", 21, "Tokyo Olympics", "200 Butterfly", "2:00:00"));
            tdb.addTime(new Time("Caeleb Dressel", "M", 24, "Tokyo Olympics", "200 butterfly", "1:50:00"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.openTimeDatabaseFile();
            writer.write(tdb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            tdb = reader.readTimeDatabaseFile();
            List<Time> timeList = tdb.getTimeList();
            assertEquals(2, timeList.size());
            checkTime("Garrett", "M", 21, "Tokyo Olympics", "200 Butterfly", "2:00:00", timeList.get(0));
            checkTime("Caeleb Dressel", "M", 24, "Tokyo Olympics", "200 butterfly", "1:50:00", timeList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}