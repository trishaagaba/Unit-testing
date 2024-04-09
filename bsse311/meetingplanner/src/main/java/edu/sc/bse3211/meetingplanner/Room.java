package edu.sc.bse3211.meetingplanner;

public class Room {
	private String ID;
	private Calendar calendar;
	
	/**
	 * Default constructor
	 */
	public Room(){
		ID="";
		calendar=  new Calendar();
	}
	
	/**
	 * Constructor, initializes calendar and sets name.
	 */
	public Room(String ID){
		this.ID=ID;
		calendar=  new Calendar();
	}

	public String getID() {
		return ID;
	}
	
	/**
	 * Add a meeting to a calendar.
	 * @see Calendar#addMeeting(Meeting)
	 */
	public void addMeeting(Meeting meeting) throws TimeConflictException{
		try{
			calendar.addMeeting(meeting);
		}catch(TimeConflictException e){
			throw new TimeConflictException("Conflict for room "+ID+":\n"+e.getMessage());
		}
	}
	
	/**
	 * Prints the agenda for a month.
	 * @see Calendar#printAgenda(int)
	 */
	public String printAgenda(int month){
		return calendar.printAgenda(month);
	}
	
	/**
	 * Prints the agenda for a month.
	 * @see Calendar#printAgenda(int, int)
	 */
	public String printAgenda(int month, int day){
		return calendar.printAgenda(month, day);
	}
	
	/**
	 * Checks whether a meeting is scheduled during a timeframe.
	 * @see Calendar#isBusy(int, int, int, int)
	 */
	public boolean isBusy(int month, int day, int start, int end) throws TimeConflictException{
		return calendar.isBusy(month,day,start,end);
	}
	
	/**
	 * Gets a particular meeting.
	 * @see Calendar#getMeeting(int, int, int)
	 */
	public Meeting getMeeting(int month, int day, int index){
		return calendar.getMeeting(month, day, index);
	}
	
	/**
	 * Removes a particular meeting.
	 * @see Calendar#removeMeeting(int, int, int)
	 */
    public void removeMeeting(int month, int day, int index){
    	calendar.removeMeeting(month,day,index);
	}
}


// String expected = "Meeting on 3/31 from 10 to 12: Meeting1";
// assertEquals("expected",meeting1.toString());

// String expected2 = "Meeting on 3/31 from 14 to 16: Meeting2";
// assertEquals("expected2",meeting2.toString());


// // String agenda = room.printAgenda(3);
// //assertTrue(agenda.contains("Meeting1"));
// //assertTrue(agenda.contains("Meeting2"));
// // public void testToString() 
// //     Meeting meeting = new Meeting(5, 20, 10, 12, "Test Meeting");
// //     String expected = "Meeting on 5/20 from 10 to 12: Test Meeting";
// //     assertEquals(expected, meeting.toString());
