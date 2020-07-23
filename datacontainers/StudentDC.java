/*
 *  This Class contains a list which will hold student objects created in the UI
 */
package datacontainers;

import datamodels.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDC {

    private List<Student> listOfStudents = new ArrayList<>();

    public StudentDC() {
        
    }
    
    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
    
}