
package datamodels;

import controllers.Application;
import exceptionhandlers.InvalidDataException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Steve
 */
public class Faculty extends Person {
    
    private LocalDate dateOfHire;
    private double salary;
    private String status;
    private ArrayList<Course> listOfCourses = new ArrayList<Course>();
    private String check_status;
       
  
    public Faculty(){
        Application.getLOGGER().info(Faculty.class.getName() + " : creating Faculty");
    }
    
    public void setDateOfHire(LocalDate p_dateOfHire){
        dateOfHire = p_dateOfHire;
        Application.getLOGGER().info(Faculty.class.getName() + " : setting date of hire" + this.dateOfHire.toString());
    }
    
    public void setSalary (double p_salary) throws InvalidDataException { 
        if (p_salary <= 0){
            p_salary = 0.0;
            throw new InvalidDataException ("Invalid salary. Setting to $0");
        }
        salary = p_salary;
        Application.getLOGGER().info(Faculty.class.getName() + " : setting salary" + salary);
    }
    
    public void setStatus(String p_status) {
       if(p_status == null){
           Application.getLOGGER().info(Faculty.class.getName() + " : setting default status" + status);
           p_status = "Fulltime";
       }
        status = p_status; 
        Application.getLOGGER().info(Faculty.class.getName() + " : setting status" + status);
       
    }
    
    public LocalDate getDateOfHire(){
        return dateOfHire;
    }
    
    public double getSalary(){
        return salary;
    }
   
    public String getStatus(){
        return status;
    }

    public ArrayList<Course> getListOfCourses(){
        return listOfCourses;
    }
    
    /* Added a method in this class to check status regardless of wether the 
     * setStatus() method is called or not. In our error handling test, setStatus
     * is not called.
     */
    public String checkStatus(){
        String status_check;
        
        if (status == null) {
            status_check = "Fulltime";
        }
        else {
            status_check = status;
        }
        return status_check;
    }
    
    public String toString(){
         
        return "Faculty{" + super.toString() + ", dateOfHire=" + dateOfHire +
                ", status=" + checkStatus() + ", salary=" + salary + ", listOfCourses=" +  
                listOfCourses + '}';
    }
    
    
}
