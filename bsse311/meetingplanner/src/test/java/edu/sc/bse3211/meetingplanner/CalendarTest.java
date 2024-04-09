package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

public class CalendarTest {
	// Add test methods here. 
	// You are not required to write tests for all classes.
	
	@Test
	public void testAddMeeting_holiday() {
		// Create Janan Luwum holiday
		Calendar calendar = new Calendar();
		// Add to calendar object.
		try {
			Meeting janan = new Meeting(2, 16, "Janan Luwum");
			calendar.addMeeting(janan);
			// Verify that it was added.
			Boolean added = calendar.isBusy(2, 16, 0, 23);
			assertTrue("Janan Luwum Day should be marked as busy on the calendar",added);
		} catch(TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}


	// ----------------------Dealing with the Calendar constructor--------------------------------
	// Initialization of the calendar object
	@Test
    public void testCalendarInitializedCorrectly() {
		Calendar calendar = new Calendar();
		assertNotNull("Calendar object is not null", calendar);
	}

	// Dealing with the days that should not exist
	@Test
    public void testCalendarInitializedWithDaysThatDoNotExist() {
        Calendar calendar = new Calendar();

        // Check the descriptions of placeholder meetings for days that don't exist
        assertEquals("Day does not exist", calendar.getMeeting(2, 29, 0).getDescription());
        assertEquals("Day does not exist", calendar.getMeeting(2, 30, 0).getDescription());
        assertEquals("Day does not exist", calendar.getMeeting(2, 31, 0).getDescription());
        assertEquals("Day does not exist", calendar.getMeeting(4, 31, 0).getDescription());
        assertEquals("Day does not exist", calendar.getMeeting(6, 31, 0).getDescription());
        assertEquals("Day does not exist", calendar.getMeeting(9, 31, 0).getDescription());
        assertEquals("Day does not exist", calendar.getMeeting(11, 30, 0).getDescription());
        assertEquals("Day does not exist", calendar.getMeeting(11, 31, 0).getDescription());
    }



	//-------------------Dealing with the CheckTimes Function-----------------------
	//test for a valid meeting time
	@Test
    public void testValidMeetingTime() {
        // Test a valid meeting time
        try {
            Calendar.checkTimes(5, 15, 10, 12);
        } catch (TimeConflictException e) {
            fail("Valid meeting time should not throw an exception: " + e.getMessage());
        }
    }

	// Test for invalid month
	@Test
    public void testCheckTimesForInvalidMonth() {
        try {
            Calendar.checkTimes(13, 1, 8, 10);
            fail("Expected TimeConflictException was not thrown");
        } catch (TimeConflictException e) {
           
        }
    }

	//Test for invalid day (greater than 31)
	@Test
    public void testCheckTimesForInvalidDay() {
        try {
            Calendar.checkTimes(1, 32, 8, 10);
            fail("Expected TimeConflictException was not thrown for invalid day");
        } catch (TimeConflictException e) {
        }
    }

	// Test for invalid start time (negative)
	@Test
    public void testCheckTimesForInvalidStartTime() {
        try {
            Calendar.checkTimes(5, 15, -1, 10);
            fail("Expected TimeConflictException was not thrown for invalid start time");
        } catch (TimeConflictException e) {
        }
    }

	 // Test for invalid end time (greater than 23)
	@Test
    public void testCheckTimesForInvalidEndTime() {
        try {
            Calendar.checkTimes(5, 15, 10, 24);
            fail("Expected TimeConflictException was not thrown for invalid end time");
        } catch (TimeConflictException e) {
        }
    }

	// Test for end time before start time
	@Test(expected = TimeConflictException.class)
	public void testCheckTimesForEndTimeBeforeStartTime() throws TimeConflictException{
		Calendar.checkTimes(1, 1, 10, 8);
	}

	// Test for end time before start time
	@Test(expected = TimeConflictException.class)
	public void testCheckTimesForStartTimeEqualsEndTime() throws TimeConflictException{
		Calendar.checkTimes(1, 1, 8, 8);
	}



	//-------------------------------Dealing with the isBusy function---------------

	// Test for a completely free time slot
	@Test
    public void testIsBusyForFreeTimeSlot() throws TimeConflictException {
        Calendar calendar = new Calendar();
        assertFalse(calendar.isBusy(5, 15, 8, 10));
    }

	// Test for a time slot with a meeting that starts and ends within the specified time frame
    @Test
    public void testIsBusyForMeetingWithinTimeFrame() throws TimeConflictException {
        Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(5, 8, 9, 10);
		calendar.addMeeting(meeting);
        assertTrue(calendar.isBusy(5, 8,9,10));
    }

	 // Test for a time slot with a meeting that overlaps the specified time frame
	@Test
    public void testIsBusyForOverlappingMeeting() throws TimeConflictException {
        Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(5, 15, 8, 12);
		calendar.addMeeting(meeting);
        assertTrue(calendar.isBusy(5, 15, 10, 11));
    }

	//test for invalid Month
	@Test(expected = TimeConflictException.class)
    public void testIsBusyForInvalidMonth() throws TimeConflictException {
        Calendar calendar = new Calendar();
        calendar.isBusy(13, 15, 8, 10);
    }

	//test for invalid day
	@Test(expected = TimeConflictException.class)
    public void testIsBusyForInvalidday() throws TimeConflictException {
        Calendar calendar = new Calendar();
        calendar.isBusy(12, 50, 8, 10);
    }

	// Test for end time before start time
	@Test(expected = TimeConflictException.class)
	public void testIsBusyForEndTimeBeforeStartTime() throws TimeConflictException{
		Calendar.checkTimes(1, 1, 10, 8);
	}

	// Test for end time before start time
	@Test(expected = TimeConflictException.class)
	public void testIsBusyForStartTimeEqualsEndTime() throws TimeConflictException{
		Calendar.checkTimes(1, 1, 8, 8);
	}


	//------------------------Dealing with the Add Meeting Function

	// Test for adding a meeting during busy time
	@Test
	public void testAddMeetingDuringBusyTime() {
		Calendar calendar = new Calendar();
		try{
			Meeting initialMeeting = new Meeting(4, 1, "Easter");
			calendar.addMeeting(initialMeeting);
			Meeting overlappingMeeting = new Meeting(4, 1, "Easter Monday");
			calendar.addMeeting(overlappingMeeting);
			fail("Expected TimeConflictExpection, but no exception was thrown");
		} catch (TimeConflictException e){
			assertTrue("TimeConflictException thrown successfully", true);
		} catch (Exception e){
			fail("Unexpected exception thrown: " + e.getMessage());
		}
	}

	// Test for adding a meeting during free time
	@Test
    public void testAddMeetingDuringFreeTime() throws TimeConflictException {
        Calendar calendar = new Calendar();
        Meeting initialMeeting = new Meeting(5, 15, 8, 10);
        calendar.addMeeting(initialMeeting);
        Meeting newMeeting = new Meeting(5, 16, 9, 11);
        calendar.addMeeting(newMeeting);
        assertTrue(calendar.isBusy(5, 16, 9, 11));
    }

	
	//test for invalid Month
	@Test(expected = TimeConflictException.class)
    public void testAddMeetingForInvalidMonth() throws TimeConflictException {
        Calendar calendar = new Calendar();
        calendar.isBusy(13, 15, 8, 10);
    }

	//test for invalid day
	@Test(expected = TimeConflictException.class)
    public void testAddMeetingInvalidday() throws TimeConflictException {
        Calendar calendar = new Calendar();
        calendar.isBusy(12, 50, 8, 10);
	}

	// Test for end time before start time
	@Test(expected = TimeConflictException.class)
	public void testAddMeetingForEndTimeBeforeStartTime() throws TimeConflictException{
		Calendar.checkTimes(1, 1, 10, 8);
	}

	// Test for end time before start time
	@Test(expected = TimeConflictException.class)
	public void testAddMeetingForStartTimeEqualsEndTime() throws TimeConflictException{
		Calendar.checkTimes(1, 1, 8, 8);
	}



	//-------------------------Dealing with the clearing meetings in a day
	//Clearing a meeting that we have created
	@Test
    public void testClearSchedule() {
        Calendar calendar = new Calendar();
        Meeting meeting1 = new Meeting(5, 15, 8, 10);
        
        try {
            calendar.addMeeting(meeting1);
        } catch (TimeConflictException e) {
            fail("Unexpected TimeConflictException: " + e.getMessage());
        }
        
        // Clear the schedule for the given day
        calendar.clearSchedule(5, 15);
        
        // Verify that the schedule is cleared for the day
        try {
            assertFalse(calendar.isBusy(5, 15, 8, 10));
        } catch (TimeConflictException e) {
            fail("Unexpected TimeConflictException: " + e.getMessage());
        }
    }

	// Attempt to clear the schedule for an invalid month and day
	@Test(expected = IndexOutOfBoundsException.class)
	public void testClearScheduleForInvalidMonthAndDay(){
		Calendar calendar = new Calendar();
		calendar.clearSchedule(13, 32);
	}	


	//------------------------ Dealing with the print Agenda function

	@Test
	public void testPrintAgendaForValidMonth(){
		// Create a Calendar object
		Calendar calendar = new Calendar();

		try{
			// Create a Room object
			Room room = new Room("Test Room");

			// Create a list of attendees
			ArrayList<Person> attendees = new ArrayList<>();
			attendees.add(new Person("John Doe"));
			attendees.add(new Person("Jane Snow"));

			// Create Meeting objects using the existing constructor
			Meeting meeting1 = new Meeting(1, 1, 8, 10, attendees, room, "Test Meeting 1");
			calendar.addMeeting(meeting1);

			Meeting meeting2 = new Meeting(1, 2, 8, 10, attendees, room, "Test Meeting 2");
			calendar.addMeeting(meeting2);

		} catch (TimeConflictException e){
			fail("Unexpected TimeConflictException: " + e.getMessage());
		}

		String agenda = calendar.printAgenda(1);

		assertTrue("Agenda contains Test Meeting 1", agenda.contains("Test Meeting 1"));
		assertTrue("Agenda contains Test Meeting 2", agenda.contains("Test Meeting 2"));
	}

	//test print Agenda for free month for free month
	@Test
	public void testPrintAgendaForFreeMonth(){
		Calendar calendar = new Calendar();
		String agenda = calendar.printAgenda(3);

		assertEquals("Agenda is empty for invalid month", "Agenda for 3:\n", agenda);
	}




	//--------------- Dealing with the Add Meeting Funcion
	//test for occupied day and month
	@Test
	public void testGetMeetingForOccupiedMonthAndDay(){
		Calendar calendar = new Calendar();
		try{
			Meeting meeting1 = new Meeting(1, 1, "Meeting 1");
			calendar.addMeeting(meeting1);

		} catch(TimeConflictException e){
			fail("Unexpected TimeConflictException: " + e.getMessage());
		}

		// Retrieve the meeting for an occupied month and day
		Meeting retrivedMeeting = calendar.getMeeting(1, 1, 0);

		assertEquals("Retrieved meeting matches expected meeting", "Meeting 1", retrivedMeeting.getDescription());
	}

	//test for Free day and month
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetMeetingForFreeMonthAndDay(){
		Calendar calendar = new Calendar();
		calendar.getMeeting(1, 1, 0);
	}


	//-----------------------Dealing with the Remove Meeting Function
	//Removing an occupied Day and month
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveMeetingForOccupiedMonthAndDay(){
		Calendar calendar = new Calendar();
		try{
			Meeting meeting1 = new Meeting(1, 1, "Meeting 1");
			calendar.addMeeting(meeting1);

		} catch(TimeConflictException e){
			fail("Unexpected TimeConflictException: " + e.getMessage());
		}

		calendar.removeMeeting(1, 1, 0);

		assertNull("Meeting removed successfully", calendar.getMeeting(1, 1, 0).getDescription());
	}

	//Removing a free Day and month
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveMeetingForFreeMonthAndDay(){
		Calendar calendar = new Calendar();
		calendar.removeMeeting(1, 1, 0);
	}


	//Removing an invalid Day and month
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveMeetingForInvalidMonthAndValidDay(){
		Calendar calendar = new Calendar();
		calendar.removeMeeting(13, 1, 0);
	}

}
