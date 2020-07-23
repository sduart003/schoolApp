
package datamodels;

import controllers.Application;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MissingDataException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Steve
 */
public class Student extends Person {
    
    private int studentID;
    private LocalDate dateOfGraduation;
    private float gpa;
    private ArrayList<Course> listOfCourses = new ArrayList<Course>();
    
    public Student(){
        Application.getLOGGER().info(Student.class.getName() + " : creating student");
    }
    
    
    public void setStudentID(int p_studentID) throws InvalidDataException, MissingDataException {

        if (p_studentID < 1000000 || p_studentID > 99999999){
            Application.getLOGGER().info(Student.class.getName() + " : invalid student ID" + studentID);
            throw new InvalidDataException("Invalid Student ID: " + p_studentID);
        } else{
            studentID = p_studentID;
            Application.getLOGGER().info(Student.class.getName() + " : setting student ID" + studentID);       

        }
    }
    
    public void setStudentID(String p_studentID) throws InvalidDataException, MissingDataException{
        
// check to see if student id is empty or null
        if (p_studentID == null || p_studentID.isEmpty()){
            Application.getLOGGER().info(Student.class.getName() + " : invalid student ID" + studentID);
            throw new MissingDataException (":Student ID" + p_studentID);
        }
        
        // Check to see if student id starts with zero or not 7 characters in length
        if ((p_studentID.startsWith("0")) || (p_studentID.length() != 7)) {
            Application.getLOGGER().info(Student.class.getName() + " : invalid student ID" + studentID);
            throw new InvalidDataException(":Student ID" + p_studentID);
        }
        
        // If we pass all the previous tests, try to parse the string into an interger
        // If unparsable, a parsing error will be thrown.  
        // Catch the error and throw an InvalidDataException
        try {
            studentID = Integer.parseInt(p_studentID);
            setStudentID(studentID);
            Application.getLOGGER().info(Student.class.getName() + " : setting student ID" + studentID);
        } catch (NumberFormatException exp) {
            Application.getLOGGER().info(Student.class.getName() + " : invalid student ID" + studentID);
            throw new InvalidDataException(":Student ID" + p_studentID);
        }
    }
    
    public void setDateOfGraduation(LocalDate p_dateOfGraduation) throws InvalidDataException{
        if (p_dateOfGraduation == null) {
            dateOfGraduation = null;
            Application.getLOGGER().info(Student.class.getName() + " : invalid date of graduation, setting to null ");
            throw new InvalidDataException("Invalid date of graduation, setting to null");
        }
        dateOfGraduation = p_dateOfGraduation;
        Application.getLOGGER().info(Student.class.getName() + " : setting date of graduation");
    }
    
    public void setGPA(float p_gpa) throws InvalidDataException {
        if (p_gpa > 0){
            gpa = p_gpa;
            Application.getLOGGER().info(Student.class.getName() + " : setting gpa" + gpa);
        } else {
            gpa = 0.0f;
            Application.getLOGGER().info(Student.class.getName() + " : invalid GPA, setting to 0.0");
            throw new InvalidDataException (":GPA, setting to 0.0");
        }
    }
    
    public float getGPA() {
        return gpa;
    }
    
    public java.time.LocalDate getDateOfGraduation(){
        return dateOfGraduation;
    }
    
    public int getStudentID(){
        return studentID;
    }
    
    public ArrayList<Course> getListOfCourses(){
        return listOfCourses;
    }
    
    public String toString(){
        return "Student{" + super.toString() + ", studentID=" + studentID + 
                ", dateOfGraduation=" + dateOfGraduation + ", gpa=" + gpa +  
                ", listOfCourses=" + listOfCourses +  '}';
    }

}
