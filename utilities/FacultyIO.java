/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.Application;
import datacontainers.FacultyDC;
import datamodels.Faculty;
import exceptionhandlers.FilePathError;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author steveduarte
 */
public class FacultyIO {
    
    /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods
     */
    private FacultyIO() {
    }
 
    
    /**
     * Writes out the classroom data in JSON format containing all classrooms in
     * the classroom data container
     *
     */
    public static void writeJSONFile(String fileLocation, FacultyDC facultyDataContainer) {

        PrintWriter jsonFile = null;

        try {
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "faculty.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(facultyDataContainer.getListOfFaculty(), jsonFile);

        } catch (Exception exp) {
            Application.getLOGGER().info(FacultyIO.class.getName() + " : error writing faculty data");
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
    public static ArrayList<Faculty> readJSONFile(String fileLocation) {

        ArrayList<Faculty> listOfFaculty = new ArrayList<>();

        try {
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "faculty.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Faculty[] courseArray = gson.fromJson(jsonFile, Faculty[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < courseArray.length; i++) {
                listOfFaculty.add(courseArray[i]);
            }            
            Application.getLOGGER().info(FacultyIO.class.getName() + " : reading faculty data");

        } catch (Exception e) {
            Application.getLOGGER().info(FacultyIO.class.getName() + " : error reading faculty data");
        } finally {
            return listOfFaculty;
        }
    }
}
