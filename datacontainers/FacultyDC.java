/*
 *  This Class contains a list which will hold factory objects created in the UI
 */
package datacontainers;

import datamodels.Faculty;

import java.util.ArrayList;
import java.util.List;

public class FacultyDC {

    private List<Faculty> listOfFaculty = new ArrayList<>();

    public FacultyDC() {
        
    }
    
    public List<Faculty> getListOfFaculty() {
        return listOfFaculty;
    }

    public void setListOfFaculty(List<Faculty> listOfFaculty) {
        this.listOfFaculty = listOfFaculty;
    }
    
}