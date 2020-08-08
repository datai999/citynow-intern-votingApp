package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {

    Logger _logger;
    Connection _connection = null;


    public MySQLConnection() {

        String hostName = "sql12.freemysqlhosting.net";
        String dbName = "sql12357362";
        String userName = "sql12357362";
        String password = "WvPadqUhXe";

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName +"?characterEncoding=UTF-8";
        _logger = Logger.getLogger(this.getClass().getName());

//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
//        _connection = DriverManager.getConnection(connectionURL, userName, password);


        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
            _connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            _logger.log(Level.WARNING, throwables.getMessage());
        }

    }

    public Connection getConnection(){
        return _connection;
    }

    public void close(){
        try {
            _connection.close();
        } catch (SQLException throwables) {
            _logger.info(throwables.getMessage());
        }
    }
}
