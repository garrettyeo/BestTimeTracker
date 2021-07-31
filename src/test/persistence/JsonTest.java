package persistence;

import model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTime(String name, String swimmerGroup, int age, String meetName, String eventName,
                             String eventTime, Time time) {
        assertEquals(name, time.getName());
        assertEquals(swimmerGroup, time.getSwimmerGroup());
        assertEquals(age, time.getAge());
        assertEquals(meetName, time.getMeetName());
        assertEquals(eventName, time.getEvent());
        assertEquals(eventTime, time.getEventTime());
    }
}
