package utilities;

import java.sql.Connection;
import java.sql.Statement;

public class DBProperties {
    
    private static Connection connection;
    private static Statement statement;

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }    

    public static void setConnection(Connection connection) {
        DBProperties.connection = connection;
    }

    public static void setStatement(Statement statement) {
        DBProperties.statement = statement;
    }    
    
}
