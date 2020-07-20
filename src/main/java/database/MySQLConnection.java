package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        String hostName = "sql12.freemysqlhosting.net";
        String dbName = "sql12350846";
        String userName = "sql12350846";
        String password = "7tSjH8Wqv9";

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        return DriverManager.getConnection(connectionURL, userName, password);

    }
}
