/*
 *  This Class contains methods to write out the classroom objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.Application;
import datacontainers.ClassroomDC;
import datamodels.Classroom;
import exceptionhandlers.FilePathError;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClassroomIO {

    /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods
     */
    private ClassroomIO() {
    }

    /**
     * Writes out the classroom data in JSON format containing all classrooms in
     * the classroom data container
     *
     */
    public static void writeJSONFile(String fileLocation, ClassroomDC classroomDataContainer) {

        PrintWriter jsonFile = null;
        
        try {
           
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "classroom.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(classroomDataContainer.getListOfClassrooms(), jsonFile);

        } catch (Exception exp) {
           Application.getLOGGER().info(ClassroomIO.class.getName() + " error writing out classroom data");
           FilePathError notFound = new FilePathError();
        } finally {
            // Flush the output stream and close the file
            jsonFile.flush();
            jsonFile.close();
        }
    }
    
     /**
     * Reads a JSON formatted file of classrooms and returns an array list of
     * classrooms.
     *
     */
    public static ArrayList<Classroom> readJSONFile(String fileLocation) {

        ArrayList<Classroom> listOfClassrooms = new ArrayList<>();
        
        try {
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "classroom.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Classroom[] classroomArray = gson.fromJson(jsonFile, Classroom[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < classroomArray.length; i++) {
                listOfClassrooms.add(classroomArray[i]);
            }
            Application.getLOGGER().info(ClassroomIO.class.getName() + " : reading classroom data");
        } catch (Exception e) {
            Application.getLOGGER().info(ClassroomIO.class.getName() + " : error reading classroom data");
        } finally {    
            return listOfClassrooms;
        }  
        
    }
    
    /**
     * Store all classrooms to database
     */
    public static void storeClassrooms(ClassroomDC classroomDataContainer) {

        // Open database connection
        InitializeDatabase.openDatabaseConnection();

        // We are looping through the list of classrooms. Each classroom will
        // create its own statement in a try/catch block so that if one insert
        // fails, they all won't fail.  We could have put the for loop inside the
        // try/catch block but then all the inserts will fail after the first failure
        for (Classroom room : classroomDataContainer.getListOfClassrooms()) {
            try {
                // Retreive the database connection and create the statement object
                Statement insertStatement = DBProperties.getConnection().createStatement();
                // Create the string for the statement objrct
                String command = "INSERT INTO uml.classrooms (roomnumber, roomtype, capacity)"
                        + "VALUES ('" + room.getRoomNumber() + "','"
                        + room.getTypeOfRoom() + "','" + room.getCapacity() + "')";
                // Execute the statement object 
                insertStatement.executeUpdate(command);
                // Close the statement object.
                insertStatement.close();
            } catch (SQLException e) {
                Application.getLOGGER().info(ClassroomIO.class.getName() + "A database error ocurred storing");
                throw new RuntimeException("A database error occured updating"
                        + " classroom table " + e.getMessage());
            }
        }

        // Close database connection
        InitializeDatabase.closeDatabaseConnection();
    }
    
    
    /**
     * retrieve data from database
     */
    public static ArrayList<Classroom> retrieveClassrooms() {

        ArrayList<Classroom> listOfClassrooms = new ArrayList<>();

        try {
            // Create a Statement object to execute the query on
            Statement statement = DBProperties.getConnection().createStatement();
            // Create the string for the statement objrct
            String command = "SELECT roomnumber, roomtype, capacity FROM classrooms ORDER BY roomnumber";
            // Execute the statement object 
            ResultSet results = statement.executeQuery(command);
            // Call private helper method to parse the result set into the array list
            listOfClassrooms = parseResults(results);
        } catch (SQLException e) {
            Application.getLOGGER().info(ClassroomIO.class.getName() + "A database error ocurred retrieving");
            throw new RuntimeException("A database error occured retrieving"
                    + " classroom table " + e.getMessage());
        }

        return listOfClassrooms;
    }
    
      /**
     * Populate the array list with data from the database
     */
    private static ArrayList<Classroom> parseResults(ResultSet results) {
        
        ArrayList<Classroom> listOfClassrooms = new ArrayList<>();
        
        try {
            while (results.next()) {
                Classroom room = new Classroom();
                room.setRoomNumber(results.getString(1));
                room.setTypeOfRoom(results.getString(2));
                room.setCapacity(Integer.parseInt(results.getString(3)));
                listOfClassrooms.add(room);
            }
        } catch (Exception e) {
            Application.getLOGGER().info(ClassroomIO.class.getName() + "A database error ocurred parsing");
            System.out.println("Error parsing result set: " + e.getMessage());
        }
        
        return listOfClassrooms;
    }

}
