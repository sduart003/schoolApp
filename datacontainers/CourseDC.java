/*
* This class contains a list which will hold course objects created in the UI
*/
package datacontainers;

import interfaces.ICourse;

import java.util.ArrayList;
import java.util.List;

public class CourseDC {
    
    private ArrayList<ICourse> listOfCourses = new ArrayList<>();
    
    public CourseDC() {
    }
    
    public List<ICourse> getListOfCourses(){
        return listOfCourses;
    }
    
    public void setListOfCourses(ArrayList<ICourse> listOfCourses){
        this.listOfCourses = listOfCourses;
    }
    
}
