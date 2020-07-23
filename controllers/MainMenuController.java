/*
 * Listens for events on the menu form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 */
package controllers;

import java.awt.event.ActionListener;
import datacontainers.ClassroomDC;
import datacontainers.CourseDC;
import datacontainers.FacultyDC;
import datacontainers.StudentDC;
import utilities.ClassroomIO;
import java.util.logging.Level;
import utilities.CourseIO;
import utilities.FacultyIO;
import utilities.StudentIO;

import view.MainMenu;

public class MainMenuController implements ActionListener {

    // File location
     String fileLocation;
    
    /**
     * Constructor
     * @param fileLocation 
     */
    public MainMenuController(String fileLocation) {
        this.fileLocation = fileLocation;
    }
    
    // The data models are instantiated here and passed to the 
    // constructors for the controllers
    ClassroomDC classroom = new ClassroomDC();
    CourseDC course = new CourseDC();
    FacultyDC faculty = new FacultyDC();
    StudentDC student = new StudentDC();
    
    // The main menu form gets created here. Notice it takes this controller object
    // as an argument to the constructor
    private MainMenu mainMenu = new MainMenu(this);

    /**
     * The ActionListener interface contains a single method, actionPerformed
     */
    public void actionPerformed(java.awt.event.ActionEvent event) {

        //  Figure out which button was clicked
        String menuItemClicked = event.getActionCommand();

         // create the controller which will open the correct form (refer to the controller constructor
        // methods do determine which data container classes need to be passed to the controller constructors)
        if (menuItemClicked.equals("Add Classroom")) {
            InputClassroomFormController inputController = new InputClassroomFormController(classroom);
        } else if (menuItemClicked.equals("List Classrooms")) {
            ReportClassroomController reportController = new ReportClassroomController(classroom);
        }  if (menuItemClicked.equals("Add Course")) {
            InputCourseFormController inputController = new InputCourseFormController(course, classroom);
        } else if (menuItemClicked.equals("List Courses")) {
            ReportCourseController reportController = new ReportCourseController(course);
        }  if (menuItemClicked.equals("Add Faculty")) {
            InputFacultyFormController inputFacultyController = new InputFacultyFormController(faculty, course);
        } else if (menuItemClicked.equals("List Faculty")) {
            ReportFacultyController reportController = new ReportFacultyController(faculty);
        }  if (menuItemClicked.equals("Add Student")) {
            InputStudentFormController inputController = new InputStudentFormController(student, course);
        } else if (menuItemClicked.equals("List Students")) {
            ReportStudentController reportController = new ReportStudentController(student);
        } else if (menuItemClicked.equals("Exit")) {
            System.exit(0);      
        } else if (menuItemClicked.equals("Save Data")) {
            ClassroomIO.writeJSONFile(fileLocation, classroom);
            CourseIO.writeJSONFile(fileLocation, course);
            FacultyIO.writeJSONFile(fileLocation, faculty);
            StudentIO.writeJSONFile(fileLocation, student);
        } else if (menuItemClicked.equals("Load Data")) {
            classroom.setListOfClassrooms(ClassroomIO.readJSONFile(fileLocation));
            course.setListOfCourses(CourseIO.readJSONFile(fileLocation));
            faculty.setListOfFaculty(FacultyIO.readJSONFile(fileLocation));
            student.setListOfStudents(StudentIO.readJSONFile(fileLocation));
        } else if (menuItemClicked.equals("Log Warning")) {
            Application.getLOGGER().setLevel(Level.WARNING);
        } else if (menuItemClicked.equals("Log Info")){
            Application.getLOGGER().setLevel(Level.INFO);
        } else if (menuItemClicked.equals("Log Severe")) {
            Application.getLOGGER().setLevel(Level.INFO);
        } else if (menuItemClicked.equals("Log All")){
            Application.getLOGGER().setLevel(Level.ALL);
        }
        
    }

    // Getter used in the Application.java class
    public MainMenu getMainMenu() {
        return mainMenu;
    }
}