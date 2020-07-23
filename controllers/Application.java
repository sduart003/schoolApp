/*
 * This is the main entry into the application. It creates a menu controller object
 * and the controller object creates the forms and the data models as needed
 */
package controllers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;


public class Application {
        
    // create a static logger
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    // create a file handler for the logger
    private static FileHandler FILEHANDLER;
    
    public static void main(String[] args) {
             
        if (args.length != 2){
             Application.getLOGGER().severe(Application.class.getName() + "Not enough arguments entered");
             JOptionPane.showMessageDialog(null,
                "Invalid Number of Arguments Provided. "
                        + "Please enter 2 arguments and try again.",
                "WARNING",
                JOptionPane.WARNING_MESSAGE);
             System.exit(0);
        }
            
        try { 
            
            // create a log file
            FILEHANDLER = new FileHandler(args[1]);
            
            // set default level of logging
            LOGGER.setLevel(Level.OFF);
            
            // link the debug handler to the debug logger
            LOGGER.addHandler(FILEHANDLER);
            
            // create simple formatter for both files
            FILEHANDLER.setFormatter(new SimpleFormatter());
            
            // make sure logging goes to file only
            LOGGER.setUseParentHandlers(false);
            
        } catch(IOException e){
            Application.getLOGGER().info(Application.class.getName() + "Logger not handled properly");
            e.printStackTrace();
            System.exit(0);
        }
       
        // Create main menu controller, the controller creates the menu form
        MainMenuController controller = new MainMenuController(args[0]);

        // Retrieve the main menu form from the controller and make it visible
        controller.getMainMenu().setVisible(true);
     
    } 
    
    // Get methods for the loggers
    public static Logger getLOGGER() {
        return LOGGER;
    }
}
