package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestTime {
    private Time testTime;

    @BeforeEach
    public void setup() {
        testTime = new Time("Garrett", "M", "21", "Lower Mainland Invitational",
                "100 Breaststroke", "1:05:25");
    }

    @Test
    public void testGetName() {
        assertEquals("Garrett", testTime.getName());
    }

    @Test
    public void testGetSwimmerGroup() {
        assertEquals("M", testTime.getSwimmerGroup());
    }

    @Test
    public void testGetAge() {
        assertEquals("21", testTime.getAge());
    }

    @Test
    public void testGetMeetName() {
        assertEquals("Lower Mainland Invitational", testTime.getMeetName());
    }

    @Test
    public void testGetEvent() {
        assertEquals("100 Breaststroke", testTime.getEvent());
    }

    @Test
    public void testGetEventTime() {
        assertEquals("1:05:25", testTime.getEventTime());
    }

    @Test
    public void testEventTimeInSeconds() {
        assertEquals(675, testTime.eventTimeInSeconds("1:05:25"));
    }

    @Test
    public void testIsBestTime() {
        assertFalse(testTime.isBestTime());
    }

    @Test
    public void testSetNewBestTime() {
        testTime.setNewBestTime();
        assertTrue(testTime.isBestTime());
    }

    @Test
    public void testSetNoBestTime() {
        testTime.setNoBestTime();
        assertFalse(testTime.isBestTime());
    }
}