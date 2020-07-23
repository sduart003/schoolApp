/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionhandlers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author steveduarte
 */
public class FilePathError {
    
    public FilePathError(){
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "File path not found", "Alert", JOptionPane.WARNING_MESSAGE);
    }
}
