package database;

import java.sql.*;
import java.util.List;

public class MySQLConnection {

    Connection _connection = null;

    public MySQLConnection() throws SQLException {

        String hostName = "sql12.freemysqlhosting.net";
        String dbName = "sql12350846";
        String userName = "sql12350846";
        String password = "7tSjH8Wqv9";

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        _connection = DriverManager.getConnection(connectionURL, userName, password);
    }

    public ResultSet execute(String query, List<Object> params) throws SQLException, ClassNotFoundException {

        PreparedStatement preStmt = _connection.prepareStatement(query);
        for (int index=0; index < params.size(); index++){
            preStmt.setObject(index+1, params.get(index));
        }

        return preStmt.executeQuery();
    }
}
