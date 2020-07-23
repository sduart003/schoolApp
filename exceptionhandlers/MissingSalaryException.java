package exceptionhandlers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MissingSalaryException {

    /**
     * Constructor takes the following data:
     * 
     * @param badFormData - the form that has bad data
     * @param exception   - the exception that was thrown
     * 
     * Creates a popup window that displays the error
     */
    public MissingSalaryException(){
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Missing Data in Salary, Setting to 0", "Alert", JOptionPane.WARNING_MESSAGE);
    }
    
  
}
