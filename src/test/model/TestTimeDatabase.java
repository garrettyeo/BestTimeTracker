package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTimeDatabase {
    private TimeDatabase testListOfTimes;
    private Time t1 = new Time("Garrett Yeo", "M", 21, "Lower Mainland Invitational",
            "100 Breaststroke", "1:05:25");
    private Time t2 = new Time("Daiya Seto", "M", 25, "World Championships",
            "400 IM", "4:06:23");
    private Time t3 = new Time("Daiya Seto", "M", 26, "Tokyo Olympics",
            "400 IM", "4:10:23");
    private Time t4 = new Time("Daiya Seto", "M", 26, "FINA A",
            "400 IM", "4:10:53");
    private Time t5 = new Time("Katie Ledecky", "F", 26, "Tokyo Olympics",
            "400 Freestyle", "4:00:00");
    private Time t6 = new Time("Daiya Seto", "M", 26, "Tokyo Olympics",
            "200 Butterfly", "1:52:00");
    private Time t7 = new Time("Daiya Seto", "M", 25, "Tokyo Olympics",
            "200 IM", "1:59:14");


    @BeforeEach
    public void setup() {
        testListOfTimes = new TimeDatabase();
        testListOfTimes.addTime(t1);
        testListOfTimes.addTime(t2);
        testListOfTimes.addTime(t3);
    }

    @Test
    public void testGetTime() {
        assertEquals(t3, testListOfTimes.getTime(2));
    }

    @Test
    public void testAddTime() {
        testListOfTimes.addTime(t5);
        assertEquals(t5, testListOfTimes.getTime(3));
    }

    @Test
    public void testChangeBestTime() {
        testListOfTimes.changeBestTime(t1);
        assertTrue(t1.isBestTime());
        testListOfTimes.changeBestTime(t2);
        assertTrue(t2.isBestTime());
        testListOfTimes.changeBestTime(t3);
        assertFalse(t3.isBestTime());
    }

    @Test
    public void testContainsSwimmerEvent() {
        assertTrue(testListOfTimes.containsSwimmerEvent(t2));
        assertTrue(testListOfTimes.containsSwimmerEvent(t3));
        assertFalse(testListOfTimes.containsSwimmerEvent(t5));
    }

    @Test
    public void testGetSwimmerBestTime() {
        testListOfTimes.addTime(t4);
        testListOfTimes.addTime(t5);
        testListOfTimes.addTime(t6);

        List<Time> testList1 = testListOfTimes.getSwimmerBestTime("Daiya Seto");
        assertEquals(t2, testList1.get(0));
        assertEquals(t6, testList1.get(1));

        List<Time> testList2 = testListOfTimes.getSwimmerBestTime("Garrett Yeo");
        assertEquals(t1, testList2.get(0));
    }

    @Test
    public void testListOfTimesSize() {
        assertEquals(3, testListOfTimes.numTimes());
    }

    @Test
    public void testGetAllSwimmerTimes() {
        testListOfTimes.addTime(t4);
        testListOfTimes.addTime(t5);
        testListOfTimes.addTime(t6);

        List<Time> testList1 = testListOfTimes.getAllSwimmerTimes("Daiya Seto");
        assertEquals(t2, testList1.get(0));
        assertEquals(t3, testList1.get(1));
        assertEquals(t6, testList1.get(3));

        List<Time> testList2 = testListOfTimes.getAllSwimmerTimes("Katie Ledecky");
        assertEquals(t5, testList2.get(0));
    }

    @Test
    public void testRemoveTime() {
        assertEquals(3, testListOfTimes.numTimes());
        testListOfTimes.removeTime("Daiya Seto", "World Championships", "400 IM");
        assertEquals(t3, testListOfTimes.getTime(1));
        assertEquals(2, testListOfTimes.numTimes());
    }
}
