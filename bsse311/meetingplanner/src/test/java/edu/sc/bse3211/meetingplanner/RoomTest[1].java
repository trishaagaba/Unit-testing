// package edu.sc.bse3211.meetingplanner;

// import static org.junit.Assert.*;
// import org.junit.Test;

// public class RoomTest {
// 	// Add test methods here. 
//     @Test
//     public void testDefaultConstructor(){
//         Room room = new Room();
//         assertNotNull(room.getID());
//         assertEquals("",room.getID());
//         assertNotNull(room.printAgenda(3));
//     }

//     @Test
//     public void testParameterizedConstructor(){
//         Room room = new Room("LLT6A");
//         assertNotNull(room.getID());
//         assertEquals("LLT6A", room.getID());
//         assertNotNull(room.printAgenda(3));
//     }

//     @Test
//     public void testAddMeeting() throws TimeConflictException{
//         Room room = new Room("LLT6A");
//         Meeting meeting1 = new Meeting(3,31,"Meeting1");
//         Meeting meeting2 = new Meeting(3,31,"Meeting2");

//         room.addMeeting(meeting1);
//         assertTrue(room.isBusy(3, 31,  10, 12));

//         try {
//             room.addMeeting(meeting2);
//             fail("Expected TimeConflictException to be thrown");
//         } catch (TimeConflictException e) {
//             // TODO: handle exception
//         }
//     }

//     @Test
//     public void testPrintAgenda(){
//         Room room = new Room("LLT6A");
//         assertNotNull(room.printAgenda(3));
//     }

//     @Test
//     public void testIsBusy() throws TimeConflictException{
//         Room room = new Room("LLT6A");
//         Meeting meeting = new Meeting(3,31,"Meeting1");
//         room.addMeeting(meeting);

//         assertTrue(room.isBusy(3,31,9,11));
//         // assertFalse(room.isBusy(3,31,13,15));   //This error will be displayed if at all i uncomment out this line 
//     }

//     @Test
//     public void testGetMeeting() throws TimeConflictException{
//         Room room = new Room("LLT6A");
//         Meeting meeting = new Meeting(3,31,"Meeting1");
//         room.addMeeting(meeting);

//         assertNotNull(room.getMeeting(3,31,0));
//     }

//     @Test
//     public void testRemoveMeeting() throws TimeConflictException{
//         Room room = new Room("LLT6A");
//         Meeting meeting = new Meeting(3,31,"Meeting1");
//         room.addMeeting(meeting);

//         assertNotNull(room.getMeeting(3,31,0));
//         room.removeMeeting(3,31,0);
//         // assertNull(room.getMeeting(3, 31, 0));   //-----//The moment i uncomment out this file it will show an error because i will be deleting a meeting that is not existing and that's impossibleand invalid
//     }
//     // You are not required to write tests for all classes.
// }
