/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.Application;
import datacontainers.CourseDC;
import datamodels.Course;
import exceptionhandlers.FilePathError;
import interfaces.ICourse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author steveduarte
 */
public class CourseIO {
    
     /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods
     */
    private CourseIO() {
    }

    
    /**
     * Writes out the classroom data in JSON format containing all classrooms in
     * the classroom data container
     *
     */
    public static void writeJSONFile(String fileLocation, CourseDC courseDataContainer) {

        PrintWriter jsonFile = null;

        try {
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "course.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(courseDataContainer.getListOfCourses(), jsonFile);

        } catch (Exception exp) {
            Application.getLOGGER().info(CourseIO.class.getName() + " : error writing out course data");
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
     * @param fileLocation
     * @return 
     */
    public static ArrayList<ICourse> readJSONFile(String fileLocation) {

        ArrayList<ICourse> listOfCourses = new ArrayList<>();

        try {
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "course.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Course[] courseArray = gson.fromJson(jsonFile, Course[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < courseArray.length; i++) {
                listOfCourses.add(courseArray[i]);
            }
            Application.getLOGGER().info(CourseIO.class.getName() + " : reading course data");
        } catch (Exception e) {
            Application.getLOGGER().info(CourseIO.class.getName() + " : error reading course data ");
        } finally {
            return listOfCourses;
        }
    }
    
}
