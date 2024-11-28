package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Event class
 */
public class TestEvent {
    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Sensor open at door");
        date = Calendar.getInstance().getTime();
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Sensor open at door", event.toString());
    }

     @Test
    void testEquals_SameObject() {
        Event event = new Event("Description");
        assertTrue(event.equals(event));
    }

    @Test
    void testEquals_NullObject() {
        Event event = new Event("Description");
        assertFalse(event.equals(null));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEquals_DifferentClass() {
        Event event = new Event("Description");
        String other = "Not an Event";
        assertFalse(event.equals(other));
    }

    @Test
    void testEquals_DifferentDescription() {
        Event event1 = new Event("Description1");
        Event event2 = new Event("Description2");
        assertFalse(event1.equals(event2));
    }

    @Test
    void testEquals_SameValues() {
        Event event1 = new Event("Description");
        Event event2 = new Event("Description");
        assertTrue(event1.equals(event2));
    }

    @Test
    void testHashCode_SameValues() {
        Event event1 = new Event("Description");
        Event event2 = new Event("Description");
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    void testHashCode_DifferentDescription() {
        Event event1 = new Event("Description1");
        Event event2 = new Event( "Description2");
        assertNotEquals(event1.hashCode(), event2.hashCode());
    }
}
