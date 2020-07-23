package model.DAO;

import controller.session.Session;
import model.DTO.UserAccount;
import database.MySQLConnection;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginAction {

    private LoginAction(){}
    private static class LazyHolder{
        public static final LoginAction INSTANCE = new LoginAction();
    }

    public static LoginAction getInstance(){
        return LazyHolder.INSTANCE;
    }


    public boolean login(String username, String password, HttpSession session){

        AtomicBoolean flag = new AtomicBoolean(false);

//            Todo: better query
//            String query = "SELECT EXISTS (SELECT username,password FROM user WHERE username = ? AND password = ? )"

        String query = "SELECT * FROM user WHERE username = ? AND password = ?";;

        List<Object> params = Arrays.asList(new Object[]{username, password});

        MySQLConnection database = new MySQLConnection();
        database.execute(query, params, rs ->{
            try {
                if (rs.next()) {
                    UserAccount user = new UserAccount(rs);
                    Session.storeLoginSuccess(session, user);
                    flag.set(true);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return flag.get();
    }

}
