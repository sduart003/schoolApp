package datamodels;

import controllers.Application;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MissingDataException;
import interfaces.IClassroom;

public class Classroom implements IClassroom {

    private String roomNumber = "XXX";
    private String typeOfRoom = "UNKNOWN";
    private int capacity;
    
    public Classroom(){
        Application.getLOGGER().info(Classroom.class.getName() + " : Creating new Classroom");
    }

    public void setRoomNumber(String p_roomNumber) throws InvalidDataException, MissingDataException  {
        // Test for valid room number
        if (p_roomNumber.isEmpty()) {
            throw new MissingDataException("Room number");
        }
        if (!p_roomNumber.matches("^[a-zA-Z]{2}[0-9]{3}$")) {
            throw new InvalidDataException("Room number");
        }
        // If valid, set room number, ensuring first 2 digits are upper case
        String updatedRoomNumber = p_roomNumber.substring(0, 2).toUpperCase() +
                p_roomNumber.substring(2, 5);
        
        this.roomNumber = updatedRoomNumber;
        Application.getLOGGER().info(Classroom.class.getName() + " : setting room number" + this.roomNumber);
    }

    public void setTypeOfRoom(String p_typeOfRoom) throws InvalidDataException, MissingDataException {
        // Test for empty string
        if (p_typeOfRoom.isEmpty()) {
            throw new MissingDataException("Room type");
        }
        // Test for invalid string
        if ((!p_typeOfRoom.equalsIgnoreCase("LAB")) && (!p_typeOfRoom.equalsIgnoreCase("CLASSROOM"))
                && (!p_typeOfRoom.equalsIgnoreCase("LECTURE HALL"))) {
            throw new InvalidDataException("Room type");
        }
        // If valid, set room type
        typeOfRoom = p_typeOfRoom.toUpperCase();
        
        Application.getLOGGER().info(Classroom.class.getName() + " : setting room type " + this.typeOfRoom);

    }

    public void setCapacity(int p_capacity) throws InvalidDataException {

        // Test for valid value 
        if (p_capacity <= 0) {
            throw new InvalidDataException("Room capacity");
        }
        // If valid, set room capacity
        capacity = p_capacity;
        
        Application.getLOGGER().info(Classroom.class.getName() + " : setting room capacity" + this.capacity);

    }
    
    public void setCapacity(String p_capacity) throws InvalidDataException {

        // Test for valid value 
        try {
            Integer.parseInt(p_capacity);
        } catch (Exception exp) {
            throw new InvalidDataException("Room capacity");
        }
        // If valid, set room capacity
        capacity = Integer.parseInt(p_capacity);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Classroom{" + "roomNumber=" + roomNumber + ", typeOfRoom="
                + typeOfRoom + ", capacity=" + capacity + '}';
    }

}
