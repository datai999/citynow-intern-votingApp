package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dto.user.UserAccount;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class LoginDaoService extends BaseDao {

    private LoginDaoService(){
        super();
    }
    private static class LazyHolder{
        public static final LoginDaoService INSTANCE = new LoginDaoService();
    }
    public static LoginDaoService getInstance(){
        return LazyHolder.INSTANCE;
    }


    public UserAccount login(String username, String password){


        final UserAccount[] user = new UserAccount[1];

//            Todo: better query
//            String query = "SELECT EXISTS (SELECT username,password FROM user WHERE username = ? AND password = ? )"

        String query = "SELECT * FROM user WHERE username = ? AND password = ?";;
        List<Object> params = Arrays.asList(new Object[]{username, password});

        execute(query, params, rs ->{
            try {
                if (rs.next()) {
                    user[0] = new UserAccount(rs);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        return user[0];
    }

}
