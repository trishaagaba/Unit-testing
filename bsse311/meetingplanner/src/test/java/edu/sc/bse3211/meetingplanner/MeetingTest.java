package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.*;



public class RoomTest {

    private Room room;

    @Before
    public void setUp() {
        room = new Room("Room1");
    }

    @Test
    public void testRoomDefaultConstructor(){
        Room room1 = new Room();
        assertNotNull(room1.getID()); 
        //room class has a default constructor tht initializes a new instance of the class w unique id
        assertNotNull(room1.getClass());
         //getClass method returns the class object of the current instance
         //code tests the default constructor of the Room class by creating a new intance of the class

    }
    @Test
    //@DisplayName("Test adding a meeting")
    public void testAddMeeting() throws TimeConflictException {
        Meeting meeting = new Meeting( 3, 31, 10, 12,null, room,"Meeting1");
        room.addMeeting(meeting);
        assertTrue(room.isBusy(3, 31, 10, 12));
    }


    

    @Test
    //@DisplayName("Test checking if room is busy")
    public void testIsBusy() throws TimeConflictException {
        Meeting meeting = new Meeting( 3, 31, 10, 12,null, room, "Meeting1");
        room.addMeeting(meeting);
        assertTrue(room.isBusy(3, 31, 9, 11));
        assertFalse(room.isBusy(3, 31, 14, 16));
    }

    @Test
    //@DisplayName("Test retrieving a meeting")
    public void testGetMeeting() throws TimeConflictException {
        Meeting meeting = new Meeting( 3, 31, 10, 12,null, room, "Meeting1");
        room.addMeeting(meeting);
        assertEquals(meeting, room.getMeeting(3, 31, 0));
    }

    @Test
    //@DisplayName("Test removing a meeting")
    public void testRemoveMeeting() throws TimeConflictException {
        Meeting meeting = new Meeting( 3, 31, 10, 12,null, room,"Meeting1");
        room.addMeeting(meeting);
        assertTrue(room.isBusy(3, 31, 10, 12));
        room.removeMeeting(3, 31, 0);
        assertFalse(room.isBusy(3, 31, 10, 12));
    }
}

