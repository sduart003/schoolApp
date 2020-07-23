/*
 * The InputCourseFormController class contains the following:
 *
 * 1) an instance of the classroom data model which is used to populate the 
 *    list of classrooms on the form. (passed in via the constructor)
 * 2) an instance of the offered course data model which is used to store any 
 *    offered course objects created (passed in via the constructor)
 * 3) an instance of the offered course input form. (created here)
 *
 * Listens for events on the input form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed" - this method contains all the logic to process the data
 * on the form, as well as several other events
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datacontainers.ClassroomDC;
import datacontainers.CourseDC;
import datamodels.Classroom;
import datamodels.Course;
import exceptionhandlers.ErrorPopup;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MissingDataException;
import view.inputforms.CourseInputForm;

public class InputCourseFormController implements ActionListener {

    // The data datacontainers needed for this form are passed in via the constructor
    CourseDC CourseDC;
    ClassroomDC ClassroomDC;

    // The form is created here
    private CourseInputForm form;

    /**
     * Constructor
     *
     * @param CourseDC
     * @param ClassroomDC
     */
    public InputCourseFormController(CourseDC CourseDC,
            ClassroomDC ClassroomDC) {

        // Store the passed in data datacontainers
        this.CourseDC = CourseDC;
        this.ClassroomDC = ClassroomDC;

        // Create the form
        form = new CourseInputForm(this);

        // make the form visible
        this.form.setVisible(true);
    }

    /**
     * actionPerformed method implemented from the ActionListener interface
     *
     * This method figures out which button was clicked based on the text of the
     * button. The button click event is passed in via the ActionEvent object.
     *
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Save")) {
            this.saveData();
        } else if (event.getActionCommand().equals("Clear")) {
            this.clearForm();
        } else if (event.getActionCommand().equals("Close")) {
            this.closeForm();
        }
    }

    /**
     * Private method called from the actionPerformed method to save the daa
     */
    private void saveData() {

        // Create a new Course object
        Course newCourse = new Course();

        // Create a default classroom
        Classroom classroom = new Classroom();

        try {
            // Retrieve the course id from the form
            String courseid = this.form.getCourseIdField().getText();
            newCourse.setCourseID(courseid.toUpperCase());

            // Retrieve the course name
            String coursename = this.form.getCourseNameField().getText();
            newCourse.setCourseName(coursename);

        } catch (InvalidDataException | MissingDataException exception) {
            Application.getLOGGER().info(InputCourseFormController.class.getName() + " : user error saving course ID/name");
            ErrorPopup e = new ErrorPopup(form, exception);
            return;
        }
        // Retrieve the selected classroom from the drop down list
        // The objects in the list are stored as generic objects and have to be
        // converted to classroom objects
        try {
            classroom = (Classroom) this.form.getListOfClassrooms().getSelectedValue();
            if (classroom != null) {
                newCourse.setClassroom(classroom);
            } else {
                newCourse.setClassroom(new Classroom());
                Application.getLOGGER().info(InputCourseFormController.class.getName() + " : setting default classroom for course");
                throw new MissingDataException("No classroom specified, setting to default");
            }
        } catch (MissingDataException exception) {
            Application.getLOGGER().info(InputCourseFormController.class.getName() + " : error selecting classroom from dropdown");
            ErrorPopup e = new ErrorPopup(form, exception);            
        }
        // Store the object in the application data model list of courses
        this.CourseDC.getListOfCourses().add(newCourse);
    }
        /**
         * Private method to clear the data
         */
    private void clearForm() {
        this.form.getCourseIdField().setText("");
        this.form.getCourseNameField().setText("");
        this.form.getListOfClassrooms().setSelectedIndex(0);
    }

    /**
     * Private method to close the form
     */
    private void closeForm() {
        this.form.dispose();
    }

    public ClassroomDC getClassroomDC() {
        return ClassroomDC;
    }

}