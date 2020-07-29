package model.service.dao;

import database.MySQLConnection;

import java.sql.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

public abstract class BaseDao {

    Logger _logger;
    MySQLConnection _myDB = null;
    Connection _connection = null;
    PreparedStatement _preStmt = null;

    protected BaseDao() {

        _logger = Logger.getLogger(this.getClass().getName());


    }

    void createConnect(){
        _myDB = new MySQLConnection();
        _connection = _myDB.getConnection();
    }


    void createPreStmt(String query, List<Object> params) throws SQLException {
        _preStmt = _connection.prepareStatement(query);
        if (params != null)
            for (int index=0; index < params.size(); index++){
                _preStmt.setObject(index+1, params.get(index));
            }
        System.out.println(_preStmt.toString());
    }

    protected void execute(String query, List<Object> params, Consumer<ResultSet> func) {
        createConnect();
        ResultSet rs = null;
        try {
            createPreStmt(query, params);
            rs = _preStmt.executeQuery();
            func.accept(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            _myDB.close();
            if ( rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    protected int execute(String query, List<Object> params){
        createConnect();
        int res = 0;
        try {
            createPreStmt(query,params);
            res = _preStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            _myDB.close();
        }
        return res;
    }
}