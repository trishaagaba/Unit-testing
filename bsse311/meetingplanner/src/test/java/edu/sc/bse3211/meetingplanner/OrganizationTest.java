package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrganizationTest {
	// Add test methods here. 
    @Test
    public void testDefaultConstructor(){
        Organization organization = new Organization();
        assertNotNull(organization.getEmployees());
        assertNotNull(organization.getRooms());
        assertEquals(5, organization.getEmployees().size());
        assertEquals(5, organization.getRooms().size());
        
    }

    @Test
    public void testGetEmployee() throws Exception{
        Organization organization = new Organization();
        Person person = organization.getEmployee("Namugga Martha");
        assertNotNull(person);
        assertEquals("Namugga Martha", person.getName());
    }

    @Test //(expected = Exception.class if at all i didnt add the throws part)
    public void testGetEmployeeNotFound() throws Exception{
        Organization organization = new Organization();
        organization.getEmployee("Mumbere Joshua"); //For this case i have used an employee that doesnt exist ...However if at all i change and place an employee that exists it will not show as an error!!
    }

    @Test
    public void testGetRoom() throws Exception{
        Organization organization = new Organization();
        Room room = organization.getRoom("LLT6A");
        assertNotNull(room);
        assertEquals("LLT6A", room.getID());
    }

    @Test //(expected = Exception .class if at all i didnt add the throws part)
    public void testGetRoomNotFound() throws Exception{
        Organization organization = new Organization();
        organization.getRoom("KIKONI");// For this case it will have to throw the error if at all the room ID is not valid 
        //However if i change it to a valid one it will not
    }
    // You are not required to write tests for all classes.
}
