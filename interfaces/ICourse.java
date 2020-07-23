/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import datamodels.Classroom;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MissingDataException;

/**
 *
 * @author Steve
 */
public interface ICourse {
    
    public String getCourseID();
    public String getCourseName();
    public Classroom getClassroom();
    
    public void setCourseID(String courseID) throws InvalidDataException, MissingDataException;
    public void setCourseName(String courseName);
    public void setClassroom(Classroom classroom) throws InvalidDataException, MissingDataException;
}
