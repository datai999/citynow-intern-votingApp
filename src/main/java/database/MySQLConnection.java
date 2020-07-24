package database;

import java.sql.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {

    Logger _logger;
    Connection _connection = null;
    PreparedStatement _preStmt = null;

    public MySQLConnection() {

        String hostName = "sql12.freemysqlhosting.net";
        String dbName = "sql12350846";
        String userName = "sql12350846";
        String password = "7tSjH8Wqv9";

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        _logger = Logger.getLogger(this.getClass().getName());

        try {
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
            _connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            _logger.log(Level.WARNING, throwables.getMessage());
        }

    }

    void close(){
        try {
            _connection.close();
            _preStmt.close();
        } catch (SQLException throwables) {
            _logger.info(throwables.getMessage());
//          throwables.printStackTrace();
        }
    }

    void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            _logger.info(throwables.getMessage());
        }
    }

    void createPreStmt(String query, List<Object> params) throws SQLException {
        _preStmt = _connection.prepareStatement(query);
        if (params != null)
            for (int index=0; index < params.size(); index++){
                _preStmt.setObject(index+1, params.get(index));
            }
    }

    public void execute(String query, List<Object> params, Consumer<ResultSet> func) {

        ResultSet rs = null;
        try {
            createPreStmt(query, params);
            rs = _preStmt.executeQuery();
            func.accept(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            close();
            if ( rs != null)
                close(rs);
        }
    }

    public int execute(String query, List<Object> params){
        int res = 0;
        try {
            createPreStmt(query,params);
            System.out.println(_preStmt.toString());

            res = _preStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            close();
        }
        return res;
    }
}
