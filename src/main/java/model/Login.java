package model;

import beans.UserAccount;
import database.MySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Login {

    private Login(){}
    private static class LazyHolder{
        public static final Login INSTANCE = new Login();
    }
    public static Login getInstance(){
        return LazyHolder.INSTANCE;
    }



    public boolean login(String username, String password){

        try {

            Connection connection = MySQLConnection.getConnection();
            String sql = "SELECT EXISTS (SELECT username,password FROM user WHERE username ="+username +" AND password = " + password + " )";



            ResultSet rs = connection.createStatement().executeQuery(sql);

            while (rs.next()) {
                UserAccount user = new UserAccount(rs);
//                lsUser.add(user);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        if (lsUser.)

        return false;
    }
}
