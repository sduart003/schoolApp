package utilities;

/**
 * This class is used to initialize the database. It has methods to drop all the 
 * tables and create all the tables.  
 * NOTE: the database must already exist in order to create and drop tables
 */
import controllers.Application;
import static controllers.Application.getLOGGER;
import java.sql.*;

public final class InitializeDatabase {
    
    /**
     * Table creation Strings
     * Used to initialize the database tables
     */
    private static String createTables[] = {
        "CREATE TABLE classrooms (roomnumber varchar(5), roomtype varchar(10), capacity integer, PRIMARY KEY (roomnumber))",
        "CREATE TABLE courses (courseid varchar(7), coursename varchar(100), room varchar(5), PRIMARY KEY (courseid))",
//        "CREATE TABLE students (studentid integer, name varchar(100), address varchar(100), dateofbirth ??, dataofgraduation ??, gpa float, PRIMARY KEY (studentid))",
//        "CREATE TABLE faculty (facultyid varchar(9), name varchar(100), address varchar(100), dateofbirth ??, dataofhire ?? salary float, PRIMARY KEY (facultyid))"
    };

    /**
     * Table deletion strings
     * Used to delete the tables from the database. Dropping tables will also
     * remove all the data!
     */
    private static String dropTables[] = {
        "DROP TABLE courses",
        "DROP TABLE classrooms",
        "DROP TABLE students",
        "DROP TABLE faculty"};

    /**
     * Registers the driver code for the database. 
     * The driver communicates between the Java JDBC and the database.
     * Creates the database connection object using the url string. This 
     * string contains the IP address of the database and also the database name.
     * In this case, the database instance name is "uml".
     * It also lists the driver manager name "mysql"
     */
    public static void openDatabaseConnection() {

        // Register the MySQL JDBC Driver
        try {
           // Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Application.getLOGGER().info("Database driver loaded");
        } catch (Exception e) {
            Application.getLOGGER().severe(e.getMessage());
            return;
        }

        // This URL specifies we are connecting with a local database.
        String url = "jdbc:mysql://localhost/uml";

        // Make a connection with the database. User is root, no password
        try {
            DBProperties.setConnection(DriverManager.getConnection(url, "globaladmin", "Inner1$Class"));
            Application.getLOGGER().info("Database connection created");
        } catch (SQLException e) {
            Application.getLOGGER().severe(
                    "Unable to make a connection to the database.\n"
                    + "The reason: " + e.getMessage());
            return;
        }
    }

    /**
     * Closes the database connection and releases resources.
     * For efficiency, you should only open and close the database once per application 
     * instance.
     */
    public static void closeDatabaseConnection() {
        // Close the statement and the connection
        try {
            DBProperties.getConnection().close();
            getLOGGER().info("Database connection closed");
        } catch (SQLException e) {
            Application.getLOGGER().severe(
                    "Unable to make a connection to the database.\n"
                    + "The reason: " + e.getMessage());
            return;
        }
    }

    /**
     * Drops all the tables listed in the dropTables array and removes all
     * the data
     * 
     * @throws SQLException 
     */
    public static void dropTables() throws SQLException {
        openDatabaseConnection();
        Statement st = DBProperties.getConnection().createStatement();

        for (int i = 0; i < createTables.length; i++) {
            try {
                st.execute(dropTables[i]);
                Application.getLOGGER().info("Table: " + dropTables[i] + " dropped");
            } catch (SQLException sqlException) {
                Application.getLOGGER().severe(sqlException.getMessage());
            }
        }
        st.close();
        closeDatabaseConnection();
    }

    /**
     * Create all the tables listed in the createTables array
     */
    public static void createTables() throws SQLException {
        openDatabaseConnection();
        Statement st = DBProperties.getConnection().createStatement();

        for (int i = 0; i < createTables.length; i++) {
            try {
                st.execute(createTables[i]);
                Application.getLOGGER().info("Table: " + createTables[i] + " created");
            } catch (SQLException sqlException) {
                Application.getLOGGER().severe(sqlException.getMessage());
            }
        }

        // Closes the statement object and the database connection
        st.close();
        closeDatabaseConnection();
        
    }

    /**
     * Restore database to initial settings
     */
    public static void resetDatabase() throws SQLException {
        Application.getLOGGER().info("Database reset");
        dropTables();
        createTables();
    }

    /**
     * This method is available if you want to initialize the database external
     * to the application.
     * 
     * @param args 
     */
    public static void main(String args[]) {
        try {
            resetDatabase();
        } catch (SQLException e) {
            Application.getLOGGER().severe(e.getMessage());
        }
    }

}
