package model.DAO;

import model.DTO.UserAccount;
import database.MySQLConnection;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class Login {

    private Login(){}
    private static class LazyHolder{
        public static final Login INSTANCE = new Login();
    }

    public static Login getInstance(){
        return LazyHolder.INSTANCE;
    }


    public boolean login(String username, String password, HttpSession session){

//            Todo: better query
//            String query = "SELECT EXISTS (SELECT username,password FROM user WHERE username = ? AND password = ? )"

        String query = "SELECT * FROM user WHERE username = ? AND password = ?";;

        List<Object> params = Arrays.asList(new Object[]{username, password});

        try {
            MySQLConnection database = new MySQLConnection();
            ResultSet rs =  database.execute(query, params);
            if(rs.next()){
                storeLoginSuccess(session, new UserAccount(rs));
                return true;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    //  todo : create new class
    void storeLoginSuccess(HttpSession session, UserAccount user){
        user.setPassword("");
        session.setAttribute("loginSuccess", user);
    }

    public UserAccount getUserLoginSuccess(HttpSession session){
        return (UserAccount) session.getAttribute("loginSuccess");
    }
}
