
package datamodels;
import controllers.Application;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MissingDataException;
import interfaces.ICourse;

public class Course implements ICourse {
    
    private String courseID;
    private String courseName;
    private Classroom classroom;
    
    public Course(){
        Application.getLOGGER().info(Course.class.getName() + " : creating course ");
    }
    
    public String getCourseID(){
        return courseID;
    }
    
    public void setCourseID(String p_courseID) throws InvalidDataException, MissingDataException {
        if (p_courseID == ""){
            throw new MissingDataException (":Course ID");
        }
        else if (!p_courseID.matches("^[a-zA-Z]{4}[0-9]{3}$")) {
            throw new InvalidDataException("Invalid course ID");
        } 
        
        String updatedCourseNumber = p_courseID.substring(0, 4).toUpperCase() +
                p_courseID.substring(4, 7);
        
        courseID = p_courseID;
        
        Application.getLOGGER().info(Course.class.getName() + " : setting Course ID" + this.courseID);
    }
    
    public String getCourseName(){
        return courseName;
    }
    
    public void setCourseName(String p_courseName){
        courseName = p_courseName;
    }
    
    public Classroom getClassroom() {
        return classroom;
    }
    
    public void setClassroom (Classroom p_classroom) throws MissingDataException{
        if (p_classroom == null){
            this.classroom = new Classroom();
            Application.getLOGGER().info(Course.class.getName() + " : setting default classroom");
            throw new MissingDataException(":Classroom, setting to default");                             
        }
        classroom = p_classroom;
        Application.getLOGGER().info(Course.class.getName() + " : setting classroom" + this.classroom.getRoomNumber());
    }   
    
    @Override
    public String toString(){
        return "Course{" + "courseID=" + courseID + ", courseName=" + courseName 
                + ", classroom=" + classroom + "}";
    }
    
}
