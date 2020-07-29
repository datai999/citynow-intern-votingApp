package model.service.dao.user;

import model.service.dao.BaseDao;
import model.service.IUserService;
import model.dto.UserAccount;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class LoginDao extends BaseDao {

    private LoginDao(){
        super();
    }
    private static class LazyHolder{
        public static final LoginDao INSTANCE = new LoginDao();
    }

    public static LoginDao getInstance(){
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
