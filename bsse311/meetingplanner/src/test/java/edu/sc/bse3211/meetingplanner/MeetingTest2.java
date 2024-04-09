// FILEPATH: /D:/Software Engineering/YEAR3/SEM 2/Software Testing and Verification/Practical/meetingplanner/src/test/java/edu/sc/bse3211/meetingplanner/MeetingTest.java
package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class MeetingTest {
    @Test
    public void testDefaultConstructor() {
        Meeting meeting = new Meeting();
        assertNotNull(meeting);
    }

    @Test
    public void testConstructorWithMonthAndDay() {
        Meeting meeting = new Meeting(5, 20);
        assertEquals(5, meeting.getMonth());
        assertEquals(20, meeting.getDay());
    }

    @Test
    public void testConstructorWithMonthDayAndDescription() {
        Meeting meeting = new Meeting(5, 20, "Test Meeting");
        assertEquals(5, meeting.getMonth());
        assertEquals(20, meeting.getDay());
        assertEquals("Test Meeting", meeting.getDescription());
    }

    @Test
    public void testConstructorWithMonthDayStartAndEnd() {
        Meeting meeting = new Meeting(5, 20, 10, 12);
        assertEquals(5, meeting.getMonth());
        assertEquals(20, meeting.getDay());
        assertEquals(10, meeting.getStartTime());
        assertEquals(12, meeting.getEndTime());
    }

    @Test
    public void testConstructorWithAllParameters() {
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(new Person("John Doe"));
        Room room = new Room("101");
        Meeting meeting = new Meeting(5, 20, 10, 12, attendees, room, "Test Meeting");
        assertEquals(5, meeting.getMonth());
        assertEquals(20, meeting.getDay());
        assertEquals(10, meeting.getStartTime());
        assertEquals(12, meeting.getEndTime());
        assertEquals(attendees, meeting.getAttendees());
        assertEquals(room, meeting.getRoom());
        assertEquals("Test Meeting", meeting.getDescription());
    }

    @Test
    public void testAddAttendee() {
        Meeting meeting = new Meeting();
        Person person = new Person("John Doe");
        meeting.addAttendee(person);
        assertTrue(meeting.getAttendees().contains(person));
    }

    @Test
    public void testRemoveAttendee() {
        Meeting meeting = new Meeting();
        Person person = new Person("John Doe");
        meeting.addAttendee(person);
        meeting.removeAttendee(person);
        assertFalse(meeting.getAttendees().contains(person));
    }

    @Test
    public void testToString() {
        Meeting meeting = new Meeting(5, 20, 10, 12, "Test Meeting");
        String expected = "Meeting on 5/20 from 10 to 12: Test Meeting";
        assertEquals(expected, meeting.toString());
    }
}