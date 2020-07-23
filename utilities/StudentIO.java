/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.Application;
import datacontainers.StudentDC;
import datamodels.Student;
import exceptionhandlers.FilePathError;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author steveduarte
 */
public class StudentIO {
     
    /**
     * Constructor is declared private because the IO classes are utilities
     * which contain static methods
     */
    private StudentIO() {
    }

    /**
     * Writes out the classroom data in JSON format containing all classrooms in
     * the classroom data container
     *
     */
    public static void writeJSONFile(String fileLocation, StudentDC studentDataContainer) {

        PrintWriter jsonFile = null;

        try {
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "student.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(studentDataContainer.getListOfStudents(), jsonFile);

        } catch (Exception exp) {
            Application.getLOGGER().info(StudentIO.class.getName() + " : error writing student data");
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
    public static ArrayList<Student> readJSONFile(String fileLocation) {

        ArrayList<Student> listOfStudents = new ArrayList<>();

        try {
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "student.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Student[] courseArray = gson.fromJson(jsonFile, Student[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < courseArray.length; i++) {
                listOfStudents.add(courseArray[i]);
            }  
            
            Application.getLOGGER().info(StudentIO.class.getName() + " : reading student data");

        } catch (Exception e) {  
            Application.getLOGGER().info(StudentIO.class.getName() + " : error reading student data");
        } finally {
            return listOfStudents;
        }
    }
}
